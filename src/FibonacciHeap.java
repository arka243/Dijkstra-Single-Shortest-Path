import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FibonacciHeap 
{
	private FibonacciHeapNode minNode = null;
	private int heapSize = 0;
	
	public boolean EmptyHeap()
	{
		if(minNode == null)
			return true;
		else return false;
	}
	
	public FibonacciHeapNode getMin()
	{
		if(EmptyHeap())
			throw new NoSuchElementException("The Fibonacci Heap is Empty!");
		return minNode;
	}
	
	public int getSize()
	{
		return heapSize;
	}
	
	public FibonacciHeapNode insertNode(int key, int priority)
	{
		FibonacciHeapNode newNode = new FibonacciHeapNode(key, priority);
		minNode = combineNodes(minNode, newNode);
		heapSize++;
		return newNode;
	}
	
	public static FibonacciHeapNode combineNodes(FibonacciHeapNode first, FibonacciHeapNode second)
	{
		if(first == null)
			return second;
		else if(second == null)
			return first;
		else
		{
			FibonacciHeapNode temp = first.next;
			first.next = second.next;
			first.next.prev = first;
			second.next = temp;
			second.next.prev = second;
			if(first.getPriority() < second.getPriority())
				return first;
			else
				return second;
		}
	}
	
	public static FibonacciHeap meld(FibonacciHeap f1, FibonacciHeap f2)
	{
		FibonacciHeap resultHeap = new FibonacciHeap();
		resultHeap.minNode = combineNodes(f1.getMin(), f2.getMin());
		resultHeap.heapSize = f1.getSize() + f2.getSize();
		f1.heapSize = 0;
		f2.heapSize = 0;
		f1.minNode = null;
		f2.minNode = null;
		return resultHeap;
	}
	
	public void DecreaseKey(FibonacciHeapNode node, int newPriority)
	{
		if(newPriority > node.getPriority())
			System.out.println("Error! Key to decrease to exceeds original value!");
		else
		{
			node.setPriority(newPriority);
			if(node.parent != null && node.getPriority() <= node.parent.getPriority())
				cascadingCut(node);
			if(node.getPriority() <= minNode.getPriority())
				minNode = node;
		}
	}
	
	public void cascadingCut(FibonacciHeapNode node)
	{
		node.setChildCut(false);
		if(node.parent == null)
			return;
		if(node.next != node)
		{
			node.next.prev = node.prev;
			node.prev.next = node.next;
		}
		if(node.parent.child == node)
		{
			if(node.next != node)
				node.parent.child = node.next;
			else
				node.parent.child = null;
		}
		node.parent.setDegree(node.parent.getDegree()-1);
		node.prev = node;
		node.next = node;
		minNode = combineNodes(minNode, node);
		
		if(node.parent.getCutValue())
			cascadingCut(node.parent);
		else
			node.parent.setChildCut(true);
		node.parent = null;
	}
	
	public FibonacciHeapNode RemoveMin()
	{
		if(EmptyHeap())
			throw new NoSuchElementException("The Fibonacci Heap is Empty!");
		heapSize--;
		FibonacciHeapNode tempMin = minNode;
		if(minNode.next == minNode)
			minNode = null;
		else
		{
			minNode.prev.next = minNode.next;
			minNode.next.prev = minNode.prev;
			minNode = minNode.next;
		}
		if(tempMin.child != null)
		{
			FibonacciHeapNode current = tempMin.child;
			do
			{
				current.parent = null;
				current = current.next;
			}while(current != tempMin.child);
		}
		minNode = combineNodes(minNode, tempMin.child);
		if(minNode == null)
			return tempMin;
		else
		{
			PairWiseCombine();
			return tempMin;
		}
	}
	
	public void PairWiseCombine()
	{
		ArrayList<FibonacciHeapNode> treeDegrees = new ArrayList<FibonacciHeapNode>();
		ArrayList<FibonacciHeapNode> toVisit = new ArrayList<FibonacciHeapNode>();
		
		for(FibonacciHeapNode temp = minNode; toVisit.isEmpty() || toVisit.get(0) != temp; temp = temp.next)
			toVisit.add(temp);
		for(FibonacciHeapNode temp: toVisit)
		{
			while(true)
			{
				while(temp.getDegree() >= treeDegrees.size())
					treeDegrees.add(null);
				if(treeDegrees.get(temp.getDegree()) == null)
				{
					treeDegrees.set(temp.getDegree(), temp);
					break;
				}
				FibonacciHeapNode rest = treeDegrees.get(temp.getDegree());
				treeDegrees.set(temp.getDegree(), null);
				FibonacciHeapNode min = (rest.getPriority() < temp.getPriority()) ? rest : temp;
				FibonacciHeapNode max = (rest.getPriority() < temp.getPriority()) ? temp : rest;
				max.next.prev = max.prev;
				max.prev.next = max.next;
				max.next = max;
				max.prev = max;
				min.child = combineNodes(min.child, max);
				max.parent = min;
				max.setChildCut(false);
				min.setDegree(min.getDegree()+1);
				temp = min;
			}
			if(temp.getPriority() <= minNode.getPriority())
				minNode = temp;
		}
	}
}
