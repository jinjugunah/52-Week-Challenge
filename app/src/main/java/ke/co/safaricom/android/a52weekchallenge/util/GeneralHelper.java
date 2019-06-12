package ke.co.safaricom.android.a52weekchallenge.util;

import java.util.UUID;

public class GeneralHelper {
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return  uuid.replaceAll("-","");
    }
}
