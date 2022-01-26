package Models.Buffet;

public class PureeBuffetDecorator extends BuffetDecorator{
    public PureeBuffetDecorator(IBuffet decoratedDish) {
        super(decoratedDish);
    }

    @Override
    public String getName() {
        return "Butter-based mashed potatoes";
    }

    public String toString() {
        return decoratedDish + (decoratedDish instanceof Buffet ?
                "Main Dish Includes:\nButter-based mashed potatoes " : "\n,butter-based mashed potatoes");
    }
}
