package team.unnamed.modulizer.v1_8_R3;

import team.unnamed.modulizer.abstraction.TitleMessenger;

public class TitleMessenger1_8_R3 implements TitleMessenger {

    private final String name;
    private final int test;

    public TitleMessenger1_8_R3(String name) {
        this(name, 0);
    }

    public TitleMessenger1_8_R3(String name, int test) {
        this.name = name;
        this.test = test;
    }

    @Override
    public void sendTitle(String player, String title, int fadeIn, int stay, int fadeOut) {
        System.out.println("Sending a title to " + player + " with the message: " + title + " test " + name + " test2 " + test);
    }

    @Override
    public void sendSubtitle(String player, String subtitle, int fadeIn, int stay, int fadeOut) {
        System.out.println("Sending a title to " + player + " with the message: " + subtitle);
    }

    @Override
    public void sendFullTitle(String player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        System.out.println("Sending a title to " + player + " with the message: " + title + " and submessage: " + subtitle);
    }

}