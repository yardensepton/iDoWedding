package Executors;


import DbConnenction.AddToDB;
import DbConnenction.GetFromDB;
import Models.Buffet.BuffetDecorator;
import Models.Buffet.MainDish;
import Models.Couple;
import Models.Invitation.*;
import Models.Music.*;
import Models.Person;
import Models.Wedding;
import Models.WeddingCar.CarDecorator;
import Models.WeddingCar.Vehicle;
import Models.WeddingHall.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Manager {
    private CriteriaPop pop = new CriteriaPop();
    private CriteriaRock rock = new CriteriaRock();
    private CriteriaMiddleEasterMusic middleEast = new CriteriaMiddleEasterMusic();
    private InvitationFactory invitationFactory = new InvitationFactory();

    public InvitationFactory getInvitationFactory() {
        return invitationFactory;
    }

    public CriteriaPop getPop() {
        return pop;
    }

    public CriteriaRock getRock() {
        return rock;
    }

    public CriteriaMiddleEasterMusic getMiddleEast() {
        return middleEast;
    }


    public boolean checkIfCanAddWedding(Person otherPerson1, Person otherPerson2, WeddingHall weddingHall, LocalDate date, int numOfGuests) throws Exception {
        AddToDB.addCouple(otherPerson1, otherPerson2, weddingHall, date, numOfGuests);
        return true;
    }

    public boolean addCouple(String firstName1, String lastName1, String id1, String firstName2, String lastName2, String id2, int numGuests, LocalDate date, eTypesOfHall weddingHall) throws Exception {
        if (id1.equals(id2)) throw new Exception("The couple has the same id");
        WeddingHall chosen = GetFromDB.getWeddingHallByType(weddingHall);
        Person otherPerson1 = new Person(firstName1, lastName1, id1);
        Person otherPerson2 = new Person(firstName2, lastName2, id2);
        checkIfCanAddPerson(otherPerson1, otherPerson2);
        checkIfCanAddWedding(otherPerson1, otherPerson2, chosen, date, numGuests);
        return true;
    }

    public boolean checkIfCanAddPerson(Person otherPerson1, Person otherPerson2) {
        try {
            AddToDB.addPeople(otherPerson1);
            AddToDB.addPeople(otherPerson2);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public void chooseDJ(Couple couple, DJ dj) throws Exception {
        ArrayList<Wedding> weddings = GetFromDB.getAllWeddings();
        for (Wedding wedding : weddings) {
            if (couple.getWedding().equals(wedding)) {
                AddToDB.addDJToWedding(wedding, dj);
                break;
            }
        }
    }

    public void chooseInvitation(Couple couple, Invitation invitation) throws Exception {
        ArrayList<Wedding> weddings = GetFromDB.getAllWeddings();
        for (Wedding wedding : weddings) {
            if (couple.getWedding().equals(wedding)) {
                AddToDB.addInvitationToWedding(wedding, invitation);
                break;
            }
        }
    }


    public void chooseCarDecorations(Couple couple, Vehicle vehicle, ArrayList<CarDecorator> decorations) throws Exception {
        ArrayList<Wedding> weddings = GetFromDB.getAllWeddings();
        for (Wedding wedding : weddings) {
            if (couple.getWedding().equals(wedding)) {
                AddToDB.addCarToWedding(wedding, vehicle, decorations);
                break;
            }
        }
    }

    public void chooseDishDecorations(Couple couple, MainDish mainDish, ArrayList<BuffetDecorator> decorations) throws Exception {
        ArrayList<Wedding> weddings = GetFromDB.getAllWeddings();
        for (Wedding wedding : weddings) {
            if (couple.getWedding().equals(wedding)) {
                mainDish.setNumOfDishes(wedding.getNumOfGuests());
                AddToDB.addDishToWedding(wedding, mainDish, decorations);
                break;
            }
        }
    }

}
