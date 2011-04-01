




public class Admin
{
	private int number;
	public Admin(int number) {
		
	}
	public int getNumber() {
		return number;
	}
	public boolean desision(boolean y) {
		if(y==true ){
				System.out.println("confirmed");
		}
		else System.out.println("refused for some reasons");		
		

		return y;
	}
	public void notes() {
		
		System.out.println("some notes");
	}
}