public class Admin{

	public boolean desision() {
		int n= (int) (Math.round(Math.random()))	;		
		boolean y;
		if(n==1 ){
			y=true;
	}
	else y=false;					
		if(y==true ){
				System.out.println("confirmed");
		}
		else System.out.println("refused for some reasons");
		return y;						
	}
	public boolean desisionTest() {				
		boolean y=true;
				//System.out.println("confirmed");
		return y;						
	}
	public void notes() {		
		System.out.println("some notes");
	}

}