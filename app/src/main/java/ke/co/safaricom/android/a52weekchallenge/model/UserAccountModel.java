package ke.co.safaricom.android.a52weekchallenge.model;

import com.google.gson.annotations.SerializedName;

import ke.co.safaricom.android.a52weekchallenge.util.Constants;

public class UserAccountModel {
    @SerializedName(Constants.LOGGED_IN_FLAG)
    private int accountStatus;
    @SerializedName(Constants.FULL_NAME_LABEL)
    private String fullName;
    @SerializedName(Constants.PHONE_NUMBER_LABLE)
    private String phoneNumber;
    @SerializedName(Constants.PIN_LABEL)
    private String pin;

    public UserAccountModel() {
    }

    public UserAccountModel(int accountStatus, String fullName, String phoneNumber, String pin) {
        this.accountStatus = accountStatus;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.pin = pin;
    }

    public int getAccounStatus() {
        return accountStatus;
    }

    public void setAccounStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
