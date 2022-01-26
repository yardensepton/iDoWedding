package Models.WeddingHall;

public enum eTypesOfHall {

        CombinedWeddingHall, ExteriorWeddingHall, IndoorWeddingHall, IntimateWeddingHall, ResortWeddingHall;

    public String getTypeHall() {
        switch(this) {
            case CombinedWeddingHall:
                return "Combined";
            case ExteriorWeddingHall:
                return "Exterior";
            case IndoorWeddingHall:
                return "Indoor";
            case IntimateWeddingHall:
                return "Intimate";
            case ResortWeddingHall:
                return "Resort";
            default:
                return null;
        }
    }


}
