package Models.Buffet;

public class SteakBuffetDecorator extends BuffetDecorator{
    public SteakBuffetDecorator(IBuffet decoratedDish) {
        super(decoratedDish);
    }

    @Override
    public String getName() {
        return "T-bone Steak topped with a simple creamy mushroom sauce";
    }

    public String toString(){
        return decoratedDish + (decoratedDish instanceof Buffet ?
                "Main Dish Includes:\nT-bone Steak topped with a simple creamy mushroom sauce " : ",\nT-bone Steak topped with a simple creamy mushroom sauce");

    }
}
