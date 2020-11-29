// Undirected graph represented in AdjacencyMatrix
import java.util.*;


class GraphAdjacencyMatrix{
    boolean[][] adjacencyMatrix;
    int numOfNodes;
    boolean isUndirected;

    public GraphAdjacencyMatrix(int numOfNodes, boolean isUndirected){
        this.adjacencyMatrix = new boolean[numOfNodes][numOfNodes];
        this.numOfNodes = numOfNodes;
        this.isUndirected = isUndirected;
    }

    public void printNodes(){
        System.out.print('{');
        for (int i = 0; i < numOfNodes; i++){
            System.out.print('{');
            for (int j = 0; j < numOfNodes; j++){
                System.out.print(this.adjacencyMatrix[i][j]);
            }
            System.out.print('}');
        }
        System.out.print('}');
    }

    public void addEdge(int sourceNode, int destinationNode){
        this.adjacencyMatrix[sourceNode][destinationNode] = true;
        if (this.isUndirected){
            this.adjacencyMatrix[destinationNode][sourceNode] = true;
        }
    }

    public void removeEdge(int sourceNode, int destinationNode){
        this.adjacencyMatrix[sourceNode][destinationNode] = false;
        if (this.isUndirected) {
            this.adjacencyMatrix[destinationNode][sourceNode] = false;
        }
    }

    public LinkedList<Integer> getConnectedNodes(int startNode){
        LinkedList<Integer> connectedNodes = new LinkedList<Integer>();
        boolean[] matrixRow = this.adjacencyMatrix[startNode];
        for (int i = 0; i < matrixRow.length; i++){
            if (matrixRow[i]){
                connectedNodes.add(i);
            }
        }
        return connectedNodes;
    }

    public void breadthFirstSearch(int startNode){
        boolean[] nodesVisited = new boolean[this.numOfNodes];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        
        // put startNode in nodesVisited,
        // and get connected nodes of startNode 
        // and put them in queue.
        nodesVisited[startNode] = true;
        queue.add(startNode);

        while (queue.size() != 0){
            int currentNode = queue.poll();
            System.out.print(currentNode + ", ");
            LinkedList<Integer> connectedNodes = getConnectedNodes(currentNode);
            
            for (int i = 0; i < connectedNodes.size(); i++){
                int node = connectedNodes.get(i);
                if (!nodesVisited[node]){
                    nodesVisited[i] = true;
                    queue.add(node);
                }
            }
        }
    }

    public static void main(String[] args){
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(3, true);
        graph.addEdge(1, 2);
        graph.addEdge(0, 1);
        // graph.removeEdge(0,2);
        // graph.printNodes();
        graph.breadthFirstSearch(0);
    }    
}