package io.nexstudios.configservice;

import io.nexstudios.configservice.service.folder.DataFolderService;
import io.nexstudios.configservice.service.folder.DefaultDataFolderService;
import io.nexstudios.configservice.service.multireader.DefaultMultiFileReaderService;
import io.nexstudios.configservice.service.multireader.MultiFileReaderService;
import io.nexstudios.configservice.service.resource.ClasspathResourceService;
import io.nexstudios.configservice.service.resource.ResourceService;
import io.nexstudios.configservice.service.singlereader.DefaultFileReaderService;
import io.nexstudios.configservice.service.singlereader.FileReaderService;
import io.nexstudios.serviceregistry.di.ServiceAccessor;
import io.nexstudios.serviceregistry.di.ServiceModule;

import java.nio.file.Path;
import java.util.Objects;

public class ConfigServiceModule implements ServiceModule {

  private final Path dataFolder;
  private final ClassLoader resourceClassLoader;

  public ConfigServiceModule(Path dataFolder, ClassLoader resourceClassLoader) {
    this.dataFolder = Objects.requireNonNull(dataFolder, "dataFolder");
    this.resourceClassLoader = Objects.requireNonNull(resourceClassLoader, "resourceClassLoader");
  }

  @Override
  public void install(ServiceAccessor serviceAccessor) {
    Objects.requireNonNull(serviceAccessor, "serviceAccessor");

    DataFolderService dataFolderService = new DefaultDataFolderService(dataFolder);
    ResourceService resourceService = new ClasspathResourceService(resourceClassLoader);

    FileReaderService fileReaderService = new DefaultFileReaderService(dataFolderService, resourceService);
    MultiFileReaderService multiFileReaderService = new DefaultMultiFileReaderService(dataFolderService);

    serviceAccessor.register(DataFolderService.class, dataFolderService);
    serviceAccessor.register(ResourceService.class, resourceService);
    serviceAccessor.register(FileReaderService.class, fileReaderService);
    serviceAccessor.register(MultiFileReaderService.class, multiFileReaderService);
  }
}