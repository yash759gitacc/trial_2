package trees;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Scanner;

public class ch {

	static int x=0;
	static int k=0;
	static LinkedList aa = new LinkedList();
	public static void main(String[] args) 
	{
		readers r1 = new readers();
		LinkedList l1 = new LinkedList();
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String[] ob = new String[257];
		for(String se : ob)
		{
			se=sc.nextLine();
		}
		int lineno = 0;
		System.out.println();
		for(String o : ob)
		{
			lineno+=1;
			r1.read(o, lineno,k);
		}
	}

}
class readers
{
	public void read(String s,int lineno,int tline)
	{
		for(int i=0;i<s.length()-8;i++)
		{
			if((s.subSequence(i,i+8)).equals("mylable:"))
			{
				s=s.substring(i+8,s.length());
			}
		}
		while(s.substring(0,1).equals(" "))
		{
			s=s.substring(1, s.length());
		}
		String[] splitted = s.split("[ ]+");
		
		int strlength = splitted.length;
		for(String as : splitted)
		{
			if(as.equals("  "));
			//as="";
		}
		for (String ad : splitted)
		{
			System.out.println(ad);
		}
	}
}






////////////////////////////////////////////////////////////////////////////////////////











