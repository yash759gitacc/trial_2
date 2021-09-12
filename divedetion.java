package trees;
import java.util.Arrays;
import java.util.Scanner;
public class divedetion
{
	static boolean flag = true ;
	public static void main(String[] args) 
	{
		/// this is the main class
		//  1.Scan the input text file 
		//  2.Remove the empty lines from input 
		//  3.call the read() function from the reader class for every line of code
		//  4.have to give three input in read fun
		//  6.String , int line no. of given string , int total no. of lines
        //  7.String should not be empty or single word
		//  8.do not call read class after getting flag == false
		//  9.store all returened bin code in an array
		//  10.scan array for error
		//  11. if tere is an error in array print error in linr ___ for that line
		//  12. otherwise print all bin codes
		
		Scanner sc = new Scanner(System.in);
		String s = "";
		int a = 0 ;
		String [] inp = new String[257];
		while((flag==true)&& a<257)
		{
			s=sc.nextLine();
			while(s.substring(0,1).equals(" "))
			{
				s=s.substring(1, s.length());
			}
			inp[a] = s;
			a++;	
		}
		for(String d : inp)
		{
			System.out.println(d);
		}
		
		
		
		
	}

}

class readerf
{
	public void read(String s, int lineno , int totlines)
	{
		
		// this is the read function 
		// 1.break the given String s in an array named splitted 
		// 2.remove any kind of spaced/null element("  ")
		// 3.scan the array to check weather the command is valid
		// 4.convert the array to machine code using below classes
		// 5.add machine code line array for_return
		// 6.turn flag into false if there is an error
		// 7.turn flag into false if "hlt" is commanded
		// 8.return flag o main class
		// 
//      ---------------------------------------------------------------      //
	
		
		
		
		
		
		
		
		
		
		
	}
	public static void prt(String[] p)
	{
		for(String i : p)
		{
			System.out.print(i);	
		}
		System.out.println();		
	}
}
class type_A
{
public String opcode(String[] a)
	{
		String opcode = "error: invalid instruction name";
		if(a[0].equals("add"))
		{
			opcode = "00000";
		}
		if(a[0].equals("sub"))
		{
			opcode = "00001";
		}
		if(a[0].equals("mul"))
		{
			opcode = "00110";
		}
		if(a[0].equals("xor"))
		{
			opcode = "01010";
		}
		if(a[0].equals("or"))
		{
			opcode = "01011";
		}
		if(a[0].equals("and"))
		{
			opcode = "01100";
		}
		return opcode ;
		
	}
	
public String reg1(String[] a)
	{
		String reg1 = "error" ;
		regs r1 = new regs();
		reg1 = r1.convert(a[1]);
		return (reg1);
	}
	
public String reg2(String[] a)
	{
		String reg2 = "error" ;
		regs r2 =new regs();
		reg2 = r2.convert(a[2]);
		return (reg2);
	}
 	
public String reg3(String[] a)
	{
		String reg3 = "error" ;
		regs r3 = new regs();
		reg3 = r3.convert(a[3]);
		return (reg3);
	}
	
public String unused(String[] a)
	{
		String unused = "00";
		return unused ;
	}
}

class type_B
{
public String reg(String[] a)
{
	regs r2 = new regs();
	String reg2 = r2.convert(a[1]);
	return (reg2);
}

public String immval(String[] a)
{
	
	String[] st = a[2].split("");	
	String[] ints = {"0","1","2","3","4","5","6","7","8","9"};
	if((st[0].equals("$"))&& (st.length>1))
	{
		st[0]="0";
		for(String s : st)
		{
			if(!(Arrays.asList(ints).contains(s)))
			{
				return ("error: invalid immval");
			}
		}
		int con = Integer.parseInt(a[2].substring(1));
		if(con>255||con<0)
		{
			return ("error: can not have immval >255 & <0");
		}
		String str = Integer.toBinaryString(con);
		String immval = ("0".repeat(8-str.length())+str);
		return immval;
	}
	else
	{
		return ("error: invalid immval");
	}
}

public String opcode(String[]a)
{
	String opcode = "error: invalid instruction name" ;
	if(a[0].equals("mov"))
	{
		opcode = "00010";
	}
	else if(a[0].equals("rs"))
	{
		opcode = "01000";
	}
	else if(a[0].equals("ls"))
	{
		opcode = "01001";	
	}
	return (opcode) ;
}
}

class type_C
{
public String opcode(String[] a)
{
	String opcode = "error: invalid instruction name";
	if(a[0].equals("mov"))
	{
		opcode = "00011";
	}
	if(a[0].equals("div"))
	{
		opcode = "00111";
	}
	if(a[0].equals("not"))
	{
		opcode = "01101";
	}
	if(a[0].equals("cmp"))
	{
		opcode = "01110";
	}
	return opcode ;
}

public String unused(String[] a)
{
	String unused ="00000";
	return unused;
}

public String reg1(String[] a)
{
	regs r1 = new regs();
	String reg1 = r1.convert(a[1]);
	return (reg1);
}

public String reg2(String[] a)
{
	regs r2 = new regs();
	String reg2 = r2.convert(a[2]);
	return (reg2);
}
}
class regs
{
public String convert(String a)
	{
		String reg = "error: invalid resistor name";
		if(a.equals("R0"))
		{
			reg="000";
		}
		else if(a.equals("R1"))
		{
			//System.out.println("hello");
			reg="001";
			
		}
		else if(a.equals("R2"))
		{
			reg="010";
		}
		else if(a.equals("R3"))
		{
			reg="011";
		}
		else if(a.equals("R4"))
		{
			reg="100";
		}
		else if(a.equals("R5"))
		{
			reg="101";
		}
		else if(a.equals("R6"))
		{
			reg="110";
		}
		else if(a.equals("FLAGS"))
		{
			reg="111";
		}
		return (reg);
	}
}


