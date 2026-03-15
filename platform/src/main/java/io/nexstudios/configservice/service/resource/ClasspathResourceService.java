package io.nexstudios.configservice.service.resource;

import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

public final class ClasspathResourceService implements ResourceService {

  private final ClassLoader classLoader;

  public ClasspathResourceService(ClassLoader classLoader) {
    this.classLoader = Objects.requireNonNull(classLoader, "classLoader");
  }

  @Override
  public Optional<InputStream> openResource(String path) {
    Objects.requireNonNull(path, "path");
    return Optional.ofNullable(classLoader.getResourceAsStream(path));
  }
}