package code;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.util.ArrayList;

public class CreditCardOpenExpiryJagacy extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;
    private String[] readData;
    private String value = null;
    private String indicator = null;
    private String amount = null;
    private String date = null;

    private String repaymentDate = null;
    private String Arreramount = null;

    private String outstandingAmount = null;
    private String accountType = null;

    private ArrayList<String> accountInformation = null;

    public CreditCardOpenExpiryJagacy() throws JagacyException
    {
       // super("sessionA","host3270.absa.co.za",993,"IBM-3279-5-E",true);

        super("sessionA", "host3270.absa.co.za",993,"IBM-3279-5-E",true);

    }

    public boolean userLogin(String username,String password) throws JagacyException
    {
        waitForChange(10000);
        userIdRow = 22;
        userIdColumn = 26;
        this.writePosition(userIdRow, userIdColumn, "IMSV");
        this.writeKey(Key.ENTER);

        this.waitForChange(10000);
        userIdRow = 14;
        userIdColumn = 10;
        this.writePosition(userIdRow,userIdColumn,username);

        userIdRow = 16;
        userIdColumn = 11 ;
        this.writePosition(userIdRow, userIdColumn, password);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow =  23;
        userIdColumn = 22;
        message = this.readPosition(userIdRow,userIdColumn,40).trim();

        System.out.println("Error Message: " + message);
        if(message.equalsIgnoreCase("INCORRECT OR NO PASSWORD ENTERED.") || message.equalsIgnoreCase("USERID IS NOT DEFINED TO RACF.") || message.equalsIgnoreCase("Your USERID is already logged on.")) {
            return false;
        }else {

            userIdRow = 2;
            userIdColumn = 2;
            this.writePosition(userIdRow, userIdColumn, "/test mfs");
            this.writeKey(Key.ENTER);
            return true;
        }
    }

   public String CheckDM9Indicator(String userData, String accountNo) throws JagacyException {

       this.waitForChange(10000);
       userIdRow = 2;
       userIdColumn = 1;
       this.writePosition(userIdRow, userIdColumn, userData);
       this.writeKey(Key.ENTER);
       this.waitForChange(30000);

       userIdRow = 2;
       userIdColumn = 25;
       this.writePosition(userIdRow, userIdColumn, accountNo);
       this.writeKey(Key.ENTER);
       this.waitForChange(30000);

       userIdRow = 12;
       userIdColumn = 1;
       repaymentDate = this.readPosition(userIdRow, userIdColumn, 25).trim();


       userIdRow = 20;
       userIdColumn = 27;
       accountType = this.readPosition(userIdRow, userIdColumn, 47).trim();

       message = "account no: " + accountNo + " : " + repaymentDate + " :  " + accountType;

       return message;
   }

 }

