package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.bind.ModuleBinder;

public final class ModuleRepositoryBuilder<E extends Enum<E>> {

    private final ModuleBinder<E> binder;

    private ModuleFormat moduleFormat = new SimpleModuleFormat("%package%", "%class_name%", "%identifier%");
    private Enum<E> currentType;

    private String modulePath = "%package%.%class_name%";
    private String packageName;
    private String className;

    ModuleRepositoryBuilder(ModuleBinder<E> binder) {
        this.binder = binder;
    }

    public ModuleRepositoryBuilder<E> setModuleFormat(ModuleFormat moduleFormat) {
        this.moduleFormat = moduleFormat;

        return this;
    }

    public ModuleRepositoryBuilder<E> setPackageName(String packageName) {
        this.packageName = packageName;

        return this;
    }

    public ModuleRepositoryBuilder<E> setClassName(String className) {
        this.className = className;

        return this;
    }

    public ModuleRepositoryBuilder<E> setCurrentType(Enum<E> currentType) {
        this.currentType = currentType;

        return this;
    }

    public ModuleRepositoryBuilder<E> setModulePath(String modulePath) {
        this.modulePath = modulePath;

        return this;
    }

    public ModuleRepository<E> build() {
        ModuleRepository<E> moduleRepository = new SimpleModuleRepository<>(binder, modulePath, moduleFormat, currentType, className, packageName);

        moduleRepository.getModule();

        return moduleRepository;
    }

}