package team.unnamed.modulizer.universal.internal;

import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.type.TypeReference;

/**
 * This is the internal model used in a binder, it is the interface called when the bind process is finished.
 * <p>
 * NOTE: The use of this interface it's under your responsibility.
 * Not recommend modifying or implementing this interface elsewhere.
 */
public interface InternalModuleBinder extends ModuleBinder, InternalModuleProvider {

  /**
   * This method is called to cache the results of a bind.
   *
   * @param abstractionType The {@linkplain TypeReference} of the abstraction.
   * @param key             The {@linkplain Key} of the implementation.
   * @param <T>             The type of the abstraction.
   */
  <T> void set(TypeReference<T> abstractionType, Key<T> key);

}