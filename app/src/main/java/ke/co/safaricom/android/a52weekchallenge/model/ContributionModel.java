package ke.co.safaricom.android.a52weekchallenge.model;

import com.google.gson.annotations.SerializedName;

public class ContributionModel {
    @SerializedName("confirmation_code")
    private String confirmationCode;
    @SerializedName("amount")
    private double amount;
    @SerializedName("date")
    private String date;

    public ContributionModel() {
    }

    public ContributionModel(String confirmationCode, double amount, String date) {
        this.confirmationCode = confirmationCode;
        this.amount = amount;
        this.date = date;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
