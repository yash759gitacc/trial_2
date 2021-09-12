package trees;

import java.util.LinkedList;
import java.util.Scanner;
public class shobit {

	static int k =0 ;
	static int for_var = 0;
    static LinkedList var = new LinkedList();
	public static void main(String[] args) 
	{
		reader r1 = new reader();
		LinkedList l1 = new LinkedList();
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		while(!(s.equals("")))
		{
			k+=1;
			l1.add(s);
			s = sc.nextLine();			
		}
		Object[] ob = l1.toArray();
		int lineno = 0;
		System.out.println();
		for(Object o : ob)
		{
			lineno+=1;
			r1.read((String)o, lineno,k);
		}
		if(!(ob[k-1].equals("hlt")))
		{
			System.out.println("Error : no halt command at last");
		}
		for (int v=0 ;v<var.size();v++)
		{
			for_var++;
			String str = Integer.toBinaryString(for_var);
			var.set(v, "0".repeat(8-str.length())+str );
			System.out.println(var.get(v));
		}
	}
}
class reader
{
	public void read(String s,int lineno,int tline)
	{
		String[] splitted = s.split(" ");
		int strlength = splitted.length;
		if((splitted[0].equals("add")||splitted[0].equals("sub")||splitted[0].equals("mul")||splitted[0].equals("xor")||splitted[0].equals("and")||splitted[0].equals("or"))&&(strlength==4))
		{
			shobit.for_var++;
			String[] mc = {"9","9","9","9","9",""};
			type_A a1 = new type_A();
			mc[0] = a1.opcode(splitted) ;
			mc[1] = a1.unused(splitted) ;
			mc[2] = a1.reg1(splitted) ;
			mc[3] = a1.reg2(splitted) ;
			mc[4] = a1.reg3(splitted) ;
			int i =0;
			for(String str: mc)
			{	i+=1;
				if(str.equals("error: invalid instruction name")||str.equals("error: invalid resistor name"))
				{
					System.out.println(str+" in line "+lineno);
					break;
				}
				else if(i==5)
				{reader.prt(mc);}
			}
		}
		else if(s.equals("hlt"))
		{
			halt h1 =new halt(s,lineno);
			
			shobit.for_var++;
		}
		else
		{
			System.out.println("error: invalid code line (maybe extra letter/word or space error) in line "+ lineno);
		}
		
		
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
class halt
{
	halt(String s,int lineno)
	{
		int tline = shobit.k;
		if(lineno != tline)
		{
			System.out.println("Error : halt command should be only at last");
		}
		else
		{
			System.out.println("1001100000000000");
		}
	}
}
class regs
{
	public String convert(String a)
	{
		//System.out.println("regs com "+a);
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
















