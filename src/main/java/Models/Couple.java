package Models;

public class Couple {
    private int serialNumber;
    private Person firstSpouse;
    private Person secondSpouse;
    private Wedding wedding;

    public Couple() {
        serialNumber = 0;
        this.firstSpouse = null;
        this.secondSpouse = null;
        this.wedding = null;
    }

    public Couple(Person firstSpouse, Person secondSpouse,int serialNumber) throws Exception {
        this.serialNumber = serialNumber;
        this.firstSpouse = firstSpouse;
        this.secondSpouse = secondSpouse;
        this.wedding = null;
    }

    public Wedding getWedding() {
        return wedding;
    }

    public void setWedding(Wedding wedding) {
        this.wedding = wedding;
    }

    public void setFirstSpouse(Person firstSpouse) {
        this.firstSpouse = firstSpouse;
    }


    public void setSecondSpouse(Person secondSpouse) {
        this.secondSpouse = secondSpouse;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return firstSpouse.toString() + " & " + secondSpouse.toString();
    }
}
