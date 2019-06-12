package ke.co.safaricom.android.a52weekchallenge.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import ke.co.safaricom.android.a52weekchallenge.R;
import ke.co.safaricom.android.a52weekchallenge.util.Constants;
import ke.co.safaricom.android.a52weekchallenge.view.auth.FragmentLogin;
import ke.co.safaricom.android.a52weekchallenge.view.auth.FragmentOTP;
import ke.co.safaricom.android.a52weekchallenge.view.auth.FragmentPin;
import ke.co.safaricom.android.a52weekchallenge.view.auth.FragmentRegister;
import ke.co.safaricom.android.a52weekchallenge.view.home.ActivityHome;
import ke.co.safaricom.android.a52weekchallenge.viewmodel.AuthViewModel;

public class FragmentSplash extends Fragment {
    public static FragmentSplash newInstance(){
        return new FragmentSplash();
    }

    private View mainView;
    private AuthViewModel authViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_splash,container,false);

        return mainView;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkAccountStatus();
    }

    private void checkAccountStatus(){
        if(authViewModel==null){
            authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        }
        authViewModel.checkAccountStatus(getContext()).observe(this,accountStatus->{
            if(accountStatus== Constants.STATUS_LOGGED_IN){
                startActivity(new ActivityHome());
            }
            else if (accountStatus==Constants.STATUS_NOT_REGISTERED){
               startFragment(FragmentRegister.newInstance());

            }
            else if (accountStatus==Constants.STATUS_NOT_VERIFIED){
                startFragment(FragmentOTP.newInstance());

            }

            else if(accountStatus==Constants.STATUS_NOT_LOGGED_IN){
                startFragment(FragmentLogin.newInstance());
            }
            else if(accountStatus==Constants.STATUS_LOCKED){
                startFragment(FragmentPin.newInstance());
            }

        });
    }

    private void startFragment(Fragment fragment){
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_up,R.animator.slide_up)
                .hide(FragmentSplash.this)
                .add(R.id.clMain, fragment)
                .addToBackStack(null)
                .commit();

    }

    private void startActivity(AppCompatActivity activity){
        startActivity(new Intent(getActivity(),activity.getClass()));
        Objects.requireNonNull(getActivity()).finish();

    }
}
