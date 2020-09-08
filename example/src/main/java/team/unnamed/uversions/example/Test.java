package team.unnamed.uversions.example;

import team.unnamed.modulizer.universal.bind.InternalModuleBinder;
import team.unnamed.modulizer.universal.internal.SimpleModuleBinder;

public class Test {

    public static void main(String[] args) {
        InternalModuleBinder binder = new SimpleModuleBinder();

        binder.installModule(new TestModule());

        System.out.println(
                binder
                        .getProvider(Abstraction.class, MinecraftVersion.v1_8_R1)
                        .getInstance(MinecraftVersion.v1_8_R1, null,"simple-constructor", "testing", 2)
                        .getName()
        );
    }

}