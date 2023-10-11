package app.entities;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String className = "Dat1 F23";
    private List<Student> listOfTeam = new ArrayList<>();

    public Team() {
        loadNames();
    }

    public String getTeamName() {
        return className;
    }

    public List<Student> getListOfTeam() {
        return listOfTeam;
    }

    private void loadNames() {
        listOfTeam.add(new Student("Ahmad Abdel Razak Hussein Alkaseb"));
        listOfTeam.add(new Student("Mustafa Altinkaya"));
        listOfTeam.add(new Student("Youssef Khaled Badran"));
        listOfTeam.add(new Student("Valdemar Degn Thyboe Christensen"));
        listOfTeam.add(new Student("Yuleisy Collazo Rojas"));
        listOfTeam.add(new Student("Sumaia El-Kalache"));
        listOfTeam.add(new Student("Patrick Fabrin"));
        listOfTeam.add(new Student("Mikkel Gold"));
        listOfTeam.add(new Student("David Ajken Hansen"));
        listOfTeam.add(new Student("Signe Kjær Brandt Hau"));
        listOfTeam.add(new Student("Lasse Kjær Hauerberg"));
        listOfTeam.add(new Student("Lauritz Max Hauge"));
        listOfTeam.add(new Student("Caroline Lykke Ainow Holmstrøm"));
        listOfTeam.add(new Student("Christian Høj"));
        listOfTeam.add(new Student("Anders Wade Jensen"));
        listOfTeam.add(new Student("Morten Bomholt Mikkelsen"));
        listOfTeam.add(new Student("Solomon Mwesigwa"));
        listOfTeam.add(new Student("Jacob Hess Pedersen"));
        listOfTeam.add(new Student("Nicolai Christian Dahl Pejtersen"));
        listOfTeam.add(new Student("Alexander Doctor Heyde Rasmussen"));
        listOfTeam.add(new Student("Nicolai Theis Rolin"));
        listOfTeam.add(new Student("Tobias Rossen"));
        listOfTeam.add(new Student("Mounir Khaled Abdel-Fattah Salem"));
        listOfTeam.add(new Student("Matthias Sigurdsson"));
        listOfTeam.add(new Student("Noah Agner Boecking Strudal"));
        listOfTeam.add(new Student("Ingrid Karen Svendsen"));
        listOfTeam.add(new Student("Mikail Cömert Turan"));
        listOfTeam.add(new Student("Nicklas Waldemar Seier Winther"));
    }

    @Override
    public String toString() {
        return getTeamName();
    }
}
