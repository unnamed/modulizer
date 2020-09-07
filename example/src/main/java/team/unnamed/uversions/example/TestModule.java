package team.unnamed.uversions.example;

import team.unnamed.uversions.MinecraftVersion;
import team.unnamed.uversions.VersionModule;
import team.unnamed.uversions.bind.VersionBinder;

public class TestModule implements VersionModule {

    @Override
    public void configure(VersionBinder binder) {
        binder
                .bind(Abstraction.class)
                .to(Implementation.class)
                .withDefaultConstructor()
                .withConstructor("simple-constructor", String.class, int.class)
                .withVersion(MinecraftVersion.v1_8_R1);
    }

}