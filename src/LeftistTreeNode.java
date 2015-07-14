public class LeftistTreeNode 
{
	private int key, priority, distance;
	public LeftistTreeNode left, right;
	
	public LeftistTreeNode(int data, int pr)
	{
		this(data, pr, null, null);
	}
	
	public LeftistTreeNode(int data, int pr, LeftistTreeNode lhs, LeftistTreeNode rhs)
	{
		key = data;
		priority = pr;
		left = lhs;
		right = rhs;
		distance = 0;
	}
	
	public int getKey()
	{
		return key;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public void setKey(int k)
	{
		key = k;
	}
	
	public void setPriority(int k)
	{
		priority = k;
	}
	
	public void setDistance(int k)
	{
		distance = k;
	}
}
