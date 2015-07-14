import java.util.ArrayList;

public class dijkstra 
{
	public static int[] leftistMethod(Graph graph)
	{
		int source = graph.getSource();
	    int noofNodes = graph.gettotalNodes();
	    ArrayList<ArrayList<Integer>> adjacencyList = graph.getadjacencyList();
		LeftistTree lTree = new LeftistTree();
		LeftistTreeNode[] leftistDistance = new LeftistTreeNode[noofNodes];
		int[] outputDistance = new int[noofNodes];
		boolean[] traversal = new boolean[noofNodes];
		for(int i = 0; i < noofNodes; i++)
			leftistDistance[i] = lTree.insertNode(i, Integer.MAX_VALUE); 
		lTree.DecreaseKey(leftistDistance[source], 0);
		traversal[source] = true; 
		while (!lTree.TreeEmpty()) 
		{
			LeftistTreeNode currentmin = lTree.RemoveMin();
			int minData = currentmin.getKey();	
			int cost = currentmin.getPriority();
			traversal[minData] = true;	
			outputDistance[minData] = cost;
			for (int i=0;i<adjacencyList.get(minData).size()/2;i++) 
			{
				int adjNode = adjacencyList.get(minData).get(2*i);
				int arCost = adjacencyList.get(minData).get(2*i+1);
				int nextcost = leftistDistance[adjNode].getPriority();
				if (!traversal[adjNode] && nextcost > cost + arCost) {
					lTree.DecreaseKey(leftistDistance[adjNode], cost + arCost);
				}
			}		
		}
		return outputDistance;
	}
	
	public static int[] fibonacciMethod(Graph graph)
	{
		int source = graph.getSource();
	    int noofNodes = graph.gettotalNodes();
	    ArrayList<ArrayList<Integer>> adjacencyList = graph.getadjacencyList();
		FibonacciHeap fHeap = new FibonacciHeap();
		FibonacciHeapNode[] fibonacciDistance = new FibonacciHeapNode[noofNodes];
		int[] outputDistance = new int[noofNodes];
		boolean[] traversal = new boolean[noofNodes];
		for(int i = 0; i < noofNodes; i++)
			fibonacciDistance[i] = fHeap.insertNode(i, Integer.MAX_VALUE); 
		fHeap.DecreaseKey(fibonacciDistance[source], 0);
		traversal[source] = true; 
		while (!fHeap.EmptyHeap()) 
		{
			FibonacciHeapNode currentmin = fHeap.RemoveMin();
			int minData = currentmin.getKey();	
			int cost = currentmin.getPriority();
			traversal[minData] = true;	
			outputDistance[minData] = cost;
			for (int i=0;i<adjacencyList.get(minData).size()/2;i++) 
			{
				int adjNode = adjacencyList.get(minData).get(2*i);
				int arCost = adjacencyList.get(minData).get(2*i+1);
				int nextcost = fibonacciDistance[adjNode].getPriority();
				if (!traversal[adjNode] && nextcost > cost + arCost) {
					fHeap.DecreaseKey(fibonacciDistance[adjNode], cost + arCost);
				}
			}		
		}
		return outputDistance;
	}
	
	public static void main(String args[])
	{
		try
		{
			if(args.length < 1 || args[0].charAt(0) != '-')
				throw new Exception();
			switch(args[0].charAt(1))
			{
				case 'r':
					if(args.length != 4)
						throw new Exception();
					int n = Integer.parseInt(args[1]);
					double d = Double.parseDouble(args[2]);
					int x = Integer.parseInt(args[3]);
					ExecutionModes.sspRandom(n,d,x);
					break;
				case 'l':
					if(args.length != 2)
						throw new Exception();
					ExecutionModes.sspLeftist(args[1]);
					break;
				case 'f':
					if(args.length != 2)
						throw new Exception();
					ExecutionModes.sspFibonacci(args[1]);
					break;
				default:
					throw new Exception();
			}
		} catch(Exception e)
		{
			System.out.println("Error! Invalid input format! Please follow either of the following input formats:");
			System.out.println("For Random Mode: -r totalnodes density sourcenode");
			System.out.println("For Leftist Tree User Input Mode: -l filename");
			System.out.println("For Fibonacci Heap User Input Mode: -f filename");
		}
	}

}
