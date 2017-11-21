package tree.bst;

/**
 * Created by igorhara on 20/11/2017.
 */
public class BinarySearchTree {

    BSTNode root;
    int leftLevel,rightLevel = 0;
    int balanceDiff = 2;


    public void add(int value){
        BSTNode node = new BSTNode(value);
        if(this.root==null){
            this.root=node;
        }else{
            this.root.addNoteChild(node);
        }
    }


    public boolean  isBalanced(){
        if(root==null){
            return true;
        }
        int left = root.getLeft()!=null?root.getLeft().getMaxLevel():0;
        int right = root.getRight()!=null?root.getRight().getMaxLevel():0;
        if(Math.abs(left-right) > balanceDiff){
            return false;
        }
        return true;
    }

    public void print(){
        System.out.println("root is: "+root.getValue());
        print(root);
        System.out.println();
    }

    private void print(BSTNode node){
        if(node==null){
            return;
        }
        print(node.getLeft());
        System.out.print(" "+node.getValue()+" ");
        print(node.getRight());
    }

    public boolean remove(int i){
        if(root==null){
            return false;
        }
        BSTNode nodeWithValue = root.getNodeWithValue(i);
        if(nodeWithValue==null){
            return false;
        }
        if(nodeWithValue==root){
            return removeRoot();
        }
        return removeMiddleNode(nodeWithValue);

    }

    private boolean removeMiddleNode(BSTNode nodeWithValue) {
        BSTNode other=null;
        if(nodeWithValue.getLeft()!=null && root.getRight()!=null){
            if(nodeWithValue.value >=nodeWithValue.parent.value){
                other = nodeWithValue.getRight();
                nodeWithValue.getParent().setRight(nodeWithValue.getLeft());
            }else{
                other = nodeWithValue.getLeft();
                nodeWithValue.getParent().setLeft(nodeWithValue.getRight());
            }
            root.addNoteChild(other);
            return true;
        }else if(nodeWithValue.getRight()!=null){
            other = nodeWithValue.getRight();
        }else if(nodeWithValue.getLeft()!=null){
            other = nodeWithValue.getLeft();
        }
        if(nodeWithValue.parent.getLeft()==nodeWithValue){
            nodeWithValue.parent.setLeft(other);
        }else{
            nodeWithValue.parent.setRight(other);
        }
        return true;
    }

    private boolean removeRoot() {
        int left = root.getLeft()!=null?root.getLeft().getMaxLevel():0;
        int right = root.getRight()!=null?root.getRight().getMaxLevel():0;
        BSTNode other;
        if(root.getLeft()!=null && root.getRight()!=null){
            if(left >=right ){
                other = root.getRight();
                root = root.getLeft();
            }else{
                other = root.getLeft();
                root = root.getRight();
            }
            root.addNoteChild(other);
            return true;
        }else if(root.getRight()!=null){
            root = root.getRight();
        }else if(root.getLeft()!=null){
            root = root.getLeft();
        }else{
            root =null;
        }
        if(root!=null){
            root.setParent(null);
        }
        return true;
    }

    public void printBalance(){
        System.out.println("level left: "+ getMaxLevel(root.getLeft()) + " | level right: "+getMaxLevel(root.getRight()));
    }


    private int getMaxLevel(BSTNode node){
        if(node==null){
            return 0;
        }
        return node.getMaxLevel();
    }


}



