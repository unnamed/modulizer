package team.unnamed.modulizer.v1_8_R3;

import team.unnamed.modulizer.abstraction.TitleMessenger;

public class Test2 implements TitleMessenger {
    @Override
    public void sendTitle(String player, String title, int fadeIn, int stay, int fadeOut) {
        System.out.println("test title");
    }

    @Override
    public void sendSubtitle(String player, String subtitle, int fadeIn, int stay, int fadeOut) {

    }

    @Override
    public void sendFullTitle(String player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {

    }
}