package io.nexstudios.configservice.service.multireader;

import io.nexstudios.configservice.config.FileConfiguration;
import io.nexstudios.serviceregistry.di.Service;

import java.nio.file.Path;
import java.util.Map;

public interface MultiFileReaderService extends Service {
  Map<Path, FileConfiguration> loadAll(Path relativeDirectory);

  Map<Path, FileConfiguration> cache();

  void reload();
}