package app.entities;

public class Student {

    private String name;
    private String level;

    // Konstruktør til at initialisere en studerendes navn
    public Student(String name, String level) {
        this.name = name;
        this.level = level;
    }

    // Metode til at få den studerendes navn
    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    // Overskrivning af toString-metoden for at returnere navnet som en streng
    @Override
    public String toString() {
        return getName() + " der er " + getLevel().toLowerCase() + " i java.";
    }
}
