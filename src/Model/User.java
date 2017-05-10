package Model;

import java.util.ArrayList;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class User {
    private HashPasswordModel hashPasswordModel;

    //user
    private String user_id;
    private String first_name;
    private String middle_initials;
    private String last_name;

    //personal
    private String gender;
    private String marital_status;
    private String street_no;
    private String street_name;
    private String city;
    private String state;
    private String country;
    private String email;
    private String nic;
    private String dob;

    //employment
    private String start_date;
    private float basic_salary;
    private String user_type;

    //login details
    private String hashPassword;
    private boolean is_active;

    //concatenated variables
    private String address;
    private String name;
    private ArrayList<String> phones;
    private String phone;


    //constructor
    public User(String user_id, String first_name, String middle_initials, String last_name, int gender,
                String marital_status, String street_no, String street_name, String city, String state, String country,
                String email, String nic, String dob, String start_date, float basic_salary,
                ArrayList<String> phones) {
        this.setUser_id(user_id);
        this.setFirst_name(first_name);
        this.setMiddle_initials(middle_initials);
        this.setLast_name(last_name);
        this.setGender(gender);
        this.setMarital_status(marital_status);
        this.setStreet_no(street_no);
        this.setStreet_name(street_name);
        this.setCity(city);
        this.setState(state);
        this.setCountry(country);
        this.setEmail(email);
        this.setNic(nic);
        this.setDob(dob);
        this.setStart_date(start_date);
        this.setBasic_salary(basic_salary);
        this.setPhones(phones);


        this.setAddress();
        this.setName();
        this.setPhone();
    }

    public User(String user_id, String user_type, String password, boolean is_active){
        this.setUser_id(user_id);
        this.setUser_type(user_type);
        hashPasswordModel = new HashPasswordModel();
        this.setHashPassword(hashPasswordModel.hashFunction(password));
        this.setIs_active(is_active);
    }
    //setters and getters


    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public float getBasic_salary() {
        return basic_salary;
    }

    public void setBasic_salary(float basic_salary) {
        this.basic_salary = basic_salary;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress() {
        this.address = this.getStreet_no()+","+this.getStreet_name()+","+this.getCity()+","+this.getState()+","+this.getCountry();
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = this.getFirst_name()+" "+this.getMiddle_initials()+" "+this.getLast_name();
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone() {
        phone = getPhones().get(0);
        for (int i = 1; i < getPhones().size(); i++){
            phone += ", "+ getPhones().get(i);
        }
    }

}
