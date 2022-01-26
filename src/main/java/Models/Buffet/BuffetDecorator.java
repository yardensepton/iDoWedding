package Models.Buffet;

public abstract class BuffetDecorator implements IBuffet {
    protected IBuffet decoratedDish;

    public BuffetDecorator(IBuffet decoratedDish) {
        this.decoratedDish = decoratedDish;
    }

    public void setDecoratedDish(IBuffet decoratedDish) {
        this.decoratedDish = decoratedDish;
    }

    public abstract String getName();
}
