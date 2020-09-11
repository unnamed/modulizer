package team.unnamed.modulizer.v1_8_R3;

import team.unnamed.modulizer.abstraction.TitleMessenger;
import team.unnamed.modulizer.bukkit.MinecraftVersion;
import team.unnamed.modulizer.bukkit.module.VersionModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;

public class VersionModule_v1_8_R3 implements VersionModule {

    @Override
    public void configure(ModuleBinder<MinecraftVersion> binder) {
        binder.bind(TitleMessenger.class)
                .to(TitleMessenger1_8_R3.class)
                .withConstructor("default", String.class)
                .withConstructor("default-2", String.class, int.class)
                .defaultIdentifier()
                .withType(MinecraftVersion.v1_8_R3);

        binder.bind(TitleMessenger.class)
                .to(Test2.class)
                .withDefaultConstructor()
                .withIdentifier("asd")
                .withType(MinecraftVersion.v1_8_R3);
    }

}