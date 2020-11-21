// Undirected graph represented in AdjacencyMatrix
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
    public static void main(String[] args){
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(3, true);
        graph.addEdge(1, 2);
        graph.addEdge(0, 2);
        graph.removeEdge(0,2);
        graph.printNodes();
    }    
}