package app.entities;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String className = "Dat1 F23"; // Klassebetegnelse
    private List<Student> listOfTeam = new ArrayList<>(); // Liste af studerende

    // Konstruktør til at initialisere holdet og tilføje studerende til listen
    public Team() {
        loadNames();
    }

    // Metode til at få holdets klassebetegnelse
    public String getTeamName() {
        return className;
    }

    // Metode til at få listen af studerende på holdet
    public List<Student> getListOfTeam() {
        return listOfTeam;
    }

    // En privat hjælpemetode til at indlæse studerendes navne og tilføje dem til listen
    private void loadNames() {
        //There's three level:  Begynder    Øvet    Hardcore
        listOfTeam.add(new Student("Ahmad Abdel Razak Hussein Alkaseb","Begynder"));
        listOfTeam.add(new Student("Mustafa Altinkaya","Øvet"));
        listOfTeam.add(new Student("Youssef Khaled Badran","Hardcore"));
        listOfTeam.add(new Student("Valdemar Degn Thyboe Christensen","Øvet"));
        listOfTeam.add(new Student("Yuleisy Collazo Rojas","Begynder"));
        listOfTeam.add(new Student("Sumaia El-Kalache","Hardcore"));
        listOfTeam.add(new Student("Patrick Fabrin","Øvet"));
        listOfTeam.add(new Student("Mikkel Gold","Hardcore"));
        listOfTeam.add(new Student("David Ajken Hansen","Øvet"));
        listOfTeam.add(new Student("Signe Kjær Brandt Hau","Begynder"));
        listOfTeam.add(new Student("Lasse Kjær Hauerberg","Begynder"));
        listOfTeam.add(new Student("Lauritz Max Hauge","Hardcore"));
        listOfTeam.add(new Student("Caroline Lykke Ainow Holmstrøm", "Begynder"));
        listOfTeam.add(new Student("Christian Høj", "Hardcore"));
        listOfTeam.add(new Student("Anders Wade Jensen", "Begynder"));
        listOfTeam.add(new Student("Morten Bomholt Mikkelsen", "Øvet"));
        listOfTeam.add(new Student("Solomon Mwesigwa", "Begynder"));
        listOfTeam.add(new Student("Jacob Hess Pedersen", "Hardcore"));
        listOfTeam.add(new Student("Nicolai Christian Dahl Pejtersen", "Begynder"));
        listOfTeam.add(new Student("Alexander Doctor Heyde Rasmussen", "Begynder"));
        listOfTeam.add(new Student("Nicolai Theis Rolin", "Begynder"));
        listOfTeam.add(new Student("Tobias Rossen", "Øvet"));
        listOfTeam.add(new Student("Mounir Khaled Abdel-Fattah Salem", "Øvet"));
        listOfTeam.add(new Student("Matthias Sigurdsson", "Hardcore"));
        listOfTeam.add(new Student("Noah Agner Boecking Strudal", "Hardcore"));
        listOfTeam.add(new Student("Ingrid Karen Svendsen", "Begynder"));
        listOfTeam.add(new Student("Mikail Cömert Turan", "Hardcore"));
        listOfTeam.add(new Student("Nicklas Waldemar Seier Winther", "Begynder"));
    }


    // Overskrivning af toString-metoden for at returnere klassebetegnelsen som en streng
    @Override
    public String toString() {
        return getTeamName();
    }
}
