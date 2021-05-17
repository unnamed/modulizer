package team.unnamed.modulizer.v1_9_R2;

import team.unnamed.modulizer.abstraction.TitleMessenger;
import team.unnamed.modulizer.universal.MinecraftVersion;
import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;

public class VersionModule_v1_9_R2 implements SimpleModule {

    @Override
    public void configure(ModuleBinder binder) {
        binder.bind(TitleMessenger.class)
                .to(TitleMessenger1_9_R2.class)
                .withDefaultConstructor()
                .defaultIdentifier()
                .withVersion(MinecraftVersion.v1_9_R2);
    }

}