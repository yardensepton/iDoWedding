package Models.Buffet;

public class RiceBuffetDecorator extends BuffetDecorator{
    public RiceBuffetDecorator(IBuffet decoratedDish) {
        super(decoratedDish);
    }

    @Override
    public String getName() {
        return "White rice with almonds and carrots";
    }

    public String toString() {
        return decoratedDish + (decoratedDish instanceof Buffet ?
                "Main Dish Includes:\nWhite rice with almonds and carrots " : ",\nwhite rice with almonds and carrots");
    }
}
