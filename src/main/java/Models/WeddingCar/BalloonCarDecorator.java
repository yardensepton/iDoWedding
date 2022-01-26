package Models.WeddingCar;

public class BalloonCarDecorator extends CarDecorator{

    public BalloonCarDecorator(IWeddingCar decoratedCar) {
        super(decoratedCar);
    }

    @Override
    public String getName() {
        return "Balloon";
    }

    public String toString(){
        return decoratedCar + (decoratedCar instanceof  Vehicle ? " decorated with:\nBalloons " : ", Balloons");
    }
}
