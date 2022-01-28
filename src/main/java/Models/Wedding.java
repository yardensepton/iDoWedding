package Models;
import DbConnenction.AddToDB;
import Models.Buffet.BuffetDecorator;
import Models.Invitation.Invitation;
import Models.Music.DJ;
import Models.WeddingCar.CarDecorator;
import Models.WeddingHall.WeddingHall;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Wedding {
    private int serialNumber;
    private WeddingHall weddingHall;
    private CarDecorator car;
    private LocalDate weddingDate;
    private BuffetDecorator mainDish;
    private DJ dj;
    private int numOfGuests;
    private Invitation invitation;

    public Wedding() {
        this.serialNumber = 0;
        this.weddingHall = null;
        this.car = null;
        this.weddingDate = null;
        this.mainDish = null;
        this.invitation = null;
    }

    public Wedding(LocalDate date, WeddingHall weddingHall, int serialNumber) throws Exception {
        this.serialNumber = serialNumber;
        setWeddingHall(weddingHall);
        setWeddingDate(date);
        this.mainDish = null;
        this.car = null;
        this.invitation = null;

    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }

    public DJ getDj() {
        return dj;
    }

    public void setDj(DJ dj) {
        this.dj = dj;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public boolean setWeddingHall(WeddingHall wantedHall) throws Exception {
        if (wantedHall.getMaxNumOfGuests() < numOfGuests) {
            throw new Exception("The wanted hall can't have that amount of guests, choose another.\n");
        }
        this.weddingHall = wantedHall;
        return true;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public WeddingHall getWeddingHall() {
        return weddingHall;
    }

    public int getNumOfGuests() {
        return numOfGuests;
    }

    public boolean setNumOfGuests(int numOfGuests,Person person,Person otherPerson) throws Exception {
        if (numOfGuests <= 0 || numOfGuests > weddingHall.getMaxNumOfGuests()) {
            AddToDB.deletePeople(person);
            AddToDB.deletePeople(otherPerson);
            throw new Exception("Error in number of guests.");
        }
        this.numOfGuests = numOfGuests;
        return true;
    }


    public CarDecorator getCar() {
        return car;
    }

    public void setCar(CarDecorator car) {
        this.car = car;
    }

    public LocalDate getWeddingDate() {
        return weddingDate;
    }

    public boolean setWeddingDate(LocalDate date) throws Exception {
        LocalDate start = LocalDate.now();
        if (date.isBefore(start)) {
            throw new Exception("This date has already passed, choose another");
        }
        this.weddingDate = date;
        return true;
    }

    public BuffetDecorator getMainDish() {
        return mainDish;
    }

    public void setMainDish(BuffetDecorator mainDish) {
        this.mainDish = mainDish;
    }


    @Override
    public boolean equals(Object otherWedding) {
        if (!(otherWedding instanceof Wedding)) return false;
        Wedding wedding = (Wedding) otherWedding;
        return this.getWeddingHall().equals(((Wedding) otherWedding).getWeddingHall()) && this.getWeddingDate().equals(((Wedding) otherWedding).getWeddingDate());
    }


    @Override
    public String toString() {
        String formattedDate = weddingDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        StringBuffer sb = new StringBuffer("\n\tChosen wedding hall:\t" + weddingHall);
        sb.append("\tWedding date: " + formattedDate + "\n\tNumber of guests: " + numOfGuests);
        if (car == null) {
            sb.append("\n\tNo car was selected.");
        } else {
            sb.append("\n\tChosen car-> " + car);
        }
        if (dj == null) {
            sb.append("\n\tNo dj was selected.");
        } else {
            sb.append(".\n\tChosen DJ-> " + dj);
        }
        if (mainDish == null) {
            sb.append("\n\tNo main course was selected.");
        } else {
            sb.append(".\n\tChosen main course-> " + mainDish);
        }
        return sb.toString();
    }
}
