package Models.WeddingHall;

public class CombinedWeddingHall extends WeddingHall {

    public CombinedWeddingHall() {
        super();
    }

    @Override
    public void setType() {
        this.type = eTypesOfHall.CombinedWeddingHall;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
