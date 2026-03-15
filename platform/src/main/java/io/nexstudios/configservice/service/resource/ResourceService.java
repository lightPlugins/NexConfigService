package io.nexstudios.configservice.service.resource;

import io.nexstudios.serviceregistry.di.Service;

import java.io.InputStream;
import java.util.Optional;

public interface ResourceService extends Service {
  Optional<InputStream> openResource(String path);
}