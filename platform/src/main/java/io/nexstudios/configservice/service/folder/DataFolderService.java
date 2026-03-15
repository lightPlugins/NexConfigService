package io.nexstudios.configservice.service.folder;

import io.nexstudios.serviceregistry.di.Service;

import java.nio.file.Path;

public interface DataFolderService extends Service {
  Path getDataFolder();
}
