import java.util.Random;

public class Tree {
    private Node root;
    private int size;

    public boolean contains(int value) { // O(log N)
        Node cur = root;
        while (cur != null) {
            if (cur.value == value)
                return true;
            else {
                if (cur.value < value)
                    cur = cur.right;
                else
                    cur = cur.left;
            }
        }
        return false;
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node == null)
            return;
        System.out.print(node.value + "  Color:" + node.color);

        System.out.print("\t(");
        if (node.left != null)
            System.out.printf("L:" + node.left.value);
        else
            System.out.print("L:null");
        System.out.print("\t\t");
        if (node.right != null)
            System.out.printf("R:" + node.right.value);
        else
            System.out.print("R:null");
        System.out.println(")");

        print(node.left);
        print(node.right);
    }

    public boolean add(int value) {
        if (root != null) {
            boolean res = add(value, root);
            root = rebalance(root);
            root.color = Color.BLACK;
            return res;
        }
        else {
            root = new Node(value);
            root.color = Color.BLACK;
            size = 1;
            return true;
        }
    }

    private boolean add(int value, Node node) {
        if (node.value == value)
            return false;
        else {
            if (node.value > value) {
                if (node.left != null) {
                    boolean result = add(value, node.left);
                    node.left = rebalance(node.left);
                    return result;
                } else {
                    node.left = new Node(value);
                    size++;
                    return true;
                }
            } else {
                if (node.right != null) {
                    boolean result = add(value,node.right);
                    node.right = rebalance(node.right);
                    return result;
                } else {
                    node.right = new Node(value);
                    size++;
                    return true;
                }
            }
        }
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean flag;
        do {
            flag = false;
            if (result.right != null && result.right.color == Color.RED &&
                    (result.left == null || result.left.color == Color.BLACK)) {
                flag = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED &&
                    (result.left.left != null &&
                            result.left.left.color == Color.RED)) {
                flag = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED &&
                    (result.right != null && result.right.color == Color.RED)) {
                flag = true;
                colorSwap(result);
            }
        } while (flag);
        return result;
    }

    private Node leftSwap(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }

    private Node rightSwap(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = Color.RED;
        return right;
    }

    private void colorSwap(Node node) {
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }

    private class Node {
        int value;
        Color color;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.color = Color.RED;
        }
    }

    private enum Color {RED, BLACK}

    public static void main(String[] args) {
        Random random = new Random();
        Tree tree = new Tree();
        for (int i = 1; i < 20; i++) {
            tree.add(random.nextInt(1,100));
        }
        tree.print();
    }
}