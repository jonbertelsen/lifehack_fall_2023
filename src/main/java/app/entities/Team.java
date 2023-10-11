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
        // Her tilføjes en række studerendes navne til listen
    }

    // Overskrivning af toString-metoden for at returnere klassebetegnelsen som en streng
    @Override
    public String toString() {
        return getTeamName();
    }
}
