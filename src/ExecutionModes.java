import java.io.IOException;
import java.io.PrintWriter;

public class ExecutionModes 
{
	public static void sspRandom(int noofNodes, double density, int sourceNode)
	{
		System.out.println("Graph being generated...");
		Graph graph = new Graph(noofNodes,density,sourceNode);
		graph.Display();
		System.out.println("Graph generation complete!");
		System.out.println("Executing Leftist Tree based routine...");
		long lstarttime = System.currentTimeMillis();
		int[] leftistDistance = dijkstra.leftistMethod(graph);
		long lendtime = System.currentTimeMillis();
		System.out.println("Time taken by Leftist Tree Method is = "+(lendtime-lstarttime)+" milliseconds");
		System.out.println("Executing Fibonacci Heap based routine...");
		long fstarttime = System.currentTimeMillis();
		int[] fibonacciDistance = dijkstra.fibonacciMethod(graph);
		long fendtime = System.currentTimeMillis();
		System.out.println("Time taken by Fibonacci Heap Method is = "+(fendtime-fstarttime)+" milliseconds");
	}
	
	public static void sspLeftist(String filename)
	{
		Graph graph = new Graph(filename);
		long lstarttime = System.currentTimeMillis();
		int[] leftistDistance = dijkstra.leftistMethod(graph);
		long lendtime = System.currentTimeMillis();
		String foutFile = "Output.txt";
		try
		{
			PrintWriter outputFile = new PrintWriter(foutFile);
			for(int i=0;i<graph.gettotalNodes();i++)
				outputFile.println(leftistDistance[i]+"//cost from node "+graph.getSource()+" to "+i);
			outputFile.close();
			System.out.println("Check Output.txt file for final output!");
		} catch(IOException io)
		{
			io.printStackTrace();
		}
	}
	
	public static void sspFibonacci(String filename)
	{
		Graph graph = new Graph(filename);
		long fstartime = System.currentTimeMillis();
		int[] fibonacciDistance = dijkstra.fibonacciMethod(graph);
		long fendtime = System.currentTimeMillis();
		String foutFile = "Output.txt";
		try
		{
			PrintWriter outputFile = new PrintWriter(foutFile);
			for(int i=0;i<graph.gettotalNodes();i++)
				outputFile.println(fibonacciDistance[i]+"//cost from node "+graph.getSource()+" to "+i);
			outputFile.close();
			System.out.println("Check Output.txt file for final output!");
		} catch(IOException io)
		{
			io.printStackTrace();
		}
	}
}
