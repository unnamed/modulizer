package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.MinecraftVersion;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.util.ValidateUtil;

public final class ModuleRepositoryBuilder {

  private final ModuleBinder binder;

  private ModuleFormat moduleFormat = new SimpleModuleFormat(
          "%package%",
          "%class_name%",
          "%identifier%"
  );

  private MinecraftVersion currentVersion;

  private String modulePath = "%package%.%class_name%";
  private String packageName;
  private String className;

  ModuleRepositoryBuilder(ModuleBinder binder) {
    this.binder = ValidateUtil.checkNotNull(
            binder,
            "The binder can't be null."
    );
  }

  public ModuleRepositoryBuilder setModuleFormat(ModuleFormat moduleFormat) {
    this.moduleFormat = ValidateUtil.checkNotNull(
            moduleFormat,
            "The module format can't be null."
    );

    return this;
  }

  public ModuleRepositoryBuilder setPackageName(String packageName) {
    this.packageName = ValidateUtil.checkNotNull(
            packageName,
            "The package name can't be null."
    );

    return this;
  }

  public ModuleRepositoryBuilder setClassName(String className) {
    this.className = ValidateUtil.checkNotNull(
            className,
            "The class name can't be null."
    );

    return this;
  }

  public ModuleRepositoryBuilder setCurrentVersion(MinecraftVersion currentVersion) {
    this.currentVersion = ValidateUtil.checkNotNull(
            currentVersion,
            "The current type can't be null."
    );

    return this;
  }

  public ModuleRepositoryBuilder setModulePath(String modulePath) {
    this.modulePath = ValidateUtil.checkNotNull(
            modulePath,
            "The modulePath can´t be null."
    );

    return this;
  }

  public ModuleRepository build() {
    ModuleRepository moduleRepository = new SimpleModuleRepository(
            binder,
            modulePath,
            moduleFormat,
            currentVersion,
            className,
            packageName
    );

    moduleRepository.getModule();

    return moduleRepository;
  }

}