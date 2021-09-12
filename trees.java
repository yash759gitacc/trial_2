package trees;

public class trees {

	public static void main(String[] args) 
	{
		node base = new node();
		tree t1 = new tree();
		t1.add(base, 10);
		t1.add(base, 20);
		t1.add(base, 5);
		t1.add(base, 2);
		t1.add(base, 7);
		t1.add(base, 1);
		t1.show(base);
		
	}

}

class node 
{
	int data;
	node l;
	node r;
}

class tree
{	
	public void add(node n, int a)
	{
		if (n.data==0)
		{
			n.data=a;
		}

		else if(a>n.data)
		{
			if(n.r==null)
			{
				node x=new node();
				x.data=a;
				n.r=x;
			}
			add(n.r,a);
		}
		
		else if (a<n.data)
		{
			if(n.l==null)
			{
				node x = new node();
				x.data=a;
				n.l=x;
			}
			add(n.l,a);
		}

	}
	public void show(node n)
	{
		if(n.l!=null)
		{
			show(n.l);
		}
		System.out.print(n.data+"  ");
		if(n.r!=null)
		{
			show(n.r);
		}
	}
	
	public void del(node n,int a)
	{
		if(a>n.data);
	}
	
	
	
	
	
}




////////////////////////////








