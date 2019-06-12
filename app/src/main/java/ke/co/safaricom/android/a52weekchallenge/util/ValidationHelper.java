package ke.co.safaricom.android.a52weekchallenge.util;

public class ValidationHelper {
    public static String validatePhoneNumber(String phoneNumber){
        if(phoneNumber.length()<1){
            return "Provide Phone Number";
        }
        else if(phoneNumber.length()<10){
            return "Incomplete phone number";
        }

        else if(phoneNumber.startsWith("+")&&phoneNumber.length()<13){
            return "Incomplete phone number";
        }

        else if(phoneNumber.startsWith("254")&&phoneNumber.length()<12){
            return "Incomplete phone number";
        }
        else if(phoneNumber.startsWith("0")&&phoneNumber.length()>10){
            return "Incomplete phone number";
        }
        else {
            return Constants.VALID_INPUT;
        }
    }

    public static String validateName(String name){
        if(name.length()<1){
            return "Provide name";
        }
        else if(name.length()<5){
            return "Name too short";
        }
        else{
            return Constants.VALID_INPUT;
        }

    }

    public static String validateOTP(String otp){
        if (otp.length()==0){
            return "Provide Verification Code";
        }
        else if(otp.length()<6){
            return "Incomplete verification Code";
        }
        else {
            return Constants.VALID_INPUT;
        }
    }
    public static String validateIdNumber(String idNumber){
        if(idNumber.length()<1){
            return "Provide ID Number / Passport";
        }
        if(idNumber.length()<4){
            return "Incomplete ID Number/Passport";
        }
        else{
            return Constants.VALID_INPUT;
        }

    }
    public static String validatePin(String pin){
        if(pin.length()<1){
            return "Provide PIN";
        }
        if(pin.length()<4){
            return "Pin should be four or more characters";
        }
        else{
            return Constants.VALID_INPUT;
        }

    }
}
