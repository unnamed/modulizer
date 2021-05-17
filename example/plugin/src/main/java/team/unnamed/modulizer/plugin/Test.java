package team.unnamed.modulizer.plugin;

import team.unnamed.modulizer.abstraction.TitleMessenger;
import team.unnamed.modulizer.universal.MinecraftVersion;
import team.unnamed.modulizer.universal.bind.ModuleBinder;
import team.unnamed.modulizer.universal.internal.SimpleModuleBinder;
import team.unnamed.modulizer.universal.internal.repository.ModuleRepository;

public class Test {

    public static void main(String[] args) {
        MinecraftVersion minecraftVersion = MinecraftVersion.v1_8_R3;

        ModuleBinder binder = new SimpleModuleBinder();

        ModuleRepository versionModuleRepository = ModuleRepository
                .builder(binder)
                .setClassName("VersionModule_%identifier%")
                .setCurrentVersion(minecraftVersion)
                .setPackageName("team.unnamed.modulizer.%identifier%")
                .build();

        versionModuleRepository
                .getProvider(TitleMessenger.class)
                .getInstance(minecraftVersion, null, "default", "asdasd")
                .sendTitle("asd", "asdasd", 2, 2, 2);

        versionModuleRepository
                .getProvider(TitleMessenger.class)
                .getInstance(minecraftVersion, null, "default-2", "asdasd", 100)
                .sendTitle("asd", "asdasd", 2, 2, 2);

        versionModuleRepository
                .getProvider(TitleMessenger.class)
                .getInstance(minecraftVersion, "asd", null)
                .sendTitle("asd", "asdasd", 2, 2, 2);
    }

}