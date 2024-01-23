import com.jagacy.util.JagacyException;
import org.testng.annotations.Test;

import java.io.IOException;

public class OpenExpiryDateTest {


    private String message;
    private boolean userLoggedIn = false;
    private CreditCardOpenExpiryJagacy debitOrderJagacy = null;

    @Test
    public void mainDebitOrder() throws JagacyException, InterruptedException, IOException
    {

        try {

            //Looping through the Excel Sheet

                    //Passing Jagacy Properties and Opening
                   // System.setProperty("sessionA.window", "true");
                    debitOrderJagacy  = new CreditCardOpenExpiryJagacy();
                    debitOrderJagacy.open();

                    //Login
                    userLoggedIn = debitOrderJagacy.userLogin("abks580", "jan@0002");

                    //
                    if(userLoggedIn){

                        message = debitOrderJagacy.CheckDM9Indicator("CMMQ ENQ", "4550270022776012");

                        System.out.println("y: "+ message);

                        debitOrderJagacy.close();
                    }else{
                           System.out.println("User not logged on");
                         debitOrderJagacy.close();
                    }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
