package team.unnamed.modulizer.universal.loader;

import team.unnamed.modulizer.universal.MinecraftVersion;
import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.exception.ModuleLoadException;
import team.unnamed.modulizer.universal.internal.repository.ModuleFormat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class SimpleModuleLoader implements ModuleLoader {

  private SimpleModule currentModule;

  private final MinecraftVersion version;

  public SimpleModuleLoader(MinecraftVersion version) {
    this.version = version;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void loadCurrentModule(ModuleBinder binder,
                                String modulePath,
                                ModuleFormat moduleFormat,
                                String className,
                                String packageName) {
    String identifierPlaceholder = moduleFormat.getIdentifierPlaceholder();

    String replacedClassName = className.replace(identifierPlaceholder, version.name());
    String replacedPackageName = packageName.replace(identifierPlaceholder, version.name());

    try {
      Class<? extends SimpleModule> clazz = (Class<? extends SimpleModule>) Class.forName(
              modulePath
                      .replace(moduleFormat.getClassNamePlaceholder(), replacedClassName)
                      .replace(moduleFormat.getPackagePlaceholder(), replacedPackageName)
      );

      Constructor<?> defaultConstructor = clazz.getConstructor();

      boolean accessible = defaultConstructor.isAccessible();

      defaultConstructor.setAccessible(true);

      SimpleModule module = (SimpleModule) defaultConstructor.newInstance();

      defaultConstructor.setAccessible(accessible);

      module.configure(binder);

      currentModule = module;
    } catch (ClassNotFoundException
            | IllegalAccessException
            | InstantiationException
            | NoSuchMethodException
            | InvocationTargetException e) {
      throw new ModuleLoadException(
              "An error has occurred while getting" +
                      " the corresponding module to " + version.name(), e
      );
    }
  }

  @Override
  public Optional<SimpleModule> getCurrentModule() {
    return Optional.ofNullable(currentModule);
  }

}