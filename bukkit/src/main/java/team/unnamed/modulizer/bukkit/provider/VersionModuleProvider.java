package team.unnamed.modulizer.bukkit.provider;

import team.unnamed.modulizer.bukkit.MinecraftVersion;
import team.unnamed.modulizer.universal.provider.SimpleModuleProvider;
import team.unnamed.modulizer.universal.type.TypeReference;

public final class VersionModuleProvider<T> extends SimpleModuleProvider<T, MinecraftVersion> {

    public VersionModuleProvider(TypeReference<T> abstractionType) {
        super(abstractionType);
    }

}