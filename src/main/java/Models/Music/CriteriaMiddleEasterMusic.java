package Models.Music;
import java.util.ArrayList;
import java.util.List;

public class CriteriaMiddleEasterMusic implements Criteria {

    public List<DJ> meetCriteria(List<DJ> djs) {
        List<DJ> MiddleEasterDj = new ArrayList<DJ>();

        for (DJ dj: djs ) {
            if(dj.getMusicalGenre().equalsIgnoreCase("Middle Eastern")){
                MiddleEasterDj.add(dj);
            }
        }
        return MiddleEasterDj;
    }

    @Override
    public String toString() {
        return "Middle Eastern";
    }
}

