package Models.WeddingCar;

public  abstract class CarDecorator implements IWeddingCar {
    protected IWeddingCar decoratedCar;

    public CarDecorator(){
        this.decoratedCar = null;
    }
    public CarDecorator(IWeddingCar decoratedCar){
        this.decoratedCar = decoratedCar;
    }

    public void setDecoratedCar(IWeddingCar decoratedCar){
        this.decoratedCar = decoratedCar;
    }

    public abstract String getName();
}
