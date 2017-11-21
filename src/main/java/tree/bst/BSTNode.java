package tree.bst;

/**
 * Created by igorhara on 20/11/2017.
 */
public class BSTNode {

    Integer value;
    Level maxLevel = null;

    BSTNode parent,left,right;


    public BSTNode(Integer v){
        this.value = v;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BSTNode getParent() {
        return parent;
    }

    public void setParent(BSTNode parent) {
        this.parent = parent;
        if(parent!=null){
            this.maxLevel = parent.maxLevel;
            if(this.maxLevel==null){
                this.maxLevel = new Level();
            }

        }else{
            this.maxLevel=null;
        }

    }

    public BSTNode getLeft() {

        return left;
    }

    public void setLeft(BSTNode left) {
        if(left!=null){
            if (left.value>=this.value){
                throwError(left);
            }
            left.setParent(this);

        }
        this.left = left;
        if(this.right==null && this.left!=null && this.maxLevel!=null){
            this.maxLevel.addLevel();
        }
    }

    private void throwError(BSTNode node) {
        throw new RuntimeException("invalid value set in the left:"+node.value+ "this value: "+this.value);
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        if(right!=null){
            if (right.value<this.value ) {
                throwError(right);
            }
            right.setParent(this);
        }
        this.right = right;
        if(this.left==null && this.right!=null && this.maxLevel!=null){
            this.maxLevel.addLevel();
        }
    }

    public void addNoteChild(BSTNode node){
        if(node ==null) {
            return;
        }
        if(node.value>=this.value){
            if(this.right==null){
                this.setRight(node);
            }else{
                this.right.addNoteChild(node);
            }
        }else{
            if(this.left==null){
                this.setLeft(node);
            }else{
                this.left.addNoteChild(node);
            }
        }

    }

    public int recalculateMaxLevel(){
        this.maxLevel.clearLevel();
        this.maxLevel.level = checkMaxLevel(this.maxLevel);
        return this.maxLevel.getLevel();
    }

    private int checkMaxLevel(Level maxLevel){
        this.maxLevel = maxLevel;
        int maxLevelLeft = this.getLeft()!=null?this.getLeft().checkMaxLevel(maxLevel):0;
        int maxLevelRight = this.getRight()!=null?this.getRight().checkMaxLevel(maxLevel):0;
        int max = maxLevelLeft >maxLevelRight ? maxLevelLeft:maxLevelRight;
        return max+1;
    }

    public BSTNode getNodeWithValue(int value){
        if(this.value==value){
            return this;
        }else if(this.value > value){
            return this.getLeft()!=null? this.getLeft().getNodeWithValue(value):null;
        }else{
            return this.getRight()!=null? this.getRight().getNodeWithValue(value):null;
        }
    }


    public boolean contains(int value){
        return this.getNodeWithValue(value)!=null;
    }


    public int getMaxLevel(){
        return this.maxLevel!=null?this.maxLevel.getLevel():0;
    }

    public class Level{
          int level = 0;

          public int getLevel(){
              return this.level;
          }

          public int addLevel(){
              this.level++;
              return level;
          }

          public void clearLevel(){
              this.level = 0;
          }
    }

}



