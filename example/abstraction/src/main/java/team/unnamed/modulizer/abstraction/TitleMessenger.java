package team.unnamed.modulizer.abstraction;

public interface TitleMessenger {

    void sendTitle(String player, String title, int fadeIn, int stay, int fadeOut);

    void sendSubtitle(String player, String subtitle, int fadeIn, int stay, int fadeOut);

    void sendFullTitle(String player, String title, String subtitle, int fadeIn, int stay, int fadeOut);

}