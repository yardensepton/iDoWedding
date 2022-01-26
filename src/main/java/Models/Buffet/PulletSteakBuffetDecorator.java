package Models.Buffet;

public class PulletSteakBuffetDecorator extends BuffetDecorator{

    public PulletSteakBuffetDecorator(IBuffet decoratedDish) {
        super(decoratedDish);
    }

    @Override
    public String getName() {
        return "Pullet Steak in Dijon mustard sauce and honey";
    }

    public String toString() {
        return decoratedDish + (decoratedDish instanceof Buffet ?
                "Main Dish Includes:\nPullet Steak in Dijon mustard sauce and honey" : ",\nPullet Steak in Dijon mustard sauce and honey");
    }
}
