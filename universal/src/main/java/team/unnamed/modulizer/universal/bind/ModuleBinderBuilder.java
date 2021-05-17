package team.unnamed.modulizer.universal.bind;

import team.unnamed.modulizer.universal.MinecraftVersion;
import team.unnamed.modulizer.universal.provider.ModuleProvider;

/**
 * This is the main model with which an abstraction is linked to its different
 * implementations, yes, implementations, this means that an abstraction can
 * be linked to more than one implementation, for that identifiers will be used.
 * Everything ends up being a {@linkplain ModuleProvider} with the respective
 * information collected in the link.
 * <p>
 * There are different options to create the {@linkplain ModuleProvider},
 * such as being able to store constructors with parameters in a cache and
 * then simply create instances with the methods that the {@linkplain ModuleProvider} can provide you.
 */
public interface ModuleBinderBuilder {

  String DEFAULT_NAME = "!!_DEFAULT_!!";

  /**
   * Get's and save the constructor gotten with the class parameters.
   * <p>
   * NOTE: This will be executed in run-time, so, if the constructor isn't found,
   * will throw an exception.
   *
   * @param identifier      The identifier of the constructor, that will be used to
   *                        get the constructor in the {@linkplain ModuleProvider}.
   * @param extraParameters The parameters to search the constructor in the bound
   *                        implementation.
   * @return The same mutable instance of {@linkplain ModuleBinderBuilder}.
   */
  ModuleBinderBuilder withConstructor(String identifier, Class<?>... extraParameters);

  /**
   * Get's and save the default constructor of the bound implementation.
   *
   * @return The same mutable instance of {@linkplain ModuleBinderBuilder}.
   */
  default ModuleBinderBuilder withDefaultConstructor() {
    return withConstructor(DEFAULT_NAME);
  }

  /**
   * Set's the identifier of the bound implementation, that will be used
   * to get the corresponding implementation in the {@linkplain ModuleProvider#getInstance}.
   * <p>
   * NOTE: Use only when you just have more than one implementation. If you only have one,
   * use the method {@linkplain ModuleBinderBuilder#defaultIdentifier()}.
   * <p>
   * Use this method when you have ready the binding.
   *
   * @param identifier The identifier of the bound implementation.
   * @return An unmodifiable instance of {@linkplain Qualified} to finish the bind.
   */
  Qualified withIdentifier(String identifier);

  /**
   * Set's the default identifier of the bound implementation.
   * <p>
   * NOTE: Use only when you just have one implementation and when you have ready
   * setup the binding.
   *
   * @return An unmodifiable instance of {@linkplain Qualified} to finish the bind.
   */
  default Qualified defaultIdentifier() {
    return withIdentifier(DEFAULT_NAME);
  }

  /**
   * This is the base model the bind starts with, it simply contains a method to
   * make the bind to an implementation.
   *
   * @param <T> The type of the abstraction.
   */
  interface Linkable<T> {

    /**
     * This method is used to bind an implementation.
     * <p>
     * NOTE: This not allows generics because is stupid.
     *
     * @param implementation The class of the implementation.
     * @return A mutable instance of {@linkplain ModuleBinderBuilder} to continue the binding.
     */
    ModuleBinderBuilder to(Class<? extends T> implementation);

  }

  /**
   * This model simply serves to qualify the binding, that's, to select
   * the type of enum that will serve as the identifier of the abstraction
   * with the implementation.
   */
  interface Qualified {

    /**
     * The method to select the identifier of the enum, this method should
     * always be called the binding.
     *
     * @param version The version that the implementation depends.
     */
    void withVersion(MinecraftVersion version);

  }

  /**
   * This is the final model, the actual structure of the {@linkplain ModuleBinderBuilder},
   * which should be implemented in case you want to create your own implementation of {@link ModuleBinderBuilder}.
   * Here all the methods will be implemented since it inherits from the other interfaces.
   *
   * @param <T> The type of the abstraction.
   */
  interface Partial<T> extends ModuleBinderBuilder, Linkable<T>, Qualified {
  }

}