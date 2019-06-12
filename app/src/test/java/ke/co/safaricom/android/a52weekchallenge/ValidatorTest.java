package ke.co.safaricom.android.a52weekchallenge;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.matchers.Not;

import ke.co.safaricom.android.a52weekchallenge.util.Constants;
import ke.co.safaricom.android.a52weekchallenge.util.ValidationHelper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class ValidatorTest {
    @Test
    public void validatePhoneNumberSuccess(){
        String actualresponse = ValidationHelper.validatePhoneNumber("+254728844034");
        String expectedResponse = Constants.VALID_INPUT;
       assertThat (actualresponse,is(expectedResponse));
    }
    @Test
    public void validatePhoneNumberFail(){
        String actualresponse = ValidationHelper.validatePhoneNumber("+25472884434");
        String expectedResponse = Constants.VALID_INPUT;
        assertThat (actualresponse, not(expectedResponse));
    }
}
