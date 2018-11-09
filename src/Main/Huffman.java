package Main;

import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Huffman {

    private ArrayList<Node> leaves;
    private ArrayList<Pair> pairs;
    private Node root;
    private HashMap codes;
    private StringBuffer code;

    public Huffman(ArrayList<Pair> pairs){
        codes = new HashMap();
        this.pairs =  pairs;
        createLeavesList();
        createTree();
        createHash();
    }

    private void createHash(){

        code = new StringBuffer();

        traverseNodes (root);

    }

    private void traverseNodes (Node n){ // percorrendo a arvore e mostrando a raiz e depois os filhos

        if (n != null){
            if(n instanceof ExternalNode){

                codes.put(((ExternalNode) n).getSymbol(), code.toString());

            }
            else{

            if (n instanceof InternalNode){
                InternalNode internal = (InternalNode) n;
                if (internal.getLeft() != null){
                    code.append('0');
                    traverseNodes(internal.getLeft());
                }
                if (internal.getRight() != null){
                    code.append('1');
                    traverseNodes(internal.getRight());}
            }

            }
            if (code.length() > 0) {
                code.deleteCharAt(code.length() - 1);
            }
        }

    }


    private void sort(){

        leaves.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {

                if(o1.getFreq() < o2.getFreq())
                    return -1;

                else if(o1.getFreq() > o2.getFreq())
                    return 1;

                else
                    return 0;
            }
        });

    }

    private void createTree(){

        while (leaves.size() > 1){ //quando tiver mais de um elemento

            sort();

            Node n0 = leaves.get(0);
            Node n1 = leaves.get(1);

            InternalNode newNode = new InternalNode(n0.getFreq() + n1.getFreq(),n0,n1);

            leaves.remove(0);
            leaves.remove(0);

            leaves.add(newNode);

        }

        root = leaves.get(0); // raiz principal

    }

    private void createLeavesList(){

        leaves =  new ArrayList<>();

        for(Pair p : pairs)
            leaves.add(new ExternalNode((int)p.getValue(),(char)p.getKey()));
    }

    private void printNode(Node n){ // percorrendo a arvore e mostrando a raiz e depois os filhos

        if (n != null){
            System.out.println(n.getFreq());
            if (n instanceof InternalNode){
                InternalNode internal = (InternalNode) n;
                if (internal.getLeft() != null)
                    printNode(internal.getLeft());
                if (internal.getRight() != null)
                    printNode(internal.getRight());
            }
        }

    }


    public String compress(String input){

        StringBuffer sb = new StringBuffer(); //string bits

        for (int i = 0; i < input.length(); i++){

            sb.append(codes.get(input.charAt(i)));

        }

       return sb.toString();
    }

    public void writeFile (String data){

        int size = data.length();

        StringBuffer sb = new StringBuffer();
        sb.append(data);

        try {
            FileOutputStream fos = new FileOutputStream("compressed.txt");
            int i = 0;
            while (i < size) {
                byte b = Byte.parseByte(sb.substring(i, i+8),2);
                fos.write(b);
                i += 8;
            }

            fos.flush();
            fos.close();

            }
            catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }   catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    }


