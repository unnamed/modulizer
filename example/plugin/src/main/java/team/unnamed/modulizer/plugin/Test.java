package team.unnamed.modulizer.plugin;

import team.unnamed.modulizer.abstraction.TitleMessenger;
import team.unnamed.modulizer.bukkit.MinecraftVersion;
import team.unnamed.modulizer.bukkit.bind.VersionModuleBinder;
import team.unnamed.modulizer.universal.internal.repository.ModuleRepository;

public class Test {

    public static void main(String[] args) {
        MinecraftVersion minecraftVersion = MinecraftVersion.v1_9_R2;

        VersionModuleBinder binder = new VersionModuleBinder();

        ModuleRepository<MinecraftVersion> versionModuleRepository = ModuleRepository
                .builder(binder)
                .setClassName("VersionModule_%identifier%")
                .setCurrentType(minecraftVersion)
                .setPackageName("team.unnamed.modulizer.%identifier%")
                .build();

        versionModuleRepository.getProvider(TitleMessenger.class)
                .getInstance(minecraftVersion, null, null)
                .sendTitle("pixel", "asdasd", 2, 2, 2);
    }

}