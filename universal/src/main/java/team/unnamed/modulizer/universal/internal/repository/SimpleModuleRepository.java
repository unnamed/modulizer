package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.loader.ModuleLoader;
import team.unnamed.modulizer.universal.loader.SimpleModuleLoader;

import java.util.Optional;

public class SimpleModuleRepository<E extends Enum<E>> implements ModuleRepository<E> {

    private final ModuleBinder<E> binder;
    private final ModuleFormat moduleFormat;

    private final ModuleLoader<E> loader;

    private final String modulePath;
    private final String className;
    private final String packageName;

    public SimpleModuleRepository(ModuleBinder<E> binder,
                                  String modulePath,
                                  ModuleFormat moduleFormat,
                                  Enum<E> currentType,
                                  String className,
                                  String packageName) {
        this.binder = binder;
        this.moduleFormat = moduleFormat;
        this.modulePath = modulePath;
        this.loader = new SimpleModuleLoader<>(currentType);
        this.className = className;
        this.packageName = packageName;
    }

    @Override
    public SimpleModule<E> getModule() {
        Optional<SimpleModule<E>> moduleOptional = loader.getCurrentModule();

        if (!moduleOptional.isPresent()) {
            loader.loadCurrentModule(binder, modulePath, moduleFormat, className, packageName);

            return getModule();
        }

        return moduleOptional.get();
    }

}