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

    public boolean is_exists(Node node, int target_value){
        if (node == null){
            return false; 
        } else if (node.value == target_value){
            return true;
        } else if (target_value <= node.value){
            return is_exists(node.left, target_value);
        } else {
            return is_exists(node.right, target_value);
        }
    }

    public int findMinValue(Node node){
        if (node == null){
            System.out.println("tree is empty!");
            return -1;
        } else if (node.left == null){
            return node.value;
        } else {
            return findMinValue(node.left);
        }
    }

    public int findMaxValue(Node node){
        if (node == null){
            System.out.println("tree is empty!");
            return -1;
        } else if (node.right == null){
            return node.value;
        } else {
            return findMaxValue(node.right);
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

    public Node deleteNode(Node node, int value){
        if (node == null){
            return node;
        } 
        else if (node.value < value){
            node.right = deleteNode(node.right, value);
        } 
        else if (node.value > value){
            node.left = deleteNode(node.left, value);
        } 
        else { // node-to-be-deleted has been found.
            // case 1: node-to-be-deleted has no childs.
            if (node.left == null && node.right == null){
                node = null;
            }
            // case 2: node-to-be-deleted only has 1 child.
            else if (node.left != null && node.right == null){
                node = node.left;
            }
            else if  (node.left == null && node.right != null){
                node = node.right;
            }
            // case 3: node-to-be-deleted has 2 childs.
            else if (node.left != null && node.right != null){
                // find node with max value in left subtree.
                // or find node with min value in right subtree.
                // replace node-to-be-deleted's value with min/max value.
                // delete original min/max value.
                int maxOfLeft = findMaxValue(node.left);
                node.value = maxOfLeft;
                node.left = deleteNode(node.left, maxOfLeft);
            }
        }
        return node;
    }

    public Node findNode(Node root, int value){
        if (root == null){
            return null;
        } else if(root.value == value){
            return root;
        } else if (root.value <= value){
            return findNode(root.right, value);
        } else {
            return findNode(root.left, value);
        }
    }

    public int inorderSuccessor(Node node, int value){
        // Go to the node with value.
        Node startNode = findNode(node, value);
        if (startNode.right != null){
            // case 1: node has right subtree: find min node in right subtree
            return findMinValue(startNode.right);
        } else {
            // case 2: node has no right subtree: go to nearest ancestor for which given node would be in left subtree.
            Node successor = null;
            Node ancestor = node;
            while (ancestor != startNode){
                if (startNode.value <= ancestor.value) {
                    successor = ancestor;
                    ancestor = ancestor.left;
                } else {
                    ancestor = ancestor.right;
                }
            }
            if (successor != null){
                return successor.value;
            } else {
                // successor not found, return -1.
                return -1;
            }
        }
    }

    public static void main(String[] args){
        /**
         * test BinarySearchTree with this tree.
         *       15
         *     /    \
         *   10      20
         *  /  \       \
         * 8    12      25
         */
        Node root = null;
        BinarySearchTree bst = new BinarySearchTree();
        root = bst.insert(root, 15);
        root = bst.insert(root, 10);
        root = bst.insert(root, 20);
        root = bst.insert(root, 25);
        root = bst.insert(root, 8);
        root = bst.insert(root, 12);
        // System.out.println("search result: " + bst.is_exists(root, 12));
        // System.out.println("min value: " + bst.findMinValue(root));
        // System.out.println("max value: " + bst.findMaxValue(root));
        // System.out.println("level order traversal: ");
        // bst.levelOrderTraversal(root);
        // System.out.println("preorder traversal : ");
        // bst.preorderTraversal(root);
        // System.out.println("inorder traversal : ");
        // bst.inorderTraversal(root);
        // System.out.println("postorder traversal : ");
        // bst.postorderTraversal(root);
        // System.out.println(bst.isBinarySearchTree(root));
        // root = bst.deleteNode(root, 10);
        // bst.inorderTraversal(root); // result should be: 8 12 15 20 25
        System.out.println(bst.inorderSuccessor(root, 8)); // result should be: 10
        System.out.println(bst.inorderSuccessor(root, 15)); // result should be: 20
        System.out.println(bst.inorderSuccessor(root, 20)); // result should be: 25
        System.out.println(bst.inorderSuccessor(root, 25)); // result should be: null
    }
}