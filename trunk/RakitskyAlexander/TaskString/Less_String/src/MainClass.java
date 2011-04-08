import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 */

/**
 * @author epam0003
 *
 */
public class MainClass {
	private StringTokenizer thisIsString;
	/**
	 * @return the thisIsString
	 */
	public StringTokenizer getThisIsString() {
		return thisIsString;
	}
	/**
	 * @param thisIsString the thisIsString to set
	 */
	public void setThisIsString(StringTokenizer thisIsString) {
		this.thisIsString = thisIsString;
	}
	public void ExempleFirst(StringTokenizer thisIsString)
	{
		System.out.println("This is first exemple:");
		List<String> strEquals = new ArrayList();
		String tempStr,tempStrEquals;
		boolean eq = false;
		while(thisIsString.hasMoreTokens())
		{
			tempStr=thisIsString.nextToken();
			if (tempStr.charAt(0)==tempStr.charAt(tempStr.length()-1))
			{
				if (strEquals.size()==0)
				{
					strEquals.add(tempStr);
					System.out.println(tempStr);
				}
				else
				{
					eq=true;
					for(int i=0;i<strEquals.size();i++)
						if (tempStr.equalsIgnoreCase(strEquals.get(i)))
							eq=false;
					if (eq)
					{
						strEquals.add(tempStr);
						System.out.println(tempStr);
					}	
				}
					
			}		
		}
	}
	
	public void ExempleSecond()
	{
		StringTokenizer strTok=thisIsString;
		System.out.println("This is second exemple:");
		String tempStr = null;
		int sum = 0;
		while(strTok.hasMoreTokens())
		{
			tempStr=strTok.nextToken();
			try
			{
				sum+=Integer.parseInt(tempStr);
			}
			catch (NumberFormatException e)
			{
				
			}
		}
		System.out.println(sum);
	}
	
	public void ExempleThird()
	{
		StringTokenizer strTok=thisIsString;
		System.out.println("This is third exemple:");
		String tempStr = null;
		String StrToStr = null ;
		String beginChar = "";
		while(strTok.hasMoreTokens())
		{
			tempStr=strTok.nextToken();
			beginChar=beginChar+tempStr.charAt(0);
			beginChar=beginChar.toUpperCase();
			if (StrToStr!=null)
				StrToStr=StrToStr+" "+beginChar+tempStr.substring(1);
			else StrToStr=" "+beginChar+tempStr.substring(1);
			//StrToStr=StrToStr+beginChar.toLowerCase()+tempStr.substring(1);
			beginChar="";
		}
		System.out.println(StrToStr);
	}
	
	
	public MainClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringTokenizer isString=new StringTokenizer("this  is string text, abbba - abbba 14 15 17","\n\t:.,- ");
		StringTokenizer isString1=new StringTokenizer("This  is string text, abbba - abbba 14 15 17","\n\t:.,- ");
		StringTokenizer isString2=new StringTokenizer("This  is string text, abbba - abbba 14 15 17","\n\t:.,- ");
		MainClass iAmObject=new MainClass();
		iAmObject.setThisIsString(isString);
		iAmObject.ExempleFirst(iAmObject.thisIsString);
		iAmObject.setThisIsString(isString1);
		iAmObject.ExempleSecond();
		iAmObject.setThisIsString(isString2);
		iAmObject.ExempleThird();
		Integer.parseInt("10");
	}

}
