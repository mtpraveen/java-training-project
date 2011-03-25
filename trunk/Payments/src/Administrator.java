public class Administrator implements IAccountManager, ICreditManager {
    private String name;
    private String surname;
    private int personalNo;

    public boolean blockCreditCard(long creditNo, String message){
        return false;
    }

    public boolean setAccount(Client client, Account account){
        return false;
    }

    public boolean setCreditCard(Account account, CreditCard creditCard){
        return false;
    }

    public boolean createAccount(long number, long amount){
        return false;
    }

    public boolean createCreditCard(long number, long validAmount, long credit){
        return false;
    }

	@Override
	public boolean setCreditCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createCreditCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean blockCreditCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setAccount() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createAccount() {
		// TODO Auto-generated method stub
		return false;
	}

}
