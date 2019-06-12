package ke.co.safaricom.android.a52weekchallenge.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ke.co.safaricom.android.a52weekchallenge.model.ContributionModel;
import ke.co.safaricom.android.a52weekchallenge.model.UserAccountModel;
import ke.co.safaricom.android.a52weekchallenge.util.Constants;

public class ContributionViewModel extends ViewModel {

    private MutableLiveData<Boolean> makeContributionResult = new MutableLiveData<>();
    private MutableLiveData<List<ContributionModel>> contributionModelListLiveData = new MutableLiveData<>();

    public LiveData<Boolean> makeContribution(ContributionModel contributionModel, Context context){

            createContribution(contributionModel, context);

        return makeContributionResult;
    }

    public LiveData<List<ContributionModel>> getContributions(Context context){
        queryContributions(context);
        return contributionModelListLiveData;
    }

    private void queryContributions(Context context){
        try {
            SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
            JSONArray jsonArray = new JSONArray(pref.getString(Constants.CONTRIBUTION_FLAG, ""));
            List<ContributionModel> contributionModels = new ArrayList<>();

            int arrayLength = jsonArray.length();

            for(int i=0;i<arrayLength;i++){
                ContributionModel contributionModel = new Gson().fromJson(jsonArray.get(i).toString(),ContributionModel.class);
                contributionModels.add(contributionModel);
            }
            contributionModelListLiveData.setValue(contributionModels);
        }
        catch (Exception ex){
            contributionModelListLiveData.setValue(null);
        }
    }

    private void createContribution(ContributionModel contributionModel,Context context){
        try {
            JSONObject jsonObject = new JSONObject(new Gson().toJson(contributionModel, ContributionModel.class));
            SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);

            JSONArray jsonArray = new JSONArray();
            try{
                 jsonArray = new JSONArray(pref.getString(Constants.CONTRIBUTION_FLAG,""));
            }catch (JSONException ex){
                ex.printStackTrace();
            }
            jsonArray.put(jsonObject);

            pref.edit().putString(Constants.CONTRIBUTION_FLAG,jsonArray.toString()).apply();
            makeContributionResult.setValue(true);
        }
        catch (JSONException ex){
            makeContributionResult.setValue(false);
        }
    }
}
