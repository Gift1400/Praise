package util;

import org.apache.commons.validator.routines.EmailValidator;

public class Helper {
        public static boolean isNullOrEmpty(String str){
            if((str == null) || (str.isEmpty())){
                return true ;
            }
            return false;
        }

        public static boolean isValidEmail(String email){
            EmailValidator validate = EmailValidator.getInstance();
            return validate.isValid(email);
        }
    }

