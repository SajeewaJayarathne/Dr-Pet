package Model;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class Phone {

    private String clientID;
    private String phoneNumber;

    public Phone(String clientID, String phoneNumber){
        this.setClientID(clientID);
        this.setPhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}
