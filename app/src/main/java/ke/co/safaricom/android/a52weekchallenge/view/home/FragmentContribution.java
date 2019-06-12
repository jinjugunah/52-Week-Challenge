package ke.co.safaricom.android.a52weekchallenge.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ke.co.safaricom.android.a52weekchallenge.R;
import ke.co.safaricom.android.a52weekchallenge.model.ContributionModel;
import ke.co.safaricom.android.a52weekchallenge.util.GeneralHelper;
import ke.co.safaricom.android.a52weekchallenge.util.ToastService;
import ke.co.safaricom.android.a52weekchallenge.viewmodel.ContributionViewModel;

public class FragmentContribution extends Fragment {
    public static FragmentContribution newInstance(){
        return new FragmentContribution();
    }

    private View mainView;
    @BindView(R.id.etAmount)
    TextInputEditText etAmount;
    @BindView(R.id.tlAmountToStartWith)
    TextInputLayout tlAmount;
    ContributionViewModel mViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_contribute,container,false);
        ButterKnife.bind(this,mainView);
        return mainView;
    }

    @OnClick(R.id.btnStartContribution)
    void contribute(){
        if(etAmount.getText().toString().trim().length()==0){
            tlAmount.setError("Provide amount");
            tlAmount.requestFocus();
        }
        else if(Double.parseDouble(etAmount.getText().toString().trim())<50){
            tlAmount.setError("Amount must be more than 50 KES");
            tlAmount.requestFocus();
        }
        else{
            makeContribution();
        }
    }
    private void makeContribution(){
        if(mViewModel==null){
            mViewModel = ViewModelProviders.of(this).get(ContributionViewModel.class);
        }
        ContributionModel contributionModel = new ContributionModel(GeneralHelper.generateString(),Double.parseDouble(etAmount.getText().toString().trim()),"09-05-2019");
        mViewModel.makeContribution(contributionModel,getContext()).observe(this, response->{
            if(response){
                ToastService.displayToast(getContext(),"Contribution Successful");
                getActivity().recreate();
            }
        });
    }
}
