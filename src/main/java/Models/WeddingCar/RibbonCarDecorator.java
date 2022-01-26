package Models.WeddingCar;

public class RibbonCarDecorator extends CarDecorator{

    public RibbonCarDecorator(IWeddingCar decoratedCar) {
        super(decoratedCar);
    }

    @Override
    public String getName() {
        return "Ribbon";
    }

    public String toString(){
        return decoratedCar + (decoratedCar instanceof  Vehicle ? " decorated with:\nRibbon " : ", Ribbon");
    }
}
