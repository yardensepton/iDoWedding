package Models.Buffet;

public class Buffet implements IBuffet {
    protected int numOfDishes;


    public Buffet(int numOfDishes) {
        this.numOfDishes = numOfDishes;

    }

    public void setNumOfDishes(int numOfDishes) {
        this.numOfDishes = numOfDishes;
    }


    @Override
    public String toString() {
        return numOfDishes +" dishes will be served\n" ;
    }
}
