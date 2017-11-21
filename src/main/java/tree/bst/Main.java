package tree.bst;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by igorhara on 21/11/2017.
 */
public class Main {


    public static void main(String[] args){
        //removeMiddleNodeTest();

        //removeRootNodeTest();

        //removeLastInsertedNodeTest();



    }

    private static void removeMiddleNodeTest() {
        BinarySearchTree tree = new BinarySearchTree();
        populateTree(tree,9);
        int valueToRemove = Math.abs(new Random().nextInt()%100);
        tree.add(valueToRemove);
        populateTree(tree,9);
        printTree(tree);
        System.out.println("value to remove "+valueToRemove);
        tree.remove(valueToRemove);
        printTree(tree);
    }
    private static void removeRootNodeTest() {
        BinarySearchTree tree = new BinarySearchTree();
        int valueToRemove = Math.abs(new Random().nextInt()%100);
        tree.add(valueToRemove);
        populateTree(tree,12);
        printTree(tree);
        System.out.println("value to remove "+valueToRemove);
        tree.remove(valueToRemove);
        printTree(tree);
    }

    private static void removeLastInsertedNodeTest() {
        BinarySearchTree tree = new BinarySearchTree();
        populateTree(tree,12);
        int valueToRemove = Math.abs(new Random().nextInt()%100);
        tree.add(valueToRemove);
        printTree(tree);
        System.out.println("value to remove "+valueToRemove);
        tree.remove(valueToRemove);
        printTree(tree);
    }


    private static void printTree(BinarySearchTree tree) {
        tree.print();
        System.out.println("is balanced "+tree.isBalanced());
        tree.printBalance();
    }

    private static void populateTree(BinarySearchTree tree,int numberOfNodes) {
        Random rand = new Random();
        rand.ints(numberOfNodes).map(i-> Math.abs(i%100)).forEach(tree::add);
    }
}
