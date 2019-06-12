package ke.co.safaricom.android.a52weekchallenge.util;

import android.content.Context;
import android.widget.Toast;

public class ToastService {
    public static void displayToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
