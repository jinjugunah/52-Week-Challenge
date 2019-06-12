package ke.co.safaricom.android.a52weekchallenge.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Objects;

import ke.co.safaricom.android.a52weekchallenge.R;
import ke.co.safaricom.android.a52weekchallenge.view.splash.FragmentSplash;

public class NavigationHelper {
    public void startFragment(FragmentActivity activity,Fragment fragment,Fragment fragmenToHide){
        Objects.requireNonNull(activity).getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_up,R.animator.slide_up)
                .hide(fragmenToHide)
                .add(R.id.clMain, fragment)
                .addToBackStack(null)
                .commit();

    }

    public void startActivity(FragmentActivity activityFrom, AppCompatActivity activity){
        activityFrom.startActivity(new Intent(activityFrom,activity.getClass()));
        Objects.requireNonNull(activityFrom).finish();

    }
}
