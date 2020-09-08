package team.unnamed.uversions.example;


import team.unnamed.modulizer.universal.SimpleModule;
import team.unnamed.modulizer.universal.bind.ModuleBinder;

public class TestModule implements SimpleModule {

    @Override
    public void configure(ModuleBinder binder) {
        binder
                .bind(Abstraction.class, MinecraftVersion.class)
                .to(Implementation.class)
                .withDefaultConstructor()
                .withConstructor("simple-constructor", String.class, int.class)
                .defaultIdentifier()
                .withType(MinecraftVersion.v1_8_R1);
    }

}