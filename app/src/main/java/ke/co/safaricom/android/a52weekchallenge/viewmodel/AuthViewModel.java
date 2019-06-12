package ke.co.safaricom.android.a52weekchallenge.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import org.json.JSONObject;

import ke.co.safaricom.android.a52weekchallenge.model.UserAccountModel;
import ke.co.safaricom.android.a52weekchallenge.util.Constants;

public class AuthViewModel extends ViewModel {
    MutableLiveData<Integer> accountStatus = new MutableLiveData<>();
    MutableLiveData<UserAccountModel> userAccountLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> registrationResponse = new MutableLiveData<>();

    public LiveData<Integer> checkAccountStatus(Context context){
        checkAccountState(context);
        return accountStatus;
    }

    public LiveData<UserAccountModel> login(String phoneNumber,String pin,Context context){
        performLogin(phoneNumber,pin,context);
        return userAccountLiveData;
    }

    public LiveData<Boolean> createAccount(UserAccountModel userAccountModel,Context context){

        accountCreation(userAccountModel,context);
        return registrationResponse;
    }

    private void performLogin(String phoneNumber,String password,Context context){
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE);
        String userAccount = pref.getString(Constants.USER_ACCOUNT_FLAG,null);
        try {
            JSONObject jsonObject = new JSONObject(userAccount);
            String userPhoneNumber = jsonObject.getString(Constants.PHONE_NUMBER_LABLE);
            String userPin = jsonObject.getString(Constants.PIN_LABEL);

            if(phoneNumber.equals(userPhoneNumber)&&userPin.equals(password)){
                userAccountLiveData.setValue(new Gson().fromJson(jsonObject.toString(),UserAccountModel.class));
            }
            else {
                userAccountLiveData.setValue(null);
            }

        }
        catch (Exception ex){
           userAccountLiveData.setValue(null);
        }

        }

    private void accountCreation(UserAccountModel userAccountModel,Context context){
        try {
            JSONObject jsonObject = new JSONObject(new Gson().toJson(userAccountModel, UserAccountModel.class));
            SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE).edit();
            editor.putString(Constants.USER_ACCOUNT_FLAG,jsonObject.toString());
            editor.apply();
            registrationResponse.setValue(true);
        }
        catch (Exception ex){
            ex.printStackTrace();
            registrationResponse.setValue(false);
        }

    }

    private void checkAccountState(Context context){
        SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE);
        String userAccount = pref.getString(Constants.USER_ACCOUNT_FLAG,null);
        if(userAccount==null){
            accountStatus.setValue(Constants.STATUS_NOT_REGISTERED);
        }else{
            try{
                JSONObject jsonObject = new JSONObject(userAccount);

                if(jsonObject.getInt(Constants.LOGGED_IN_FLAG)==Constants.STATUS_NOT_LOGGED_IN){
                    accountStatus.setValue(Constants.STATUS_NOT_LOGGED_IN);
                }
                else if (jsonObject.getInt(Constants.LOGGED_IN_FLAG)==Constants.STATUS_LOCKED){
                    accountStatus.setValue(Constants.STATUS_LOCKED);
                }
                else if (jsonObject.getInt(Constants.LOGGED_IN_FLAG)==Constants.STATUS_LOGGED_IN){
                    accountStatus.setValue(Constants.STATUS_LOGGED_IN);
                }
            }
            catch (Exception ex){
                accountStatus.setValue(Constants.STATUS_NOT_REGISTERED);
            }
        }

    }


}
