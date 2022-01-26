package Models.Music;
import java.util.ArrayList;
import java.util.List;

public class CriteriaRock implements Criteria {


    public List<DJ> meetCriteria(List<DJ> djs) {
        List<DJ> RockDj = new ArrayList<DJ>();

        for (DJ dj: djs ) {
            if(dj.getMusicalGenre().equalsIgnoreCase("Rock")){
                RockDj.add(dj);
            }
        }
        return RockDj;
    }

    @Override
    public String toString() {
        return "rock";
    }

}

