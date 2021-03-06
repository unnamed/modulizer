package team.unnamed.modulizer.universal;

import team.unnamed.modulizer.universal.bind.ModuleBinder;

/**
 * A simple interface that represents a configuration of the module
 * that you'll modulate.
 */
public interface SimpleModule {

  /**
   * The configuration of the module, you can bind all abstractions.
   * see {@linkplain ModuleBinder} to view all available methods to do
   * the bind.
   *
   * @param binder An unmodifiable instance of {@linkplain ModuleBinder}
   */
  void configure(ModuleBinder binder);

}