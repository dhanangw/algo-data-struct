import java.util.*;


class GraphAdjacencyList{
    ArrayList<ArrayList<Integer>> graph;
    int numOfNodes;
    boolean isUndirected;

    public GraphAdjacencyList(int numOfNodes, boolean isUndirected){
        this.graph = new ArrayList<ArrayList<Integer>>(numOfNodes);
        this.numOfNodes = numOfNodes;
        this.isUndirected = isUndirected;
    }

    public void addNode() {
        ArrayList<Integer> newNode = new ArrayList<Integer>();
        graph.add(newNode);
    }

    public void addEdge(int sourceNode, int targetNode){
        ArrayList<Integer> sourceNodeEdgeList = graph.get(sourceNode);
        sourceNodeEdgeList.add(targetNode);
        if (this.isUndirected){
            ArrayList<Integer> targetNodeEdgeList = graph.get(targetNode);
            targetNodeEdgeList.add(sourceNode);
        }
    }

    public void printGraph(){
        System.out.print('{');
        for (int i = 0; i < this.numOfNodes; i++){
            System.out.print(graph.get(i) + ",");
        }
        System.out.print('}');
    }

    public void breadthFirstSearch(int startNode){
        boolean[] visitedNodes = new boolean[this.numOfNodes];
        Queue<Integer> queue = new LinkedList<Integer>();

        visitedNodes[startNode] = true;
        queue.add(startNode);

        while(queue.size() != 0){
            int currentNode = queue.poll();
            System.out.print(currentNode + ", ");
            ArrayList<Integer> connectedNodes = this.graph.get(currentNode);

            for (int i = 0; i < connectedNodes.size(); i++){
                int node = connectedNodes.get(i);
                if (!visitedNodes[node]){
                    visitedNodes[node] = true;
                    queue.add(node);
                }
            }
        }
    }

    public static void main(String[] args){
        GraphAdjacencyList graph = new GraphAdjacencyList(3, true);
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        // graph.printGraph();
        graph.breadthFirstSearch(1);
    }
}