import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Graph 
{
	private int noofNodes, noofEdges, sourceNode;
	private ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>();
	
	public Graph(int totalnodes, double density, int sourcenode)
	{
		sourceNode = sourcenode;
		noofNodes = totalnodes;
		noofEdges = (int) (noofNodes*(noofNodes-1)*density/200);
		if(noofEdges < noofNodes - 1)
		{
			System.out.println("Too less density for Graph creation! Exiting...");
			System.exit(0);
		}
		boolean connectedGraph = false;
		Random random = new Random();
		while(!connectedGraph)
		{
			if (noofEdges == (noofNodes*(noofNodes-1)/2)) 
            {
                for(int i=0;i<noofNodes;i++)
                    adjacencyList.add(new ArrayList<Integer>());
                for (int i=0;i<noofNodes;i++) 
                {
                    for (int j=i+1;j<noofNodes;j++) 
                    {
                        int edgecost = random.nextInt(1000);
                        adjacencyList.get(i).add(j);
                        adjacencyList.get(i).add(edgecost);
                        adjacencyList.get(j).add(i);
                        adjacencyList.get(j).add(edgecost);
                    }
                }
                connectedGraph = true;
            }
			else
			{
				boolean[][] graph = new boolean[noofNodes][noofNodes];
				boolean[] connectivity = new boolean[noofNodes];
				for(int i=0;i<noofNodes;i++)
					adjacencyList.add(new ArrayList<Integer>());
				for (int i=0;i<noofEdges;i++) 
                {
                    int edgecost = random.nextInt(1000);
                    int source = random.nextInt(noofNodes);
                    int destination = random.nextInt(noofNodes);
                    while(source == destination || graph[source][destination])
                    {
                    	source = random.nextInt(noofNodes);
                    	destination = random.nextInt(noofNodes);                    	
                    }
                    graph[source][destination] = true;
                    graph[destination][source] = true;
                    adjacencyList.get(source).add(destination);
                    adjacencyList.get(source).add(edgecost);
                    adjacencyList.get(destination).add(source);
                    adjacencyList.get(destination).add(edgecost);
                }
				DFS(graph, connectivity, 0);
				connectedGraph = true;
				for(int i=0;i<connectivity.length;i++)
				{
					if(!connectivity[i])
					{
						connectedGraph = false;
						break;
					}
				}
			}
		}
	}

	public Graph(String filename)
	{
		File file = new File(filename);
		try
		{
			Scanner s = new Scanner(file);
			if(s.hasNextInt())
				sourceNode = Integer.parseInt(s.next());
			if(s.hasNextInt())
				noofNodes = Integer.parseInt(s.next());
			if(s.hasNextInt())
				noofEdges = Integer.parseInt(s.next());
			for(int i=0;i<noofNodes;i++)
				adjacencyList.add(new ArrayList<Integer>());
			while(s.hasNextInt())
			{
				int source = Integer.parseInt(s.next());
				int destination = Integer.parseInt(s.next());
				int edgecost = Integer.parseInt(s.next());
				adjacencyList.get(source).add(destination);
				adjacencyList.get(source).add(edgecost);
				adjacencyList.get(destination).add(source);
				adjacencyList.get(destination).add(edgecost);
			}
			s.close();
		} catch(Exception e)
		{
			System.out.println("Error, file not found!");
		}
	}
	
	public int getSource()
	{
		return sourceNode;
	}
	
	public int gettotalNodes()
	{
		return noofNodes;
	}
	
	public int gettotalEdges()
	{
		return noofEdges;
	}
	
	public ArrayList<ArrayList<Integer>> getadjacencyList()
	{
		return adjacencyList;
	}
	
	public void DFS(boolean[][] graphMatrix, boolean[] connectivity, int source) {
        connectivity[source] = true;
        for (int i=0;i<connectivity.length;i++) 
        {
            if (graphMatrix[source][i] && !connectivity[i]) 
            {
                DFS(graphMatrix, connectivity, i);
            }
        }   
    }
	
	public void Display()
	{
        for(int i=0;i<this.adjacencyList.size();i++)
        {
            ArrayList<Integer> nodeAdjacency = adjacencyList.get(i);
            System.out.printf("Source: "+i+"\t\t");
            for(int j=0;j<nodeAdjacency.size();j++)
            {
                System.out.printf("Destination: "+nodeAdjacency.get(j));
                System.out.print("\t");
                if(j+1<nodeAdjacency.size())
                {
                    j++;
                    System.out.printf("Cost: "+nodeAdjacency.get(j)+"\t");
                }
                
            }
            System.out.println("");
        }
    }
}
