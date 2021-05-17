package team.unnamed.modulizer.universal.internal.repository;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.internal.InternalModuleProvider;
import team.unnamed.modulizer.universal.provider.ModuleProvider;

/**
 * This is the main module, where you must have an instance of this class,
 * to obtain the respective {@linkplain ModuleProvider}.
 * <p>
 * See {@linkplain InternalModuleProvider} to understand how to use this.
 */
public interface ModuleRepository extends InternalModuleProvider {

  /**
   * Gets the loaded module.
   * <p>
   * NOTE: This instance is only informative, you don't need to use it.
   *
   * @return A not null instance of the loaded module.
   */
  SimpleModule getModule();

  /**
   * Creates an instance of the builder, to select the properties of the repository.
   *
   * @param binder The instance of the {@linkplain ModuleBinder}.
   * @return An instance of the {@linkplain ModuleRepositoryBuilder}.
   */
  static ModuleRepositoryBuilder builder(ModuleBinder binder) {
    return new ModuleRepositoryBuilder(binder);
  }

}