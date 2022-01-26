package DbConnenction;

import Models.Buffet.*;
import Models.Couple;
import Models.Invitation.Invitation;
import Models.Invitation.InvitationFactory;
import Models.Music.DJ;
import Models.Person;
import Models.Wedding;
import Models.WeddingCar.*;
import Models.WeddingHall.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class GetFromDB {

    private static ResultSet rs = null;
    public static ArrayList<Person> people = new ArrayList<>();
    public static ArrayList<WeddingHall> weddingHalls = new ArrayList<>();
    public static ArrayList<Wedding> weddings = new ArrayList<>();
    public static ArrayList<DJ> djs = new ArrayList<>();

    public static ArrayList<DJ> getAllDJsByGenre(String input) throws Exception {
        int index = 1;
        String sql = ("select people.first_name,people.last_name, people.id_from_user,musicalgenre.name as musical_genre\n" +
                "from(dj join people on dj.id=people.id)join musicalgenre on dj.genre_id = musicalgenre.id\n" +
                "where musicalgenre.name =?");
        ArrayList<DJ> djsByGenre = new ArrayList<>();
        rs = DBHelper.ReadDataAndInputFromUser(sql, index, input);
        while (rs.next()) {
            DJ dj = new DJ();
            dj.setId(rs.getString("id_from_user"));
            dj.setFirstName(rs.getString("first_name"));
            dj.setLastName(rs.getString("last_name"));
            dj.setMusicalGenre(rs.getString("musical_genre"));
            djsByGenre.add(dj);
        }
        DBHelper.CloseConnection();
        return djsByGenre;
    }

    public static ArrayList<DJ> getAllDJs() throws Exception {
        String sql = ("select people.first_name,people.last_name, people.id_from_user,musicalgenre.name as musical_genre\n" +
                "from(dj join people on dj.id=people.id)join musicalgenre on dj.genre_id = musicalgenre.id");
        rs = DBHelper.ReadData(sql);
        while (rs.next()) {
            DJ dj = new DJ();
            dj.setId(rs.getString("id_from_user"));
            dj.setFirstName(rs.getString("first_name"));
            dj.setLastName(rs.getString("last_name"));
            dj.setMusicalGenre(rs.getString("musical_genre"));
            djs.add(dj);
        }
        DBHelper.CloseConnection();
        return djs;
    }

    public static int getId(String idFromUser) throws Exception {
        int id = 0;
        String sql = ("select id from people where id_from_user= ' " + idFromUser + " ';");
        rs = DBHelper.ReadData(sql);
        while (rs.next()) {
            id = rs.getInt("id");
        }
        DBHelper.CloseConnection();
        return id;
    }


    public static ArrayList<Person> getAllPeople() throws Exception {
        String sql = ("select * from people;");
        rs = DBHelper.ReadData(sql);
        while (rs.next()) {
            String id = rs.getString("id_from_user");
            String lastName = rs.getString("last_name");
            String firstName = rs.getString("first_name");
            Person person = new Person(firstName, lastName, id);
            people.add(person);
        }
        DBHelper.CloseConnection();
        return people;
    }

    public static boolean isPersonExist(String id) throws Exception {
        int index = 1;
        String sql = ("select * from people where id_from_user=?;");
        rs = DBHelper.ReadDataAndInputFromUser(sql, index, id);
        if (rs.next()) {
            return true;
        }
        DBHelper.CloseConnection();
        return false;
    }

    public static int getCouplesNumber() throws Exception {
        String sql = ("SELECT MAX(id) FROM couples;");
        int num = 0;
        rs = DBHelper.ReadData(sql);
        while (rs.next()) {
            num = rs.getInt("MAX(id)");
        }
        DBHelper.CloseConnection();
        return num;
    }

    public static ArrayList<Couple> getAllCouples() throws Exception {
        int size = getCouplesNumber();
        ArrayList<Couple> couples = new ArrayList<>();
        for (int j = 1; j <= size; j++) {
            couples.add(getCouple(j));
        }
        return couples;
    }

    public static ArrayList<Wedding> getAllWeddings() throws Exception {
        int size = getWeddingsNumber();
        for (int j = 1; j <= size; j++) {
            weddings.add(getWedding(j));
        }
        return weddings;
    }

    public static int getWeddingsNumber() throws Exception {
        String sql = ("SELECT MAX(id) FROM wedding;");
        int num = 0;
        rs = DBHelper.ReadData(sql);
        while (rs.next()) {
            num = rs.getInt("MAX(id)");
        }
        DBHelper.CloseConnection();
        return num;
    }

    public static Person geCertainPerson(String id) throws Exception {
        Person person = new Person();
        int index = 1;
        String sql = ("select * from people where id=?;");
        rs = DBHelper.ReadDataAndInputFromUser(sql, index, id);
        if (rs.next()) {
            String Id = rs.getString("id_from_user");
            String lastName = rs.getString("last_name");
            String firstName = rs.getString("first_name");
            person.setLastName(lastName);
            person.setId(Id);
            person.setFirstName(firstName);
        }
        DBHelper.CloseConnection();
        return person;
    }

    public static int getInvitationID(Invitation invitation) throws Exception {
        String name = invitation.toString();
        int id = 0;
        String sql = ("select * from invitation_send_type where type = '" + name + "';");
        rs = DBHelper.ReadData(sql);
        if (rs.next()) {
            id = rs.getInt("id");
        }
        DBHelper.CloseConnection();
        return id;
    }

    public static ArrayList<String> getInvitationType() throws Exception {
        String sql = ("select * from invitation_send_type;");
        ArrayList<String> names = new ArrayList<>();
        rs = DBHelper.ReadData(sql);
        while (rs.next()) {
            names.add(rs.getString("type"));
        }
        DBHelper.CloseConnection();
        return names;
    }

    public static int getCarDecorationID(CarDecorator carDecorator) throws Exception {
        String name = carDecorator.getName();
        int id = 0;
        String sql = ("select * from decorates_type where name = '" + name + "';");
        rs = DBHelper.ReadData(sql);
        if (rs.next()) {
            id = rs.getInt("id");
        }
        DBHelper.CloseConnection();
        return id;
    }

    public static int getExtraID(BuffetDecorator buffetDecorator) throws Exception {
        String name = buffetDecorator.getName();
        int id = 0;
        String sql = ("select * from extras_table where name = '" + name + "';");
        rs = DBHelper.ReadData(sql);
        if (rs.next()) {
            id = rs.getInt("id");
        }
        DBHelper.CloseConnection();
        return id;
    }


    public static int getCarTypeID(Vehicle vehicle) throws Exception {
        String name = vehicle.getBrand();
        int id = 0;
        String sql = ("select * from car_types where name = '" + name + "';");
        rs = DBHelper.ReadData(sql);
        while (rs.next()) {
            id = rs.getInt("id");
        }
        DBHelper.CloseConnection();
        return id;
    }

    public static WeddingHall getCertainWeddingHall(int i) throws Exception {
        getAllWeddingHalls();
        for (int j = 0; j < weddingHalls.size(); j++) {
            if (j + 1 == i) {
                return weddingHalls.get(j);
            }
        }
        return null;
    }


    public static boolean checkIfHallIsFree(Wedding thisWedding, Person person, Person otherPerson) throws Exception {
        weddings = getAllWeddings();
        for (Wedding wedding : weddings) {
            if (wedding.equals(thisWedding)) {//if the wanted hall is occupied in this date
                AddToDB.deletePeople(person);
                AddToDB.deletePeople(otherPerson);
                throw new Exception("The chosen hall is occupied on this date.");
            }
        }
        return true;
    }


    public static int getWeddingHallSerialNumber(WeddingHall hall) throws Exception {
        weddingHalls = getAllWeddingHalls();
        for (int j = 0; j < weddingHalls.size(); j++) {
            if (hall.equals(weddingHalls.get(j))) {
                return j + 1;
            }
        }
        return 0;
    }

    public static DJ getCertainDJ(int id) throws Exception {
        if (id == 0) {
            return null;
        }
        DJ dj = new DJ();
        int index = 1;
        String sql = ("select people.first_name,people.last_name,people.id,people.id_from_user,musicalgenre.name as musical_genre\n" +
                "from(dj join people on dj.id=people.id)join musicalgenre on dj.genre_id = musicalgenre.id\n" +
                "where people.id=?;");
        rs = DBHelper.ReadDataAndInputFromUserInt(sql, index, id);
        while (rs.next()) {
            String Id = rs.getString("id_from_user");
            String lastName = rs.getString("last_name");
            String firstName = rs.getString("first_name");
            dj.setLastName(lastName);
            dj.setId(Id);
            dj.setFirstName(firstName);
            dj.setMusicalGenre(rs.getString("musical_genre"));
        }
        DBHelper.CloseConnection();
        return dj;
    }

    public static Invitation getCertainInvitation(int id) throws Exception {
        if (id == 0) {
            return null;
        }
        InvitationFactory invitationFactory = new InvitationFactory();
        Invitation invitation = null;
        int index = 1;
        String sql = ("select invitation_send_type.type as name \n" +
                "from(wedding join invitation_send_type on wedding.invitation=invitation_send_type.id)\n" +
                "where wedding.id=?;");
        rs = DBHelper.ReadDataAndInputFromUserInt(sql, index, id);
        while (rs.next()) {
            String name = rs.getString("name");
            invitation = invitationFactory.getInvitation(name);
        }
        DBHelper.CloseConnection();
        return invitation;
    }

    public static ArrayList<Vehicle> getVehicleTypes() throws Exception {
        Sedan sedan = new Sedan();
        Limousine limousine = new Limousine();
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        int index = 1;
        String sql = ("select * from car_types;");
        rs = DBHelper.ReadData(sql);
        while (rs.next()) {
            if (rs.getString("name").equals(sedan.toString())) {
                vehicles.add(sedan);
            }
            if (rs.getString("name").equals(limousine.toString())) {
                vehicles.add(limousine);
            }
        }
        DBHelper.CloseConnection();
        return vehicles;
    }

    public static Wedding getWedding(int serialNum) throws Exception {
        int index = 1;
        String sql = ("select * from wedding where id =?;");
        Wedding wedding = new Wedding();
        rs = DBHelper.ReadDataAndInputFromUserInt(sql, index, serialNum);
        if (rs.next()) {
            int guests = rs.getInt("num_guests");
            int weddingHallNum = rs.getInt("hall_id");
            int djNum = rs.getInt("dj_id");
            Date date = rs.getDate("date");
            LocalDate localD = date.toLocalDate();
            wedding.setWeddingDate(localD);
            wedding.setWeddingHall(getCertainWeddingHall(weddingHallNum));
            wedding.setNumOfGuests(guests,null,null);
            wedding.setSerialNumber(serialNum);
            DJ dj = getCertainDJ(djNum);
            wedding.setDj(dj);
            wedding.setCar(getAllDecoratedCarsPerWedding(serialNum));
            wedding.setMainDish(getAllDecoratedDishesPerWedding(serialNum, guests));
            wedding.setInvitation(GetFromDB.getCertainInvitation(serialNum));
        }
        DBHelper.CloseConnection();
        return wedding;
    }


    public static WeddingHall getWeddingHallByType(eTypesOfHall typesOfHall) throws Exception {
        weddingHalls = getAllWeddingHalls();
        for (WeddingHall hall : weddingHalls) {
            if (typesOfHall==eTypesOfHall.CombinedWeddingHall){
                return hall;
            }
            if (typesOfHall==eTypesOfHall.IndoorWeddingHall){
                return hall;
            }
            if (typesOfHall==eTypesOfHall.ResortWeddingHall){
                return hall;
            }
            if (typesOfHall==eTypesOfHall.ExteriorWeddingHall){
                return hall;
            }
            if (typesOfHall==eTypesOfHall.IntimateWeddingHall){
                return hall;
            }
        }
        return null;
    }

    public static ArrayList<WeddingHall> getAllWeddingHalls() throws Exception {
        String sql = ("select * from wedding_hall;");
        rs = DBHelper.ReadData(sql);
        while (rs.next()) {
            switch (rs.getString("type")) {
                case "Combined":
                    CombinedWeddingHall combinedWeddingHall = new CombinedWeddingHall();
                    combinedWeddingHall.setNameOfHall(rs.getString("name"));
                    combinedWeddingHall.setTypeCanopy(rs.getString("canopy"));
                    combinedWeddingHall.setAddress(rs.getString("address"));
                    combinedWeddingHall.setType();
                    combinedWeddingHall.setMaxNumOfGuests(rs.getInt("max_guests"));
                    weddingHalls.add(combinedWeddingHall);
                    break;
                case "Exterior":
                    ExteriorWeddingHall exteriorWeddingHall = new ExteriorWeddingHall();
                    exteriorWeddingHall.setNameOfHall(rs.getString("name"));
                    exteriorWeddingHall.setTypeCanopy(rs.getString("canopy"));
                    exteriorWeddingHall.setAddress(rs.getString("address"));
                    exteriorWeddingHall.setType();
                    exteriorWeddingHall.setMaxNumOfGuests(rs.getInt("max_guests"));
                    weddingHalls.add(exteriorWeddingHall);
                    break;
                case "Indoor":
                    IndoorWeddingHall indoorWeddingHall = new IndoorWeddingHall();
                    indoorWeddingHall.setNameOfHall(rs.getString("name"));
                    indoorWeddingHall.setAddress(rs.getString("address"));
                    indoorWeddingHall.setTypeCanopy(rs.getString("canopy"));
                    indoorWeddingHall.setType();
                    indoorWeddingHall.setMaxNumOfGuests(rs.getInt("max_guests"));
                    weddingHalls.add(indoorWeddingHall);
                    break;
                case "Intimate":
                    IntimateWeddingHall intimateWeddingHall = new IntimateWeddingHall();
                    intimateWeddingHall.setNameOfHall(rs.getString("name"));
                    intimateWeddingHall.setTypeCanopy(rs.getString("canopy"));
                    intimateWeddingHall.setAddress(rs.getString("address"));
                    intimateWeddingHall.setType();
                    intimateWeddingHall.setMaxNumOfGuests(rs.getInt("max_guests"));
                    weddingHalls.add(intimateWeddingHall);
                    break;
                case "Resort":
                    ResortWeddingHall resortWeddingHall = new ResortWeddingHall();
                    resortWeddingHall.setNameOfHall(rs.getString("name"));
                    resortWeddingHall.setAddress(rs.getString("address"));
                    resortWeddingHall.setTypeCanopy(rs.getString("canopy"));
                    resortWeddingHall.setType();
                    resortWeddingHall.setMaxNumOfGuests(rs.getInt("max_guests"));
                    weddingHalls.add(resortWeddingHall);
                    break;
            }
        }
        DBHelper.CloseConnection();
        return weddingHalls;
    }


    public static CarDecorator createCarDecorations(ResultSet s, Object vehicle) throws Exception {
        BalloonCarDecorator balloonCarDecorator = new BalloonCarDecorator(null);
        FlowersCarDecorator flowersCarDecorator = new FlowersCarDecorator(null);
        MrAndMrsCarDecorator mrAndMrsCarDecorator = new MrAndMrsCarDecorator(null);
        RibbonCarDecorator ribbonCarDecorator = new RibbonCarDecorator(null);

        if (s.getString("decoration").equals(balloonCarDecorator.getName())) {
            balloonCarDecorator.setDecoratedCar((IWeddingCar) vehicle);
            return balloonCarDecorator;
        }
        if (s.getString("decoration").equals(flowersCarDecorator.getName())) {
            flowersCarDecorator.setDecoratedCar((IWeddingCar) vehicle);
            return flowersCarDecorator;
        }
        if (s.getString("decoration").equals(mrAndMrsCarDecorator.getName())) {
            mrAndMrsCarDecorator.setDecoratedCar((IWeddingCar) vehicle);
            return mrAndMrsCarDecorator;
        } else {
            ribbonCarDecorator.setDecoratedCar((IWeddingCar) vehicle);
            return ribbonCarDecorator;
        }
    }

    public static CarDecorator getAllDecoratedCarsPerWedding(int weddingNum) throws Exception {
        int index = 1;
        String sql = ("select wedding_cars.wedding_car_num as car,car_types.name as type,decorates_type.name as decoration\n" +
                "from(wedding_cars join decorated_cars on decorated_cars.id = wedding_cars.wedding_car_num)join decorates_type on" +
                " decorated_cars.id_decoration = decorates_type.id join car_types on car_types.id= wedding_cars.id_type\n" +
                "where wedding_car_num=?;");
        rs = DBHelper.ReadDataAndInputFromUserInt(sql, index, weddingNum);
        Limousine limousine = new Limousine();
        Sedan sedan = new Sedan();
        CarDecorator carDecorator = null;
        while (rs.next()) {
            if (carDecorator instanceof CarDecorator) {
                carDecorator = createCarDecorations(rs, carDecorator);
            } else if (rs.getString("type").equals(limousine.getBrand())) {
                carDecorator = createCarDecorations(rs, limousine);
            } else if (rs.getString("type").equals(sedan.getBrand())) {
                carDecorator = createCarDecorations(rs, sedan);
            }
        }
        DBHelper.CloseConnection();
        return carDecorator;
    }

    public static BuffetDecorator getAllDecoratedDishesPerWedding(int weddingNum, int guests) throws Exception {
        int index = 1;
        String sql = ("select wedding_main_dishes.id as dish, extras_table.name as decoration\n" +
                "from(wedding_main_dishes join decorated_dishes on wedding_main_dishes.id = decorated_dishes.id)join extras_table on\n" +
                "decorated_dishes.id_addition = extras_table.id \n" +
                "where wedding_main_dishes.id=?;");
        rs = DBHelper.ReadDataAndInputFromUserInt(sql, index, weddingNum);
        MainDish mainDish = new MainDish(guests);
        BuffetDecorator buffetDecorator = null;
        while (rs.next()) {
            if (buffetDecorator instanceof BuffetDecorator) {
                buffetDecorator = createDishDecorations(rs, buffetDecorator);
            } else {
                buffetDecorator = createDishDecorations(rs, mainDish);
            }
        }
        DBHelper.CloseConnection();
        return buffetDecorator;
    }

    public static BuffetDecorator createDishDecorations(ResultSet s, Object mainDish) throws Exception {
        PureeBuffetDecorator pureeBuffetDecorator = new PureeBuffetDecorator(null);
        PulletSteakBuffetDecorator pulletSteakBuffetDecorator = new PulletSteakBuffetDecorator(null);
        RiceBuffetDecorator riceBuffetDecorator = new RiceBuffetDecorator(null);
        RisottoBuffetDecorator risottoBuffetDecorator = new RisottoBuffetDecorator(null);
        RoastSalmonBuffetDecorator roastSalmonBuffetDecorator = new RoastSalmonBuffetDecorator(null);
        SteakBuffetDecorator steakBuffetDecorator = new SteakBuffetDecorator(null);

        if (s.getString("decoration").equals(pureeBuffetDecorator.getName())) {
            pureeBuffetDecorator.setDecoratedDish((IBuffet) mainDish);
            return pureeBuffetDecorator;
        }
        if (s.getString("decoration").equals(pulletSteakBuffetDecorator.getName())) {
            pulletSteakBuffetDecorator.setDecoratedDish((IBuffet) mainDish);
            return pulletSteakBuffetDecorator;
        }
        if (s.getString("decoration").equals(riceBuffetDecorator.getName())) {
            riceBuffetDecorator.setDecoratedDish((IBuffet) mainDish);
            return riceBuffetDecorator;
        }
        if (s.getString("decoration").equals(risottoBuffetDecorator.getName())) {
            risottoBuffetDecorator.setDecoratedDish((IBuffet) mainDish);
            return risottoBuffetDecorator;
        }
        if (s.getString("decoration").equals(roastSalmonBuffetDecorator.getName())) {
            roastSalmonBuffetDecorator.setDecoratedDish((IBuffet) mainDish);
            return roastSalmonBuffetDecorator;
        } else {
            steakBuffetDecorator.setDecoratedDish((IBuffet) mainDish);
            return steakBuffetDecorator;
        }
    }


    public static Couple getCouple(int idCouple) throws Exception {
        int index = 1;
        int wedding = 0;
        String sql = ("select couples.id,people.first_name, people.last_name, people.id_from_user,wedding.id as wedding\n" +
                "from( people join couples on people.id = couples.first_spouse OR people.id = couples.second_spouse)join wedding on couples.wedding_id=wedding.id\n" +
                "where wedding.id =?;");
        rs = DBHelper.ReadDataAndInputFromUserInt(sql, index, idCouple);
        ArrayList<Person> peopleToBeCouple = new ArrayList<>();
        Couple couple = new Couple();
        while (rs.next()) {
            String id = rs.getString("id_from_user");
            wedding = rs.getInt("wedding");
            String lastName = rs.getString("last_name");
            String firstName = rs.getString("first_name");
            Person person1 = new Person(firstName, lastName, id);
            peopleToBeCouple.add(person1);
        }
        DBHelper.CloseConnection();
        return fromPeopleToCouple(peopleToBeCouple, couple, wedding);
    }

    public static Couple fromPeopleToCouple(ArrayList<Person> peopleToBeCouple, Couple couple, int wedding) throws Exception {
        couple.setFirstSpouse(peopleToBeCouple.get(0));
        couple.setSecondSpouse(peopleToBeCouple.get(1));
        couple.setWedding(getWedding(wedding));
        return couple;
    }


}
