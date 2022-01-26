package Models.WeddingCar;

public class FlowersCarDecorator extends CarDecorator {


    public FlowersCarDecorator(IWeddingCar decoratedCar) {
        super(decoratedCar);
    }

    @Override
    public String getName() {
        return "Flowers";
    }

    public String toString(){
        return decoratedCar + (decoratedCar instanceof  Vehicle ? " decorated with:\nFlowers " : " , Flowers");
    }
}
