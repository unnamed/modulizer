package team.unnamed.modulizer.universal.bind;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.type.TypeReference;

/**
 * A simple model that represents the start binding.
 * <p>
 * NOTE: Using {@linkplain TypeReference} to allow generics in the abstraction.
 */
public interface ModuleBinder {

  /**
   * The method to start the binding of any abstraction allowing generics.
   *
   * @param abstractionType The {@linkplain TypeReference} of the abstraction.
   * @param <T>             The type of the abstraction.
   * @return An unmodifiable instance of {@linkplain ModuleBinderBuilder.Linkable} to do the binding.
   */
  <T> ModuleBinderBuilder.Linkable<T> bind(TypeReference<T> abstractionType);

  /**
   * The method to start the binding of any abstraction.
   *
   * @param abstractClass The class of the abstraction.
   * @param <T>           The type of the abstraction.
   * @return An unmodifiable instance of {@linkplain ModuleBinderBuilder.Linkable} to do the binding.
   */
  default <T> ModuleBinderBuilder.Linkable<T> bind(Class<T> abstractClass) {
    return bind(TypeReference.of(abstractClass));
  }

  /**
   * Installs another {@linkplain SimpleModule} in the same module.
   *
   * @param module An instance of {@linkplain SimpleModule} to install.
   */
  default void installModule(SimpleModule module) {
    module.configure(this);
  }

}