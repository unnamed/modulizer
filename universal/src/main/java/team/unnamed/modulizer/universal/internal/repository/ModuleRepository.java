package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.SimpleModule;

public interface ModuleRepository<E extends Enum<E>> {

    SimpleModule<E> getModule();

    static <E extends Enum<E>> ModuleRepositoryBuilder<E> builder() {
        return new ModuleRepositoryBuilder<>();
    }

}