package io.nexstudios.configservice.config;

import org.spongepowered.configurate.CommentedConfigurationNode;

import java.nio.file.Path;

public abstract class FileConfiguration extends ConfigurationSection {

  protected FileConfiguration(CommentedConfigurationNode node) {
    super(node);
  }

  public abstract Path path();

  public abstract void reload();

  public abstract void save();
}