package app.entities;

public class Student {

    private String name;

    // Konstruktør til at initialisere en studerendes navn
    public Student(String name) {
        this.name = name;
    }

    // Metode til at få den studerendes navn
    public String getName() {
        return name;
    }

    // Overskrivning af toString-metoden for at returnere navnet som en streng
    @Override
    public String toString() {
        return getName();
    }
}
