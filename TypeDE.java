package trees;
///////   get opcode of instruction
///////   check register name
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

////////////////////////////CLASS ACTING AS THE MAIN INSTRUCTION////////////////////////
public class TypeDE{
	public static void main(String[] args)throws IOException {
		Reader.init(System.in);
		boolean flag = true;
		int count_N = 0;   ////////// this is used to count the no of inputs given 
		int i = 0;
		String arr_i[] = new String[257];  ///     arr_i is used to denote array storing all the inputs
		while(flag){
			count_N+=1;
			arr_i[i] = Reader.nextLine();
			if(arr_i[i].equals("hlt")){
				break;
			}
			i+=1;	
		}
		CheckAP obj = new CheckAP();
		obj.count_NH = count_N;
		int temp = 0;
		while(temp<count_N-1 && obj.emergency !=1){
			if(arr_i[temp]==" "){
				obj.l_no_i +=1;
				continue;
			}else{
				String tarr[]= arr_i[temp].split(" ");
				obj.l_no_i +=1;
				obj.check(tarr);
			}
			temp+=1;
		}
	}
}

//////////////////////////////////////CLASS WHICH CHECKS THE ERROR AND BOTH CONVERT INTO BINARY CODING////////////////////////
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

	
	//////////////////////////CHECK WHETHER THE NAME IS ALPHANUMERIC OR NOT/////////////////////
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
	///////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////FUNCTION WHICH CHECKS THE ERROR AND CONVERTS INTO BINARY CODE//////////////////////////
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
////////////////////////////////////////////////////////////////////////////////////

////////////////////////////HELPER CLASS WHICH IMPLEMENTS - CHECKING REGISTER NAMES AND RETURNS OPCODE/////////////////////
class Helper{
	public boolean checkRegisterName(String s){
		if(s.equals("R0") || s.equals("R1") || s.equals("R2") || s.equals("R3") || s.equals("R4") || s.equals("R5") || s.equals("R6") || s.equals("FLAGS")){
			return true;
		}
		return false;
	}
	public String getOpcode(String s){
		if(s.equals("ld")){
			return "00100";
		}
		if(s.equals("st")){
			return "00101";
		}
		if(s.equals("jmp")){
			return "01111";
		}
		if(s.equals("jlt")){
			return "10000";
		}
		if(s.equals("jgt")){
			return "10001";
		}
		if(s.equals("je")){
			return "10010";
		}
		return "0";												
	}
}

//////////////////////////////////////////FORMATION OF LINKED LIST/////////////////////////////	
class LinkedList{
	public NodeM startM;
	public Node start;
	private int size;
	public LinkedList() {
		size = 0;
		start = null;
		startM = null;
	}
	public boolean isEmpty() {
		return (size == 0);
	}
			
	public void insert(String val) {
		Node ptr = new Node(val);
		Node temp = start;
		if (isEmpty()) {
			start = ptr;
		} else {
			while (temp != null) {
				if (temp.getNext() == null) {
					temp.setNext(ptr);
					break;
				}
				temp = temp.getNext();
			}
		}
			// start = temp;
		size += 1;
	}
	public void insertM(String val, int indexInmem) {
		NodeM ptrM = new NodeM(val);
		NodeM tempM = startM;
		ptrM.setIndex(indexInmem);
		if (isEmpty()) {
			startM = ptrM;
		} else {
			while (tempM != null) {
				if (tempM.getNext() == null) {
					tempM.setNext(ptrM);
					break;
				}
				tempM = tempM.getNext();
			}
		}
			// start = temp;
		size += 1;
	}		
}
//////////////////////////////////////////////////////////////////

///////////////////// class for making of linked list for storing label /////////////////////////
class NodeM{
	private String data;
	private NodeM next;
	private int indInM;
	public NodeM(String val) {
		data = val;
		next = null;
		indInM = -1;
	}
	public NodeM (String d, int k) {
		data = d;
		next = null;
		indInM = k;
	}
	public void setNext(NodeM n) {
		next = n;
	}
	
	public void setData(String d) {
		data = d;
	}
	public void setIndex(int k) {
		indInM = k;
	}
	
	public NodeM getNext() {
		return next;
	}
	public int getIndex() {
		return indInM;
	}
	
	public String getData() {
		return data;
	}	
}
////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////
class Node {
	private String data;
	private Node next;
	public Node() {
		data = "";
		next = null;
	}
	
	public Node(String d, Node n) {
		next = n;
		data = d;
	}
	public Node(String d) {
		data = d;
	}
	
	public void setNext(Node n) {
		next = n;
	}
	
	public Node getNext() {
		return next;
	}
	
	public String getData() {
		return data;
	}
}	
////////////////////////////////////////////////////////////////////////////////////


///////////////////////// CLASS TAKING FOR THE INPUT ////////////////////////////
class Reader {
	static BufferedReader reader;
	static StringTokenizer tokenizer;
	
	/** call this method to initialize reader for InputStream */
	static void init(InputStream input) {
		reader = new BufferedReader(
		    new InputStreamReader(input) );
		tokenizer = new StringTokenizer("");
	}
	
	/** get next word */
	static String next() throws IOException {
		while ( ! tokenizer.hasMoreTokens() ) {
			tokenizer = new StringTokenizer(
			    reader.readLine() );
		}
		return tokenizer.nextToken();
	}
	static String nextLine() throws IOException {
		return reader.readLine();
	}
	
	static int nextInt() throws IOException {
		return Integer.parseInt( next() );
	}
	
	static double nextDouble() throws IOException {
		return Double.parseDouble( next() );
	}
}
/////////////////////////////////////////////////////////////////////////////////////////