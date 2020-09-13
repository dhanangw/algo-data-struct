import java.util.LinkedList;
import java.util.Queue;

class BinarySearchTree{
    private class Node{
        int value;
        Node left;
        Node right;
        
        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public Node insert(Node node, int value){
        if (node == null){
            Node newNode = new Node(value);
            node = newNode;
        } else if (node.value <= value) {
            node.right = insert(node.right, value);
        } else if (node.value >= value){
            node.left = insert(node.left, value);
        }
        return node;
    }

    public boolean search(Node node, int target_value){
        if (node == null){
            return false; 
        } else if (node.value == target_value){
            return true;
        } else if (target_value <= node.value){
            return search(node.left, target_value);
        } else {
            return search(node.right, target_value);
        }
    }

    public int findMin(Node node){
        if (node == null){
            System.out.println("tree is empty!");
            return -1;
        } else if (node.left == null){
            return node.value;
        } else {
            return findMin(node.left);
        }
    }

    public int findMax(Node node){
        if (node == null){
            System.out.println("tree is empty!");
            return -1;
        } else if (node.right == null){
            return node.value;
        } else {
            return findMax(node.right);
        }
    }

    public int findHeight(Node node){
        if (node == null){
            return -1;
        }
        int leftHeight = findHeight(node.left);
        int rightHeight = findHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void levelOrderTraversal(Node node){
        if (node == null){
            return;
        }

        Queue<Node> discoveredNodesQueue = new LinkedList<Node>();
        discoveredNodesQueue.add(node);
        while(!discoveredNodesQueue.isEmpty()){
            Node visitedNode = discoveredNodesQueue.poll();
            System.out.print(visitedNode.value + " ");
            if (visitedNode.left != null){
                discoveredNodesQueue.add(visitedNode.left);
            } 
            if (visitedNode.right != null){
                discoveredNodesQueue.add(visitedNode.right);
            }
        };
    }

    public void preorderTraversal(Node node){
        if (node == null){
            return;
        }
        System.out.print(node.value + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    public void inorderTraversal(Node node){
        if (node == null){
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.value + " ");
        inorderTraversal(node.right);
    }

    public void postorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        inorderTraversal(node.right);
        System.out.print(node.value + " ");
    }

    Node previousNodeIsBinarySearchTree;

    public boolean isBinarySearchTree(Node node){
        /*
        do inorder traversal,
        saves previousNode,
        if currentNode.value <= previousNode.value then not an BST.
        else is an BST.
        */
        if (node == null){
            return true;
        }
        if (isBinarySearchTree(node.left) == false){
            return false;
        }

        if (previousNodeIsBinarySearchTree != null
                && node.value <= previousNodeIsBinarySearchTree.value){
            return false;
        }
        previousNodeIsBinarySearchTree = node;

        return isBinarySearchTree(node.right);
    }

    public static void main(String[] args){
        Node root = null;
        BinarySearchTree bst = new BinarySearchTree();
        root = bst.insert(root, 15);
        root = bst.insert(root, 10);
        root = bst.insert(root, 20);
        root = bst.insert(root, 25);
        root = bst.insert(root, 8);
        root = bst.insert(root, 12);
        // System.out.println("search result: " + bst.search(root, 12));
        // System.out.println("min value: " + bst.findMin(root));
        // System.out.println("max value: " + bst.findMax(root));
        // System.out.println("level order traversal: ");
        // bst.levelOrderTraversal(root);
        // System.out.println("preorder traversal : ");
        // bst.preorderTraversal(root);
        // System.out.println("inorder traversal : ");
        // bst.inorderTraversal(root);
        // System.out.println("postorder traversal : ");
        // bst.postorderTraversal(root);
        System.out.println(bst.isBinarySearchTree(root));
    }
}