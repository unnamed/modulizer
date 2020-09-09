package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.internal.InternalModuleProvider;

public interface ModuleRepository<E extends Enum<E>> extends InternalModuleProvider<E> {

    SimpleModule<E> getModule();

    static <E extends Enum<E>> ModuleRepositoryBuilder<E> builder(ModuleBinder<E> binder) {
        return new ModuleRepositoryBuilder<>(binder);
    }

}