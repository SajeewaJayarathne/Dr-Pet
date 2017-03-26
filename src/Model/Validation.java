package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class Validation {

    //validate inputs
    //validate email
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validateEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    //validate city and country

    //validate nic
    public boolean validateNIC(String nic){

        if (nic.length()<10 || nic.length()>10){
            return false;
        } else {
            for (int digit = 0; digit < 9; digit++){
                String nicSubstring = nic.substring(digit, digit+1);
                if (!nicSubstring.matches("[-+]?\\d*\\.?\\d+")){
                    return false;
                }
            }

            if (!(nic.substring(9)=="V" && nic.substring(9)=="v")){
                return false;
            }
            return true;
        }
    }

    //validate phone number
    public boolean validatePhone(String phone){
        //validate phone numbers of format "1234567890"
        if (phone.matches("\\d{10}")) return true;

        //validating phone number with -, . or spaces
        else if(phone.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;

        //validating phone number with extension length from 3 to 5
        else if(phone.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;

        //validating phone number where area code is in braces ()
        else if(phone.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;

        //return false if nothing matches the input
        else return false;
    }
}
