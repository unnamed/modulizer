package team.unnamed.modulizer.universal.provider;

import team.unnamed.modulizer.universal.MinecraftVersion;
import team.unnamed.modulizer.universal.internal.Key;
import team.unnamed.modulizer.universal.type.TypeReference;

/**
 * This is the base model to be able to obtain everything about any binding result.
 * <p>
 * All information about an abstraction and its different implementations
 * will be stored here, for example:
 * - The instance of the {@linkplain TypeReference} to the abstraction.
 * - The {@linkplain Key}'s of the different implementations.
 *
 * @param <T> The type of the abstraction.
 */
public interface ModuleProvider<T> {

  /**
   * @return The not null instance of the {@linkplain TypeReference} to the abstraction.
   */
  TypeReference<T> getAbstraction();

  /**
   * Gets a key by the implementation identifier and the enum identifier.
   * <p>
   * NOTE: If the implementation identifier is null, will set to default value
   * and the key returned will be of the default implementation.
   *
   * @param implementationIdentifier The nullable identifier of the implementation.
   * @param version                  The version of the implementation.
   * @return An nullable instance of the corresponding {@linkplain Key} of
   * the implementation.
   */
  Key<T> getKey(String implementationIdentifier, MinecraftVersion version);

  /**
   * Gets an instance of the implementation gotten with the enum and implementation
   * identifiers.
   * <p>
   * NOTE: If the constructor identifier is null, will set to default value
   * and the instance returned will be of the empty constructor.
   *
   * @param version                  The version of the implementation.
   * @param implementationIdentifier The nullable identifier of the implementation.
   * @param constructorIdentifier    The constructor identifier of the implementation.
   * @param values                   The values to use for creating the instance.
   * @return An nullable instance of the abstraction with the corresponding implementation.
   */
  T getInstance(MinecraftVersion version,
                String implementationIdentifier,
                String constructorIdentifier,
                Object... values);

  /**
   * Gets an instance of the implementation with the default identifiers.
   *
   * @param version The version of the implementation.
   * @return An nullable instance of the abstraction with the corresponding implementation.
   */
  default T getInstance(MinecraftVersion version) {
    return getInstance(version, null, null);
  }

  /**
   * @param key The {@linkplain Key} of the implementation to do the register.
   */
  void registerVersion(Key<T> key);

  /**
   * Gets an {@linkplain Class} of the gotten implementation with the parameters provided..
   * <p>
   * See {@linkplain ModuleProvider#getKey} to understand how functions this method.
   *
   * @param implementationIdentifier The nullable identifier of the implementation.
   * @param version                  The version of the implementation.
   * @return An nullable instance of the {@linkplain Class} of the implementation.
   */
  default Class<? extends T> getImplementation(String implementationIdentifier, MinecraftVersion version) {
    return getKey(implementationIdentifier, version).getImplementation();
  }

}