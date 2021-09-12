package trees;
import java.io.File;
import java.util.Scanner;
public class rough {

	public static void main(String[] args)//throws Exception
	{

		String[] input  = new String[5];
		int i = 0 ;
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine())
		{
			input[i]=sc.nextLine();
			i++;
			if(i==5)
			{
				break;
			}
		}
		for(String s : input)
		{
			System.out.println(s);
		}
		
		
		//int reg_1 = Integer.parseInt(mac_code.substring(10,13),2)+Integer.parseInt(mac_code.substring(13,16),2) ;
//		System.out.println(Integer.parseInt(mac_code,2));
//		System.out.println(mac_code.substring(5,6));
	
		//	System.out.println(mac_code.substring(10,13));
		
//		System.out.println(1&1);
//		System.out.println
//		System.out.println
		
		
		
	}

}

class CheckAP{
	public int emergency = 0;
	Helper hlp = new Helper();
	LinkedList head1 = new LinkedList();
	LinkedList head2 = new LinkedList();
	int l_no_i = 0;     // keeps track of the line number of input given in the array
	public int count_NH;     // counts the total number of inputs given as ahelper for this class being copied from the previous class
	public int count_v = 0;    // this is used to count the varible found in the code 
	String arr_err[] = new String[256];
	static String arr_mem[] = new String[256];
	int l_no_m = 0;    /// keeps track of the memory 
	int i = 0;
	boolean flag = true;
	String s;
	String a;
	public int count = 0;
	public String st_label = " ";  ///// this is used to identify that instruction inside the label is called
	public int ifordl;


	public static boolean checkName(String s) {
		char ch[] = s.toCharArray();
		for (char c : ch) {
			int t = (int)c;
			if ((64<t && t<91) || (96<t && t<123) || t==95 || (47<t && t<58)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
	public void check(String arr[]) {
		ifordl = arr[0].length()-1;
		if(arr[0].equals("var")) {
			if(arr.length == 2) { 
				if(checkName(arr[1])) {
					head1.insert(arr[1]);  /// inserting the varible_ name in the linked list
					count_v += 1;
				}else{
					System.out.printf("Error at Line no. %d"+st_label+": Invalid Declaration of variable name.", l_no_i);
					//System.out.println();
				}
			}else{
				System.out.printf("Error at Line no. %d"+st_label+": Invalid no of operands given for instruction type: Declaration of Variable.", l_no_i);
			}
		}else if(arr[0].equals("ld") || arr[0].equals("st")) {  //// this instruction loads the values in the registers
			count = 0;
			if(arr.length ==3){				
				if(hlp.checkRegisterName(arr[1].replaceAll("[^a-zA-Z0-9]", ""))) { /// requirement to check that register name is within 0-6
					Node temp = head1.start;
					i = 0;
					while(temp!=null) {
						count+=1; 
						if(arr[2].equals(temp.getData())){
							s = hlp.getOpcode(arr[0]);   //// REQUIREMENT OF SOMETHING WHICH CAN RETURN OPCODE OF INSTRUCTION
							a = Integer.toBinaryString(Character.getNumericValue(arr[1].charAt(arr[0].length()-1))); //// gives register encoding in binary
							s += String.format("%3s", a).replaceAll(" ", "0");  // 3-bit Integer
							a = Integer.toBinaryString(count+count_NH-count_v); // get binarEncode for the variable stored in the men=mory after execution of all type of instrucions
							s += String.format("%8s", a).replaceAll(" ", "0");  // 3-bit Integer  
							arr_mem[l_no_m] = s;        //// storing the final instruction converted to the array
							l_no_m +=1;      //// incrementing the array index to hold the postition of new string coming
							System.out.println(s);
							flag = false;
							break;
						}
						temp = temp.getNext();
					}
					if(flag) {
						System.out.printf("Error at Line no. %d"+st_label+": %s No such Variable Declared", l_no_i, arr[1]);
						System.out.println();
					}
				}else {
					System.out.printf("Error at Line no. %d"+st_label+": %s No such Register defined.", l_no_i, arr[2]);
					System.out.println();
				}
			}else {
				System.out.printf("Error at Line no. %d"+st_label+": Invalid no of operands given for instruction Type: D.", l_no_i);
				System.out.println();
			}
		}else if (arr[0].equals("jmp") || arr[0].equals("jlt") || arr[0].equals("jgt") || arr[0].equals("je")) {
			count = 0;
			if(arr.length==2) {
				NodeM temp = head2.startM;
				while(temp!=null) {
					if(temp.getData().equals(arr[1])) {
						s = hlp.getOpcode(arr[0]);
						s += "000";
						a = Integer.toBinaryString(Character.getNumericValue(arr[1].charAt(arr[0].length()-1))); //// gives register encoding in binary
						s += String.format("%8s", a).replaceAll(" ", "0");  // 8-bit Integer
						arr_mem[l_no_m] = s;        //// storing the final instruction converted to the array
						l_no_m +=1;      //// incrementing the array index to hold the postition of new string coming
						System.out.println(s);
						flag = false;
						break;
					}
					count+=1;
					temp = temp.getNext();
				}
				if(flag) {
					System.out.printf("Error at Line no. %d"+st_label+": %s No such label Declared.", l_no_i, arr[1]);
					System.out.println();
				}
			}else {
				System.out.printf("Error at Line no. %d"+st_label+": Invalid no of operands given for instruction Type: E.", l_no_i);
				System.out.println();
			}
		}else if ((arr[0].charAt(ifordl)) == 58) {    //// i am not handling the error case where the instruction inside the label also has some label we have to give error also handle it 
			if(checkName(arr[0].substring(0,arr[0].length()-1))) { /// checks the name of the label
				if(1<arr.length && arr.length<6) {  /// checks the no of operands
					String tarr[] = new String[arr.length-1];
					head2.insertM(arr[0].substring(0,arr[0].length()-1), l_no_i);
					for (int i = 1; i < arr.length; i++) {
						tarr[i-1] = arr[i];
					}
					st_label = " in the Instruction iside the Declared Label";
					check(tarr);  ////  it checks the instructions which is inside the label by sending it to again in the check function
				}else {
					System.out.printf("Error at Line no. %d"+st_label+": Invalid no of operands for instruction type: Declaration of Label.", l_no_i);
					System.out.println();
				}
			}else {
				System.out.printf("Error at Line no. %d"+st_label+": Invalid Declaration of Label name.", l_no_i);
				System.out.println();
			}
		}else if(arr[0].equals("hlt")){
			emergency = 1;
			s = hlp.getOpcode(arr[0]);
			s += String.format("%11s", 0).replaceAll(" ", "0");  /// 11 bit no
			System.out.println(s);
		}else{
			System.out.printf("Error at Line no. %d"+st_label+": The instruction does not matches the type of instruction with the ISA.", l_no_i);
			st_label = " ";
			System.out.println();
		}
	}
}







