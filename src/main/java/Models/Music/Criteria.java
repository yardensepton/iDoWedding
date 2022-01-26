package Models.Music;

import java.util.List;

public interface Criteria {
    List<DJ> meetCriteria(List<DJ> djs);
    String toString();

}

