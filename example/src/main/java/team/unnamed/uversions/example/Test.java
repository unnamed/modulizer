package team.unnamed.uversions.example;

import team.unnamed.uversions.MinecraftVersion;
import team.unnamed.uversions.bind.InternalVersionBinder;
import team.unnamed.uversions.internal.SimpleVersionBinder;

public class Test {

    public static void main(String[] args) {
        InternalVersionBinder binder = new SimpleVersionBinder();

        binder.installModule(new TestModule());

        System.out.println(
                binder
                        .getProvider(Abstraction.class)
                        .getInstance(MinecraftVersion.v1_8_R1, "simple-constructor", "testing", 2)
                        .getName()
        );
    }

}