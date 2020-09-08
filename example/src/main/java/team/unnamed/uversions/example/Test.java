package team.unnamed.uversions.example;

import team.unnamed.modulizer.bukkit.MinecraftVersion;
import team.unnamed.modulizer.bukkit.bind.VersionModuleBinder;

public class Test {

    public static void main(String[] args) {
        VersionModuleBinder binder = new VersionModuleBinder();

        binder.installModule(new TestModule());

        System.out.println(
                binder
                        .getProvider(Abstraction.class)
                        .getInstance(MinecraftVersion.v1_8_R1, null,"simple-constructor", "testing", 2)
                        .getName()
        );
    }

}