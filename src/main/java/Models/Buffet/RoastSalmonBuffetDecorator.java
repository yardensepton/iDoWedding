package Models.Buffet;

public class RoastSalmonBuffetDecorator extends BuffetDecorator{
    public RoastSalmonBuffetDecorator(IBuffet decoratedDish) {
        super(decoratedDish);
    }

    @Override
    public String getName() {
        return "Roast Salmon sour with lemon and lime";
    }

    public String toString(){
        return decoratedDish + (decoratedDish instanceof Buffet ?
                "Main Dish Includes:\nRoast Salmon sour with lemon and lime " : ",\nroast Salmon sour with lemon and lime ");
    }
}
