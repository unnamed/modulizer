package team.unnamed.modulizer.universal;

import team.unnamed.modulizer.universal.bind.ModuleBinder;

public interface SimpleModule<E extends Enum<E>> {

    void configure(ModuleBinder<E> binder);

}