package io.nexstudios.configservice.service.folder;

import java.nio.file.Path;
import java.util.Objects;

public final class DefaultDataFolderService implements DataFolderService {

  private final Path dataFolder;

  public DefaultDataFolderService(Path dataFolder) {
    this.dataFolder = Objects.requireNonNull(dataFolder, "dataFolder");
  }

  @Override
  public Path getDataFolder() {
    return dataFolder;
  }
}