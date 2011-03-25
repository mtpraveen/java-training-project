import java.util.ArrayList;

public class Client {
    private String name;
    private String surname;
    private ArrayList<CreditCard> creditCards;
    private ArrayList<Account> accounts;

    public boolean payOrder(long orderNo, long price){
        return false;
    }

    public boolean makeTransfer(long accountNo, long amount){
        return false;
    }

    public boolean blockCreditCard(long creditNo, String reason){
        return false;
    }

    public boolean revokeAccount(long accountNo, String reason){
        return false;
    }

    public boolean addAmount(long amount){
        return false;
    }

    public void setName(String name){
    }

    public String getName(){
        return null;
    }

    public void setSurname(String surname){
    }

    public String getSurname(){
        return null;
    }

}
