package Main;

public class InternalNode extends Node {

    private Node left, right;

    public InternalNode(int freq, Node left, Node right){

        super(freq);

        this.left = left;
        this.right = right;

    }

    public Node getLeft(){

        return  left;

    }

    public Node getRight(){

        return right;
    }

}
