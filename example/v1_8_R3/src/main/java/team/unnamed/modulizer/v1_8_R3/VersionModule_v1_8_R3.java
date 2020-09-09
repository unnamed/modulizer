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
                .withDefaultConstructor()
                .defaultIdentifier()
                .withType(MinecraftVersion.v1_8_R3);
    }

}