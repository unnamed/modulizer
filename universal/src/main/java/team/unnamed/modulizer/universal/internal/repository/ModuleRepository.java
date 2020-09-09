package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.internal.InternalModuleProvider;
import team.unnamed.modulizer.universal.provider.ModuleProvider;

/**
 * This is the main module, where you must have an instance of this class,
 * to obtain the respective {@linkplain ModuleProvider}.
 *
 * See {@linkplain InternalModuleProvider} to understand how to use this.
 *
 * @param <E> The type of the enum identifier.
 */
public interface ModuleRepository<E extends Enum<E>> extends InternalModuleProvider<E> {

    /**
     * Gets the loaded module.
     *
     * NOTE: This instance is only informative, you don't need to use it.
     *
     * @return A not null instance of the loaded module.
     */
    SimpleModule<E> getModule();

    /**
     * Creates an instance of the builder, to select the properties of the repository.
     *
     * @param binder The instance of the {@linkplain ModuleBinder}.
     * @param <E>    The type of the enum identifier.
     * @return An instance of the {@linkplain ModuleRepositoryBuilder}.
     */
    static <E extends Enum<E>> ModuleRepositoryBuilder<E> builder(ModuleBinder<E> binder) {
        return new ModuleRepositoryBuilder<>(binder);
    }

}