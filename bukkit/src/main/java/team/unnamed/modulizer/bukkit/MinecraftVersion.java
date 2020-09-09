package team.unnamed.modulizer.bukkit;

import org.bukkit.Bukkit;

public enum MinecraftVersion {

    v1_8_R1,
    v1_8_R2,
    v1_8_R3,
    v1_9_R1,
    v1_9_R2,
    v1_10_R1,
    v1_11_R1,
    v1_12_R1,
    v1_13_R1,
    v1_13_R2,
    v1_14_R1,
    v1_15_R1,
    v1_16_R1

    ;

    public static MinecraftVersion getVersion() {
        return MinecraftVersion.valueOf(
                Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1)
        );
    }

}