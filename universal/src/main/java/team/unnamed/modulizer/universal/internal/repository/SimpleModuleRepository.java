package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.internal.InternalModuleBinder;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.loader.ModuleLoader;
import team.unnamed.modulizer.universal.loader.SimpleModuleLoader;
import team.unnamed.modulizer.universal.provider.ModuleProvider;
import team.unnamed.modulizer.universal.type.TypeReference;
import team.unnamed.modulizer.universal.util.ValidateUtil;

import java.util.Optional;

public class SimpleModuleRepository<E extends Enum<E>> implements ModuleRepository<E> {

    private final InternalModuleBinder<E> binder;
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
        ValidateUtil.checkState(
                binder instanceof InternalModuleBinder,
                "Binder isn't an instance of InternalModuleBinder"
        );

        this.binder = (InternalModuleBinder<E>) binder;
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

    @Override
    public <T> ModuleProvider<T, E> getProvider(TypeReference<T> abstractType) {
        return binder.getProvider(abstractType);
    }

}