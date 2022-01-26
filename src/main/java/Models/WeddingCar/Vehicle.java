package Models.WeddingCar;

public abstract class Vehicle implements IWeddingCar {
    protected String brand;
    public Vehicle() {
        setBrand();
    }

    public String getBrand() {
        return brand;
    }

    public abstract void setBrand( );


    @Override
    public String toString() {
        return brand  ;
    }

}
