package DbConnenction;
import Models.Buffet.BuffetDecorator;
import Models.Buffet.MainDish;
import Models.Couple;
import Models.Invitation.Invitation;
import Models.Music.DJ;
import Models.Person;
import Models.Wedding;
import Models.WeddingCar.CarDecorator;
import Models.WeddingCar.Vehicle;
import Models.WeddingHall.WeddingHall;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class AddToDB {


    public static boolean checkIfCanAddPerson(Person otherPerson) throws Exception {
        ArrayList<Person> allPeople = GetFromDB.getAllPeople();
        if (!allPeople.isEmpty()) {
            for (Person person : allPeople) {
                if (person.equals(otherPerson)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void addPeople(Person person) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        if (!checkIfCanAddPerson(person)){
            throw new Exception("One/all of the couple already exists");
        }
        String idFromUser = person.getId();
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String sql = "  INSERT INTO `i_do_weddings`.`people` (`id_from_user`,`first_name`,`last_name`) VALUES  ( ' " + idFromUser + " ', '" + firstName + " ',' " + lastName + " ' )";
        int num = DBHelper.WriteData(sql);
        DBHelper.CloseConnection();
        if (num == -1) {
            throw new Exception("The Person wasn't added successfully");
        }
    }

    public static void addCouple(Person person, Person otherPerson, WeddingHall hall, LocalDate date, int numGuests) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        int id = GetFromDB.getCouplesNumber() + 1;
        Wedding wedding = addWedding(hall, date, numGuests, id,person,otherPerson);
        if (wedding == null) {
            deletePeople(person);
            deletePeople(otherPerson);
            return;
        }
        Couple couple = new Couple();
        int id1 = GetFromDB.getId(person.getId());
        int id2 = GetFromDB.getId(otherPerson.getId());
        couple.setFirstSpouse(person);
        couple.setSecondSpouse(otherPerson);
        String sql = "INSERT INTO `i_do_weddings`.`couples` (`id`,`first_spouse`,`second_spouse`,`wedding_id`) VALUES (" + id + "," + id1 + "," + id2 + "," + wedding.getSerialNumber() + ");";
        couple.setSerialNumber(id);
        int num = DBHelper.WriteData(sql);
        DBHelper.CloseConnection();
        if (num == -1) {
            deletePeople(person);
            deletePeople(otherPerson);
            throw new Exception("The couple wasn't added successfully");
        } else {
            System.out.println("couple and wedding were added successfully");
        }
    }
    public static void addInvitationToWedding(Wedding wedding, Invitation invitation) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        int serialNumber = wedding.getSerialNumber();
        int invitationType = GetFromDB.getInvitationID(invitation);
        String sql = "UPDATE `i_do_weddings`.`wedding` SET `invitation` =" + invitationType + "  WHERE `id` = " + serialNumber + ";";
        int num = DBHelper.WriteData(sql);
        DBHelper.CloseConnection();
        if (num == -1) {
            throw new Exception("The invitation wasn't added successfully");
        }
    }


    public static Wedding addWedding(WeddingHall hall, LocalDate date, int numOfGuests, int id,Person person,Person otherPerson) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        Wedding thisWedding = new Wedding();
        if (!(thisWedding.setWeddingHall(hall) && thisWedding.setWeddingDate(date) && thisWedding.setNumOfGuests(numOfGuests,person,otherPerson))) {
            return null;
        }
        if (!GetFromDB.checkIfHallIsFree(thisWedding,person,otherPerson)) {
            return null;
        } else {
            int hallNum = GetFromDB.getWeddingHallSerialNumber(hall);
            Date date1 = java.sql.Date.valueOf(date);
            String sql = "INSERT INTO `i_do_weddings`.`wedding`\n" +
                    "(`id`,`hall_id`,`date`,`num_guests`)\n" +
                    "VALUES( " + id + "," + hallNum + " ,' " + date1 + " ' ," + numOfGuests + ");";
            thisWedding.setSerialNumber(id);
            int num = DBHelper.WriteData(sql);
            DBHelper.CloseConnection();
            if (num == -1) {
                throw new Exception("The wedding wasn't added successfully");
            }
            return thisWedding;
        }
    }

    public static void addDishToAllDishes(Wedding wedding) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        int serialNumber = wedding.getSerialNumber();
        String sql = "INSERT INTO `i_do_weddings`.`wedding_main_dishes` (`id`) VALUES(" + serialNumber + ");";
        int num = DBHelper.WriteData(sql);
        DBHelper.CloseConnection();
        if (num == -1) {
            throw new Exception("The dish wasn't added successfully");
        }
    }

    public static void addDishToWedding(Wedding wedding, MainDish mainDish, ArrayList<BuffetDecorator> decorations) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        addDishToAllDishes(wedding);
        addDecorationsToDish(wedding, decorations);
        int serialNumber = wedding.getSerialNumber();
        String sql = "UPDATE `i_do_weddings`.`wedding` SET `main_dish` =" + serialNumber + "  WHERE `id` = " + serialNumber + ";";
        int num = DBHelper.WriteData(sql);
        DBHelper.CloseConnection();
        if (num == -1) {
            throw new Exception("The dish wasn't added successfully");
        }
    }



    public static void addDecorationsToDish(Wedding wedding, ArrayList<BuffetDecorator> decorations) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        int serialNumber = wedding.getSerialNumber();
        for (BuffetDecorator decorator : decorations) {
            int decorationID = GetFromDB.getExtraID(decorator);
            String sql = "INSERT INTO `i_do_weddings`.`decorated_dishes`(`id`,`id_addition`)VALUES(" + serialNumber + "," + decorationID + ");";
            int num = DBHelper.WriteData(sql);
            DBHelper.CloseConnection();
            if (num == -1) {
                throw new Exception("The dish wasn't added successfully");
            }
        }
    }

    public static void addCarToAllCars(Wedding wedding, Vehicle vehicle) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        int serialNumber = wedding.getSerialNumber();
        int id = GetFromDB.getCarTypeID(vehicle);
        String sql = "INSERT INTO `i_do_weddings`.`wedding_cars` (`wedding_car_num`,`id_type`)VALUES(" + serialNumber + ", " + id + " );";
        int num = DBHelper.WriteData(sql);
        DBHelper.CloseConnection();
        if (num == -1) {
            throw new Exception("The car wasn't added successfully");
        }
    }

    public static void addCarToWedding(Wedding wedding, Vehicle vehicle, ArrayList<CarDecorator> decorations) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        addCarToAllCars(wedding, vehicle);
        addDecorationsToCar(wedding, decorations);
        int serialNumber = wedding.getSerialNumber();
        String sql = "UPDATE `i_do_weddings`.`wedding` SET `car` =" + serialNumber + " WHERE `id` = " + serialNumber + ";";
        int num = DBHelper.WriteData(sql);
        DBHelper.CloseConnection();
        if (num == -1) {
            throw new Exception("The car wasn't added successfully");
        }
    }

    public static void addDecorationsToCar(Wedding wedding, ArrayList<CarDecorator> decorations) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        int serialNumber = wedding.getSerialNumber();
        for (CarDecorator decorator : decorations) {
            int decorationID = GetFromDB.getCarDecorationID(decorator);
            String sql = "INSERT INTO `i_do_weddings`.`decorated_cars`\n" +
                    "(`id`,`id_decoration`)VALUES(" + serialNumber + "," + decorationID + ");";
            int num = DBHelper.WriteData(sql);
            DBHelper.CloseConnection();
            if (num == -1) {
                throw new Exception("The car wasn't added successfully");
            }
        }
    }


    public static void addDJToWedding(Wedding wedding, DJ dj) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        String djID = dj.getId();
        int id = GetFromDB.getId(djID);
        int num = wedding.getSerialNumber();
        String sql = "UPDATE `i_do_weddings`.`wedding` SET `dj_id` =" + id + "  WHERE `id` = " + num + ";";
        DBHelper.WriteData(sql);
        DBHelper.CloseConnection();
        if (num == -1) {
            throw new Exception("The dj wasn't added successfully");
        }
    }

    public static void deletePeople(Person person) throws Exception {
        if (!DBHelper.OpenConnection()) {
            throw new Exception("There is a connection problem");
        }
        String idFromUser = person.getId();
        String sql = "DELETE FROM `i_do_weddings`.`people` WHERE id_from_user=' " + idFromUser + " ' ;";
        int num = DBHelper.WriteData(sql);
        DBHelper.CloseConnection();
        if (num == -1) {
            throw new Exception("The Person wasn't deleted successfully");
        }
    }
}
