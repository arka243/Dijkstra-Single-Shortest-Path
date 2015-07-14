
public class FibonacciHeapNode 
{
	private int degree = 0;
	private boolean ChildCut = false;
	private int key, priority;
	public FibonacciHeapNode next, prev, parent, child;
	
	public FibonacciHeapNode (int data, int pr)
	{
		next = this;
		prev = this;
		key = data;
		priority = pr;
	}
	
	public int getDegree()
	{
		return degree;
	}
	
	public boolean getCutValue()
	{
		return ChildCut;
	}
	
	public int getKey()
	{
		return key;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public void setDegree(int value)
	{
		degree = value;
	}
	
	public void setChildCut(boolean value)
	{
		ChildCut = value;
	}
	
	public void setKey(int value)
	{
		key = value;
	}	
	
	public void setPriority(int value)
	{
		priority = value;
	}	
	
}
