package Model;

/**
 * Created by Sajeewa on 5/1/2017.
 */
public class Owner {
    private String own_id;
    private String first_name;
    private String middle_initials;
    private String last_name;
    private String gender;
    private String street_no;
    private String street_name;
    private String city;
    private String state;
    private String country;
    private String email;
    private String address;
    private String name;
    private String phone;

    //create full owner
    public Owner(String own_id, String first_name, String middle_initials, String last_name, int gender, String street_no,
                 String street_name, String city, String state, String country, String email, String phone){

        this.setOwn_id(own_id);
        this.setFirst_name(first_name);
        this.setMiddle_initials(middle_initials);
        this.setLast_name(last_name);
        this.setGender(gender);
        this.setStreet_no(street_no);
        this.setStreet_name(street_name);
        this.setCity(city);
        this.setState(state);
        this.setCountry(country);
        this.setEmail(email);
        this.setPhone(phone);

        this.setAddress();
        this.setName();
    }

    public String getOwn_id() {
        return own_id;
    }

    public void setOwn_id(String own_id) {
        this.own_id = own_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_initials() {
        return middle_initials;
    }

    public void setMiddle_initials(String middle_initials) {
        this.middle_initials = middle_initials;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getStreet_no() {
        return street_no;
    }

    public void setStreet_no(String street_no) {
        this.street_no = street_no;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(){
        this.address = this.getStreet_no()+","+this.getStreet_name()+","+this.getCity()+","+this.getState()+","+this.getCountry();
    }
    public void setName(){
        this.name = this.getFirst_name()+" "+this.getMiddle_initials()+" "+this.getLast_name();
    }

}
