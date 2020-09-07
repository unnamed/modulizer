package team.unnamed.uversions.example;

public class Implementation implements Abstraction {

    private final String name;

    public Implementation() {
        name = "asdasd";
    }

    public Implementation(String name, int idk) {
        this.name = name + idk;
    }

    @Override
    public String getName() {
        return name;
    }

}