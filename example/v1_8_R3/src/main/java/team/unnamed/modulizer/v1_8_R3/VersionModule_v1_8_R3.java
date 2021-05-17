package team.unnamed.modulizer.v1_8_R3;

import team.unnamed.modulizer.abstraction.TitleMessenger;
import team.unnamed.modulizer.universal.MinecraftVersion;
import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;

public class VersionModule_v1_8_R3 implements SimpleModule {

    @Override
    public void configure(ModuleBinder binder) {
        binder.bind(TitleMessenger.class)
                .to(TitleMessenger1_8_R3.class)
                .withConstructor("default", String.class)
                .withConstructor("default-2", String.class, int.class)
                .defaultIdentifier()
                .withVersion(MinecraftVersion.v1_8_R3);

        binder.bind(TitleMessenger.class)
                .to(Test2.class)
                .withDefaultConstructor()
                .withIdentifier("asd")
                .withVersion(MinecraftVersion.v1_8_R3);
    }

}