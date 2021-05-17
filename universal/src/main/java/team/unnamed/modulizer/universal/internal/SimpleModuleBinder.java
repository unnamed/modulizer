package team.unnamed.modulizer.universal.internal;

import team.unnamed.modulizer.universal.bind.ModuleBinderBuilder;
import team.unnamed.modulizer.universal.exception.ModuleLoadException;
import team.unnamed.modulizer.universal.provider.ModuleProvider;
import team.unnamed.modulizer.universal.provider.SimpleModuleProvider;
import team.unnamed.modulizer.universal.type.TypeReference;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleModuleBinder implements InternalModuleBinder {

  private final Map<TypeReference<?>, ModuleProvider<?>> providers;

  public SimpleModuleBinder() {
    providers = new LinkedHashMap<>();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> void set(TypeReference<T> abstractionType, Key<T> key) {
    ModuleProvider<T> versionProvider = new SimpleModuleProvider<>(abstractionType);

    if (providers.containsKey(abstractionType)) {
      versionProvider = (ModuleProvider<T>) providers.get(abstractionType);
    }

    versionProvider.registerVersion(key);
    providers.put(abstractionType, versionProvider);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> ModuleProvider<T> getProvider(TypeReference<T> abstractType) {
    ModuleProvider<T> moduleProvider = (ModuleProvider<T>) providers.get(abstractType);

    if (moduleProvider == null) {
      throw new ModuleLoadException("Couldn't find any module for " + abstractType + ".");
    }

    return moduleProvider;
  }

  @Override
  public <T> ModuleBinderBuilder.Linkable<T> bind(TypeReference<T> abstractionType) {
    return new SimpleModuleBinderBuilder<>(this, abstractionType);
  }

}