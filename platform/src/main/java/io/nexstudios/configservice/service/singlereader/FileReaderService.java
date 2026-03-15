package io.nexstudios.configservice.service.singlereader;

import io.nexstudios.configservice.config.FileConfiguration;
import io.nexstudios.serviceregistry.di.Service;

import java.nio.file.Path;

public interface FileReaderService extends Service {
  FileConfiguration load(Path relativePath, String resourcePath, boolean loadDefaults);
}