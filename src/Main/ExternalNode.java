package Main;

public class ExternalNode extends Node {

    private char symbol;

    public ExternalNode (int freq, char symbol){

        super(freq);

        this.symbol= symbol;

    }

    public char getSymbol() {
        return symbol;
    }
}
