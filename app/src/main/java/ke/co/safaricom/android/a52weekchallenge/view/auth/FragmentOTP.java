package ke.co.safaricom.android.a52weekchallenge.view.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ke.co.safaricom.android.a52weekchallenge.R;

public class FragmentOTP extends Fragment {
    public static FragmentOTP newInstance(){
        return  new FragmentOTP();
    }

    private View mainView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_otp,container,false);
        return mainView;
    }
}
