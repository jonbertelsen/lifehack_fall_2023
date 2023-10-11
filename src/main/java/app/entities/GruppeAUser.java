package app.entities;

public class GruppeAUser {
    private String name;

    public GruppeAUser(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return getName();
    }
}
