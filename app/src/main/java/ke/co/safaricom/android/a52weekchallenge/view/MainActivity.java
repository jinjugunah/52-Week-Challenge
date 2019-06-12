package ke.co.safaricom.android.a52weekchallenge.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

import ke.co.safaricom.android.a52weekchallenge.R;
import ke.co.safaricom.android.a52weekchallenge.view.auth.FragmentLogin;
import ke.co.safaricom.android.a52weekchallenge.view.splash.FragmentSplash;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.clMain, FragmentSplash.newInstance())
                    .commitNow();
        }
    }

}
