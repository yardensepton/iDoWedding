package Models.WeddingHall;

import java.util.Objects;

public abstract class WeddingHall {

    protected String nameOfHall;
    protected String typeCanopy;
    protected int maxNumOfGuests;
    protected String address;
    protected eTypesOfHall type;

    public WeddingHall() {
        this.nameOfHall = null;
    }

    public WeddingHall(String nameOfHall,String typeCanopy, String address,int maxNumOfGuests) {
        setMaxNumOfGuests(maxNumOfGuests);
        setNameOfHall(nameOfHall);
        setAddress(address);
        setTypeCanopy(typeCanopy);
        setType();
    }

    public eTypesOfHall getType() {
        return type;
    }

    public String getNameOfHall() {
        return nameOfHall;
    }

    public String getTypeCanopy() {
        return typeCanopy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setNameOfHall(String name) {
        this.nameOfHall =name;
    }
    public void setTypeCanopy(String typeCanopy) {
        this.typeCanopy =typeCanopy;
    }

    public void setType() {
        this.type = eTypesOfHall.CombinedWeddingHall;
    }
    public void setMaxNumOfGuests(int maxNumOfGuests) {
        this.maxNumOfGuests =maxNumOfGuests;
    }

    public int getMaxNumOfGuests() {
        return maxNumOfGuests;
    }

    public String getName() {
        StringBuffer sb = new StringBuffer(nameOfHall + " , " + type.getTypeHall() + "Address: " + address + "\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeddingHall)) return false;
        WeddingHall that = (WeddingHall) o;
        return getNameOfHall().equals(that.getNameOfHall());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameOfHall());
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(nameOfHall + " , " + type.getTypeHall() + "\nCanopy style: " + typeCanopy + "\nThe maximum number of guests is: " + maxNumOfGuests +
                "\nAddress: " + address + "\n");
        return sb.toString();
    }


}
