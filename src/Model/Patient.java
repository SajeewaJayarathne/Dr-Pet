package Model;

/**
 * Created by Sajeewa on 5/1/2017.
 */
public class Patient {
    private String pet_id;
    private String name;
    private String owner_ID;
    private String gender;
    private String type;
    private String breed;
    private String date_of_birth;

    public Patient(String pet_id, String name, String owner_ID, int gender, String type, String breed, String date_of_birth){
        this.setPet_id(pet_id);
        this.setName(name);
        this.setOwner_ID(owner_ID);
        this.setGender(gender);
        this.setType(type);
        this.setBreed(breed);
        this.setDate_of_birth(date_of_birth);
    }


    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner_ID() {
        return owner_ID;
    }

    public void setOwner_ID(String owner_ID) {
        this.owner_ID = owner_ID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(int gender) {
        if (gender==0){
            this.gender = "Male";
        } else {
            this.gender = "Female";
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
