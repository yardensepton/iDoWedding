package Models.Music;

import Models.Person;

public class DJ extends Person {
    private String MusicalGenre;

    public DJ(String firstName, String lastName, String id,String MusicalGenre) throws Exception {
        super(firstName, lastName, id);
        this.MusicalGenre = MusicalGenre;
    }
    public DJ()  {
        super();
        this.MusicalGenre = null;
    }
    public void setMusicalGenre(String musicalGenre) {
        MusicalGenre = musicalGenre;
    }

    public String getMusicalGenre() {
        return MusicalGenre;
    }

    @Override
    public String toString() {
      return super.toString() +"\n"+ getMusicalGenre();
    }
}