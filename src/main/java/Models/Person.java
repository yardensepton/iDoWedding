package Models;


public class Person {
    private String firstName;
    private String lastName;
    private String id;

    public Person(String firstName, String lastName, String id) throws Exception {
        setFirstName(firstName);
        setLastName(lastName);
        setId(id);
    }

    public Person() {
        this.id = null;
        this.firstName = null;
        this.lastName = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (firstName.isEmpty()) {
            throw new Exception("Missing field - first name");
        }
        if (!(firstName.matches("^[ A-Za-z]+$"))) throw new Exception("Error in first name");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (lastName.isEmpty()) {
            throw new Exception("Missing field - last name");
        }
        if (!(lastName.matches("^[ A-Za-z]+$"))) throw new Exception("Error in last name");
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object otherPerson) {
        if (!(otherPerson instanceof Person)) return false;
        Person person = (Person) otherPerson;
        return id.equals(person.getId());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception {
        if (!(countDigits(id) == 9 && id.matches("^[0-9 ]+$"))) throw new Exception("Error in id");
        id =id.replaceAll("\\D+","");
        this.id = id;
    }

    public int countDigits(String id) {
        int digits = 0;
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) >= 48 && id.charAt(i) <= 57)
                digits++;
        }
        return digits;
    }

    @Override
    public String toString() {
        String last = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        String first = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        StringBuffer sb = new StringBuffer(last + " " + first + "(" + id + ")");
        return sb.toString();
    }

}




