package za.ac.util;

import org.apache.commons.validator.routines.EmailValidator;
import java.util.regex.Pattern;


public class Helper {
        public static boolean isNullOrEmpty(String str){
            if((str == null) || (str.isEmpty())){
                return true ;
            }
            return false;
        }

    private static final String numberValidator = "^\\(?\\d{3}\\)?[-\\s.]?\\d{3}[-\\s.]?\\d{4}$";

        public static  boolean isValidNumber(String value){
            if(value == null){
                return false;
            }
            return Pattern.matches(numberValidator, value);
        }

        public static boolean isValidEmail(String email){
            EmailValidator validate = EmailValidator.getInstance();
            return validate.isValid(email);
        }
    }

