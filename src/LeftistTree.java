import java.util.ArrayList;
import java.util.NoSuchElementException;

public class LeftistTree 
{
	private LeftistTreeNode rootNode;
	
	public LeftistTree()
	{
		rootNode = null;
	}
	
	public boolean TreeEmpty()
	{
		if(rootNode == null)
			return true;
		else return false;
	}
	
	public LeftistTreeNode insertNode(int k, int pr)
	{
		LeftistTreeNode newNode = new LeftistTreeNode(k, pr);
		rootNode = mergeNodes(rootNode, newNode);
		return newNode;
	}
	
	public void meld(LeftistTreeNode treeroot)
	{
		if(rootNode == treeroot)
			return;
		rootNode = mergeNodes(rootNode, treeroot);
	}
	
	public LeftistTreeNode mergeNodes(LeftistTreeNode n1, LeftistTreeNode n2)
	{
		if(n1 == null)
			return n2;
		if(n2 == null)
			return n1;
		if(n1.getPriority() > n2.getPriority())
		{
			LeftistTreeNode temp = n1;
			n1 = n2;
			n2 = temp;
		}
		n1.right = mergeNodes(n1.right, n2);
		if(n1.left == null)
		{
			n1.left = n1.right;
			n1.right = null;
		}
		else
		{
			if(n1.left.getDistance() < n1.right.getDistance())
			{
				LeftistTreeNode temp = n1.left;
				n1.left = n1.right;
				n1.right = temp;
			}
			n1.setDistance(n1.right.getDistance()+1);
		}
		return n1;
	}
	
	public LeftistTreeNode RemoveMin()
	{
		if(TreeEmpty())
			throw new NoSuchElementException("The Leftist Tree is Empty");
		LeftistTreeNode minNode = rootNode;
		rootNode = mergeNodes(rootNode.left, rootNode.right);
		return minNode;
	}
	
	public void DecreaseKey(LeftistTreeNode node, int newPriority)
	{
		if(newPriority > node.getPriority())
			System.out.println("Error! Key to decrease to exceeds original value!");
		else
		{
			if(rootNode.getKey() == node.getKey())
				rootNode.setPriority(newPriority);
			else if((rootNode.left != null && rootNode.left.getKey() == node.getKey()) || (rootNode.right != null && rootNode.right.getKey() == node.getKey()))
			{
				if(rootNode.getPriority() > newPriority)
				{
					if(rootNode.left != null && rootNode.left.getKey() == node.getKey())
					{
						rootNode.left = rootNode.right;
						rootNode.right = null;
						node.setPriority(newPriority);
						meld(node);
						return;
					}
					if(rootNode.right != null && rootNode.right.getKey() == node.getKey())
					{
						rootNode.right = null;
						node.setPriority(newPriority);
						meld(node);
						return;
					}
				}
				else
				{
					if(rootNode.right != null && rootNode.right.getPriority() == node.getPriority())
					{
						rootNode.right.setPriority(newPriority);
						return;
					}
					if(rootNode.left != null && rootNode.left.getPriority() == node.getPriority())
					{
						rootNode.left.setPriority(newPriority);
						return;
					}
				}
			}
			else
			{
				LeftistTreeNode current = rootNode;
				ArrayList<LeftistTreeNode> allnodes = new ArrayList<LeftistTreeNode>();
				populate(current, allnodes);
				int parentpos = allnodes.indexOf(rootNode);
				int nodepos = allnodes.indexOf(node);
				LeftistTreeNode parent = rootNode;
				while(!(parent.left == null && parent.right == null))
				{
					if(parentpos < nodepos && parent.right != null)
						parent = parent.right;
					if(parentpos > nodepos && parent.left != null)
						parent = parent.left;
					parentpos = allnodes.indexOf(parent);
					if((parent.left != null && parent.left.getKey() == node.getKey()) || (parent.right != null && parent.right.getKey() == node.getKey()))
						break;
				}
				if(parent.getPriority() > newPriority)
				{
					if(parent.right != null && parent.right.getKey() == node.getKey())
						parent.right = null;
					if(parent.left != null && parent.left.getKey() == node.getKey())
					{
						parent.left = parent.right;
						parent.right = null;
					}
					node.setPriority(newPriority);
					meld(node);
				}
				else
				{
					if(parent.right != null && parent.right.getKey() == node.getKey())
						parent.right.setPriority(newPriority);
					if(parent.left != null && parent.left.getKey() == node.getKey())
						parent.left.setPriority(newPriority);
				}
			}
		}
	}
	
	private void populate(LeftistTreeNode r, ArrayList<LeftistTreeNode> allNodes)
    {
        if (r != null)
        {
            populate(r.left, allNodes);
            allNodes.add(r);
            populate(r.right, allNodes);
        }
    }
}
