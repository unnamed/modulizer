package team.unnamed.modulizer.v1_9_R2;

import team.unnamed.modulizer.abstraction.TitleMessenger;
import team.unnamed.modulizer.bukkit.MinecraftVersion;
import team.unnamed.modulizer.bukkit.module.VersionModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;

public class VersionModule_v1_9_R2 implements VersionModule {

    @Override
    public void configure(ModuleBinder<MinecraftVersion> binder) {
        binder
                .bind(TitleMessenger.class)
                .to(TitleMessenger1_9_R2.class)
                .withDefaultConstructor()
                .defaultIdentifier()
                .withType(MinecraftVersion.v1_9_R2);
    }

}