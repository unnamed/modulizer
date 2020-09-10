package team.unnamed.modulizer.bukkit;

import org.bukkit.Bukkit;

import java.util.List;

public enum MinecraftVersion {

    v1_7_R1,
    v1_7_R2,
    v1_7_R3,
    v1_7_R4,
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

    public static MinecraftVersion getVersion(List<MinecraftVersion> possibleVersions) {
        MinecraftVersion minecraftVersion = valueOf(
                "v" + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1)
        );

        if (!possibleVersions.contains(minecraftVersion)) {
            throw new IllegalArgumentException("Your server version hasn't supported!");
        }

        return minecraftVersion;
    }

}