package Models.Music;

public enum eMusic {

        Rock, Pop, MiddleEast;

    public String getMusic() {
        switch(this) {
            case Rock:
                return "rock";
            case Pop:
                return "pop";
            case MiddleEast:
                return "middle east";
            default:
                return null;
        }
    }


}
