package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.MinecraftVersion;
import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.internal.InternalModuleBinder;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.loader.ModuleLoader;
import team.unnamed.modulizer.universal.loader.SimpleModuleLoader;
import team.unnamed.modulizer.universal.provider.ModuleProvider;
import team.unnamed.modulizer.universal.type.TypeReference;
import team.unnamed.modulizer.universal.util.ValidateUtil;

import java.util.Optional;

public class SimpleModuleRepository implements ModuleRepository {

  private final InternalModuleBinder binder;
  private final ModuleFormat moduleFormat;

  private final ModuleLoader loader;

  private final String modulePath;
  private final String className;
  private final String packageName;

  public SimpleModuleRepository(ModuleBinder binder,
                                String modulePath,
                                ModuleFormat moduleFormat,
                                MinecraftVersion currentVersion,
                                String className,
                                String packageName) {
    ValidateUtil.checkState(
            binder instanceof InternalModuleBinder,
            "Binder isn't an instance of InternalModuleBinder"
    );

    this.binder = (InternalModuleBinder) binder;
    this.moduleFormat = moduleFormat;
    this.modulePath = modulePath;
    this.loader = new SimpleModuleLoader(currentVersion);
    this.className = className;
    this.packageName = packageName;
  }

  @Override
  public SimpleModule getModule() {
    Optional<SimpleModule> moduleOptional = loader.getCurrentModule();

    if (!moduleOptional.isPresent()) {
      loader.loadCurrentModule(binder, modulePath, moduleFormat, className, packageName);

      return getModule();
    }

    return moduleOptional.get();
  }

  @Override
  public <T> ModuleProvider<T> getProvider(TypeReference<T> abstractType) {
    return binder.getProvider(abstractType);
  }

}