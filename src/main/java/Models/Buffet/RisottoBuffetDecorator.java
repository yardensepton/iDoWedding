package Models.Buffet;

public class RisottoBuffetDecorator extends BuffetDecorator {
    public RisottoBuffetDecorator(IBuffet decoratedDish) {
        super(decoratedDish);
    }

    @Override
    public String getName() {
        return "Creamy risotto based on wild mushrooms and white wine";
    }

    public String toString() {
        return decoratedDish + (decoratedDish instanceof Buffet ?
                "Main Dish Includes:\nCreamy risotto based on wild mushrooms and white wine " : ",\ncreamy risotto based on wild mushrooms and white wine");
    }
}
