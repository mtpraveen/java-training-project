
public class Autobase {
	private String a1,a2,a3;
	
	private int[] p;
	public void autobase(){
    p=new int [3];

		a1="vw polo";
		p[0]=100;
		a2="audi all road";
		p[1]=150;
		a3="volvo s40";
		p[2]=120;
		System.out.println("autobase:");
		System.out.println("1 "+a1+" "+p[0]+"$");
		System.out.println("2 "+a2+" "+p[1]+"$");
		System.out.println("3 "+a3+" "+p[2]+"$");
		
	}
	public int price(int l){
		int n= p[l-1]	;
		return n;
		
	}



}
