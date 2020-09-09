package team.unnamed.modulizer.universal.loader;

import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.internal.repository.ModuleFormat;

import java.util.Optional;

public interface ModuleLoader<E extends Enum<E>> {

    void loadCurrentModule(ModuleBinder<E> binder,
                           String modulePath,
                           ModuleFormat moduleFormat,
                           String className,
                           String packageName);

    Optional<SimpleModule<E>> getCurrentModule();

}