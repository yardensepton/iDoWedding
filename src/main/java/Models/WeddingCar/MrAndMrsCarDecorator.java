package Models.WeddingCar;

public class MrAndMrsCarDecorator extends CarDecorator{


    public MrAndMrsCarDecorator(IWeddingCar decoratedCar) {
        super(decoratedCar);
    }

    @Override
    public String getName() {
        return "Mr And Mrs Sign";
    }

    public String toString(){
        return decoratedCar + (decoratedCar instanceof  Vehicle ?
                " decorated with:\nMr And Mrs Sign " : ", Mr And Mrs Sign");
    }
}
