package team.unnamed.uversions.example;


import team.unnamed.modulizer.bukkit.MinecraftVersion;
import team.unnamed.modulizer.bukkit.module.VersionModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;

public class TestModule implements VersionModule {

    @Override
    public void configure(ModuleBinder<MinecraftVersion> binder) {
        binder
                .bind(Abstraction.class)
                .to(Implementation.class)
                .withDefaultConstructor()
                .withConstructor("simple-constructor", String.class, int.class)
                .defaultIdentifier()
                .withType(MinecraftVersion.v1_8_R1);
    }

}