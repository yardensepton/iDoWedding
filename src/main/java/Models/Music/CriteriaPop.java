package Models.Music;
import java.util.ArrayList;
import java.util.List;

public class CriteriaPop implements Criteria {

    public List<DJ> meetCriteria(List<DJ> djs) {
        List<DJ> popDj = new ArrayList<DJ>();

        for (DJ dj: djs ) {
            if(dj.getMusicalGenre().equalsIgnoreCase("POP")){
                popDj.add(dj);
            }
        }
        return popDj;
    }

    @Override
    public String toString() {
        return "pop";
    }
}


