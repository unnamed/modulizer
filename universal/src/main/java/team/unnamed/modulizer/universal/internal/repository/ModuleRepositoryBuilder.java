package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.util.Validate;

public final class ModuleRepositoryBuilder<E extends Enum<E>> {

    private final ModuleBinder<E> binder;

    private ModuleFormat moduleFormat = new SimpleModuleFormat("%package%", "%class_name%", "%identifier%");
    private Enum<E> currentType;

    private String modulePath = "%package%.%class_name%";
    private String packageName;
    private String className;

    ModuleRepositoryBuilder(ModuleBinder<E> binder) {
        this.binder = Validate.checkNotNull(binder, "The binder can't be null.");
    }

    public ModuleRepositoryBuilder<E> setModuleFormat(ModuleFormat moduleFormat) {
        this.moduleFormat = Validate.checkNotNull(moduleFormat, "The module format can't be null.");

        return this;
    }

    public ModuleRepositoryBuilder<E> setPackageName(String packageName) {
        this.packageName = Validate.checkNotNull(packageName, "The package name can't be null.");

        return this;
    }

    public ModuleRepositoryBuilder<E> setClassName(String className) {
        this.className = Validate.checkNotNull(className, "The class name can't be null.");

        return this;
    }

    public ModuleRepositoryBuilder<E> setCurrentType(Enum<E> currentType) {
        this.currentType = Validate.checkNotNull(currentType, "The current type can't be null.");

        return this;
    }

    public ModuleRepositoryBuilder<E> setModulePath(String modulePath) {
        this.modulePath = Validate.checkNotNull(modulePath, "The modulePath can´t be null.");

        return this;
    }

    public ModuleRepository<E> build() {
        ModuleRepository<E> moduleRepository = new SimpleModuleRepository<>(binder, modulePath, moduleFormat, currentType, className, packageName);

        moduleRepository.getModule();

        return moduleRepository;
    }

}