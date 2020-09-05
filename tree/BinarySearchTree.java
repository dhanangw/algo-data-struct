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

    public static void main(String[] args){
        Node root = null;
        BinarySearchTree bst = new BinarySearchTree();
        root = bst.insert(root, 15);
        root = bst.insert(root, 10);
        root = bst.insert(root, 20);
        root = bst.insert(root, 25);
        root = bst.insert(root, 8);
        root = bst.insert(root, 12);
        root = bst.insert(root, 120);
        System.out.println("search result: " + bst.search(root, 12));
        System.out.println("min value: " + bst.findMin(root));
        System.out.println("max value: " + bst.findMax(root));
    }
}