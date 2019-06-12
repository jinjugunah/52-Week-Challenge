package ke.co.safaricom.android.a52weekchallenge.view.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import ke.co.safaricom.android.a52weekchallenge.util.Constants;
import ke.co.safaricom.android.a52weekchallenge.util.MD5HashHelper;
import ke.co.safaricom.android.a52weekchallenge.util.NavigationHelper;
import ke.co.safaricom.android.a52weekchallenge.util.ToastService;
import ke.co.safaricom.android.a52weekchallenge.util.ValidationHelper;
import ke.co.safaricom.android.a52weekchallenge.view.home.ActivityHome;
import ke.co.safaricom.android.a52weekchallenge.viewmodel.AuthViewModel;

public class FragmentLogin extends Fragment {
    public static FragmentLogin newInstance(){
        return new FragmentLogin();
    }

    @BindView(R.id.etPhoneNumber)
    TextInputEditText etPhoneNumber;
    @BindView(R.id.etPin)
    TextInputEditText etPin;
    @BindView(R.id.tlPin)
    TextInputLayout tlPin;
    @BindView(R.id.tlPhoneNumber)
    TextInputLayout tlPhoneNumber;
    private View mainView;
    private AuthViewModel authViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this,mainView);
        return mainView;
    }

    @OnClick(R.id.btnLogin)
    public void login(View view){
        if(validateInput()){
            login();
        }
    }

    private void login(){
        if(authViewModel==null){
            authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        }
        authViewModel.login(etPhoneNumber.getText().toString().trim(), MD5HashHelper.md5(etPin.getText().toString().trim()),getContext()).observe(this,userAccountModel -> {
            if(userAccountModel!=null){
                new NavigationHelper().startActivity(getActivity(), new ActivityHome());
            }
            else {
                ToastService.displayToast(getContext(),getString(R.string.invalid_username_or_password));
            }
        });
    }
    private boolean validateInput(){
        boolean isValid = true;
        String validatePhoneNumber = ValidationHelper.validatePhoneNumber(etPhoneNumber.getText().toString().trim());
        String validatePin =  ValidationHelper.validatePin(etPin.toString().trim());
        if(!validatePhoneNumber.equals(Constants.VALID_INPUT)){
            tlPhoneNumber.setError(validatePhoneNumber);
            tlPhoneNumber.requestFocus();
            isValid = false;
        }
        else if(!validatePin.equals(Constants.VALID_INPUT)){
            tlPin.setError(validatePin);
            tlPin.requestFocus();
            isValid = false;
        }

        return  isValid;
    }
}
