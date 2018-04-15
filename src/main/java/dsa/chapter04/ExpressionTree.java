package dsa.chapter04;

import java.util.Stack;


public class ExpressionTree {

    private BinaryNode<String> root;
    private Stack<BinaryNode<String>> nodes;


    public ExpressionTree() {
        root = null;
        nodes = new Stack<>();
    }

    private void pushNode(String c) {
        BinaryNode<String> node = new BinaryNode<>(c);
        this.nodes.push(node);
    }

    private void pushNode(BinaryNode<String> node) {
        nodes.push(node);
    }

    private BinaryNode<String> popNode() {
        return nodes.pop();
    }

    private boolean isOperand(String c) {
        return true;
    }

    private boolean isOperator(String c) {
        String[] operators = {"+", "-", "*", "/"};
        for(String op: operators) {
            if(c.equals(op)) {
                return true;
            }
        }
        return false;
    }

    public static void inOrderTravel(BinaryNode<String> node) {
        if(node != null) {
            inOrderTravel(node.getLeft());
            System.out.println(" " + node.getElement() + " ");
            inOrderTravel(node.getRight());
        }
    }


    public BinaryNode<String> postfixToExpressionTree(String expression) {
        String[] components = expression.split("\\s+");

        for(String c: components) {
            if(isOperator(c)) {
                BinaryNode<String> leftTree = popNode();
                BinaryNode<String> rightTree = popNode();
                BinaryNode<String> parent = new BinaryNode<>(c);
                parent.setLeft(leftTree);
                parent.setRight(rightTree);
                pushNode(parent);
            } else {
                pushNode(c);
            }
        }

        root = popNode();
        return root;
    }


    public static void main(String[] args) {
        String expression = "a b + c d e + * *";
        ExpressionTree tree = new ExpressionTree();
        BinaryNode<String> root = tree.postfixToExpressionTree(expression);
        ExpressionTree.inOrderTravel(root);
    }
}
