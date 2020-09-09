package team.unnamed.modulizer.v1_9_R2;

import team.unnamed.modulizer.abstraction.TitleMessenger;

public class TitleMessenger1_9_R2 implements TitleMessenger {

    @Override
    public void sendTitle(String player, String title, int fadeIn, int stay, int fadeOut) {
        System.out.println("Sending a title to " + player + " with the message: " + title);
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