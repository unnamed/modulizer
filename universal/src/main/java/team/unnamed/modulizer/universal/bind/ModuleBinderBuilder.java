package team.unnamed.modulizer.universal.bind;

import team.unnamed.modulizer.universal.provider.ModuleProvider;

/**
 * This is the main model with which an abstraction is linked to its different
 * implementations, yes, implementations, this means that an abstraction can
 * be linked to more than one implementation, for that identifiers will be used.
 * Everything ends up being a {@linkplain ModuleProvider} with the respective
 * information collected in the link.
 *
 * There are different options to create the {@linkplain ModuleProvider},
 * such as being able to store constructors with parameters in a cache and
 * then simply create instances with the methods that the {@linkplain ModuleProvider} can provide you.
 *
 * @param <E> The type of the enum identifier.
 */
public interface ModuleBinderBuilder<E extends Enum<E>> {

    String DEFAULT_NAME = "!!_DEFAULT_!!";

    /**
     * Get's and save the constructor gotten with the class parameters.
     *
     * NOTE: This will be executed in run-time, so, if the constructor isn't found,
     *       will throw an exception.
     *
     * @param identifier        The identifier of the constructor, that will be used to
     *                          get the constructor in the {@linkplain ModuleProvider}.
     * @param extraParameters   The parameters to search the constructor in the bound
     *                          implementation.
     * @return The same mutable instance of {@linkplain ModuleBinderBuilder}.
     */
    ModuleBinderBuilder<E> withConstructor(String identifier, Class<?>... extraParameters);

    /**
     * Get's and save the default constructor of the bound implementation.
     *
     * @return The same mutable instance of {@linkplain ModuleBinderBuilder}.
     */
    default ModuleBinderBuilder<E> withDefaultConstructor() {
        return withConstructor(DEFAULT_NAME);
    }

    /**
     * Set's the identifier of the bound implementation, that will be used
     * to get the corresponding implementation in the {@linkplain ModuleProvider#getInstance}.
     *
     * NOTE: Use only when you just have more than one implementation. If you only have one,
     *       use the method {@linkplain ModuleBinderBuilder#defaultIdentifier()}.
     *
     *       Use this method when you have ready the binding.
     *
     * @param identifier The identifier of the bound implementation.
     * @return An unmodifiable instance of {@linkplain Qualified} to finish the bind.
     */
    Qualified<E> withIdentifier(String identifier);

    /**
     * Set's the default identifier of the bound implementation.
     *
     * NOTE: Use only when you just have one implementation and when you have ready
     *       setup the binding.
     *
     * @return An unmodifiable instance of {@linkplain Qualified} to finish the bind.
     */
    default Qualified<E> defaultIdentifier() {
        return withIdentifier(DEFAULT_NAME);
    }

    /**
     * This is the base model the bind starts with, it simply contains a method to
     * make the bind to an implementation.
     *
     * @param <T> The type of the abstraction.
     * @param <E> The type of the enum identifier.
     */
    interface Linkable<T, E extends Enum<E>> {

        /**
         * This method is used to bind an implementation.
         *
         * NOTE: This not allows generics because is stupid.
         *
         * @param implementation The class of the implementation.
         * @return A mutable instance of {@linkplain ModuleBinderBuilder} to continue the binding.
         */
        ModuleBinderBuilder<E> to(Class<? extends T> implementation);

    }

    /**
     * This model simply serves to qualify the binding, that's, to select
     * the type of enum that will serve as the identifier of the abstraction
     * with the implementation.
     *
     * @param <E> The type of the enum identifier.
     */
    interface Qualified<E extends Enum<E>> {

        /**
         * The method to select the identifier of the enum, this method should
         * always be called the binding.
         *
         * @param enumType The enum to identify the implementation.
         */
        void withType(E enumType);

    }

    /**
     * This is the final model, the actual structure of the {@linkplain ModuleBinderBuilder},
     * which should be implemented in case you want to create your own implementation of {@link ModuleBinderBuilder}.
     * Here all the methods will be implemented since it inherits from the other interfaces.
     *
     * @param <T> The type of the abstraction.
     * @param <E> The type of the enum identifier.
     */
    interface Partial<T, E extends Enum<E>> extends ModuleBinderBuilder<E>, Linkable<T, E>, Qualified<E> { }

}