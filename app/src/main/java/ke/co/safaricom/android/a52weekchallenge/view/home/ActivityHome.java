package ke.co.safaricom.android.a52weekchallenge.view.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ke.co.safaricom.android.a52weekchallenge.R;

public class ActivityHome extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.clMain,FragmentHome.newInstance())
                    .commitNow();
        }
    }
}

