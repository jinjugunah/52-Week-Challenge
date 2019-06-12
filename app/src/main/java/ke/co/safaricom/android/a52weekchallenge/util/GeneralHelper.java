package ke.co.safaricom.android.a52weekchallenge.util;

import java.util.UUID;

public class GeneralHelper {
    /**
     * This class generates random combination of text that is used to depict the
     * transaction code
     * @return code: the string to be used as transaction code
     */
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return  uuid.replaceAll("-","").substring(0,10);
    }
}
