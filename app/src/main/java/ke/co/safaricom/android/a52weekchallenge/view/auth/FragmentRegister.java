package ke.co.safaricom.android.a52weekchallenge.view.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ke.co.safaricom.android.a52weekchallenge.R;
import ke.co.safaricom.android.a52weekchallenge.model.UserAccountModel;
import ke.co.safaricom.android.a52weekchallenge.util.Constants;
import ke.co.safaricom.android.a52weekchallenge.util.MD5HashHelper;
import ke.co.safaricom.android.a52weekchallenge.util.NavigationHelper;
import ke.co.safaricom.android.a52weekchallenge.util.ToastService;
import ke.co.safaricom.android.a52weekchallenge.util.ValidationHelper;
import ke.co.safaricom.android.a52weekchallenge.view.home.ActivityHome;
import ke.co.safaricom.android.a52weekchallenge.viewmodel.AuthViewModel;

public class FragmentRegister extends Fragment {
    public static FragmentRegister newInstance(){
        return new FragmentRegister();
    }

    private View mainView;
    @BindView(R.id.etFullName)
    TextInputEditText etFullName;
    @BindView(R.id.tlFullName)
    TextInputLayout tlFullName;
    @BindView(R.id.tlIdNumber)
    TextInputLayout tlIdNumber;
    @BindView(R.id.etIdNumber)
    TextInputEditText etIdNumber;
    @BindView(R.id.tlPhoneNumber)
    TextInputLayout tlPhoneNumber;
    @BindView(R.id.etPhoneNumber)
    TextInputEditText etPhoneNumber;

    @BindView(R.id.tlPin)
    TextInputLayout tlPin;

    @BindView(R.id.etPin)
    TextInputEditText etPin;

    @BindView(R.id.btnCreateAccount)
    Button btnCreateAccount;
    private AuthViewModel authViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_register,container,false);
        ButterKnife.bind(this,mainView);


        return mainView;
    }

    @OnClick(R.id.btnCreateAccount)
    void createAccount(View view){
        if(validateInput()){
            createAccount();
        }
    }


    private void createAccount(){
        if(authViewModel==null){
            authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        }

        UserAccountModel userAccountModel = new UserAccountModel(2,etFullName.getText().toString().trim(),etPhoneNumber.getText().toString().trim(), MD5HashHelper.md5(etPin.getText().toString().trim()));
        authViewModel.createAccount(userAccountModel,getContext()).observe(this,registrationResult->{
            if(registrationResult){
                ToastService.displayToast(getContext(),getString(R.string.registration_success_message));
                new NavigationHelper().startActivity(getActivity(),new ActivityHome());

            }
            else{
                ToastService.displayToast(getContext(),getString(R.string.registration_success_message));

            }
        });
    }

    private boolean validateInput(){
        boolean isValid = true;
        String validateName = ValidationHelper.validateName(etFullName.getText().toString().trim());
        String validateIdNumber = ValidationHelper.validateIdNumber(etIdNumber.getText().toString().trim());
        String validatePhoneNumber = ValidationHelper.validatePhoneNumber(etPhoneNumber.getText().toString().trim());
        String validatePin = ValidationHelper.validatePin(etPin.getText().toString().trim());

        if(!validateName.equals(Constants.VALID_INPUT)){
            tlFullName.setError(validateName);
            tlFullName.requestFocus();
            isValid = false;
        }
        else if(!validateIdNumber.equals(Constants.VALID_INPUT)){
            tlIdNumber.setError(validateIdNumber);
            tlIdNumber.requestFocus();
            isValid = false;
        }
        else if(!validatePhoneNumber.equals(Constants.VALID_INPUT)){
            tlPhoneNumber.setError(validatePhoneNumber);
            tlPhoneNumber.requestFocus();
            isValid = false;
        }
        else if(!validatePin.equals(Constants.VALID_INPUT)){
            tlPin.setError(validatePhoneNumber);
            tlPin.requestFocus();
            isValid = false;
        }

        return  isValid;


    }
}
