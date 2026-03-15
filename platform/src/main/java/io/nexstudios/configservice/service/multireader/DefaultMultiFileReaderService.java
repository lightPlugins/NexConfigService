package io.nexstudios.configservice.service.multireader;

import io.nexstudios.configservice.config.FileConfiguration;
import io.nexstudios.configservice.config.YamlFileConfiguration;
import io.nexstudios.configservice.service.folder.DataFolderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class DefaultMultiFileReaderService implements MultiFileReaderService {

  private final DataFolderService dataFolderService;

  private volatile Path directory;
  private final ConcurrentHashMap<Path, FileConfiguration> cache = new ConcurrentHashMap<>();

  public DefaultMultiFileReaderService(DataFolderService dataFolderService) {
    this.dataFolderService = Objects.requireNonNull(dataFolderService, "dataFolderService");
  }

  @Override
  public Map<Path, FileConfiguration> loadAll(Path relativeDirectory) {
    Objects.requireNonNull(relativeDirectory, "relativeDirectory");
    this.directory = dataFolderService.getDataFolder().resolve(relativeDirectory);

    reload();
    return cache();
  }

  @Override
  public Map<Path, FileConfiguration> cache() {
    return Map.copyOf(cache);
  }

  @Override
  public void reload() {
    Path dir = this.directory;
    if (dir == null) {
      throw new IllegalStateException("MultiFileReaderService has no directory set. Call loadAll(...) first.");
    }

    Map<Path, FileConfiguration> next = new LinkedHashMap<>();

    if (Files.exists(dir)) {
      try (var walk = Files.walk(dir)) {
        walk.filter(Files::isRegularFile)
            .filter(p -> p.getFileName().toString().endsWith(".yml"))
            .filter(p -> !p.getFileName().toString().startsWith("_"))
            .forEach(p -> {
              Path rel = dir.relativize(p);
              next.put(rel, new YamlFileConfiguration(p, true));
            });
      } catch (IOException e) {
        throw new IllegalStateException("Failed to read directory: " + dir, e);
      }
    }

    cache.clear();
    cache.putAll(next);
  }
}