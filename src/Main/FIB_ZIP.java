package Main;

import javafx.util.Pair;

import java.util.ArrayList;

public class FIB_ZIP {



    public static void main(String args[]){

        ArrayList<Pair>  lista = new ArrayList<>();

        lista.add(new Pair('$',3));
        lista.add(new Pair('#',10));
        lista.add(new Pair('-',20));
        lista.add(new Pair('+',22));
        lista.add(new Pair('@',12));
        lista.add(new Pair('/',15));
        lista.add(new Pair('*',18));

        Huffman huffman = new Huffman(lista);

//        System.out.println(huffman.compress("-@/+*"));

        huffman.writeFile("0010011001111000");




    }



}
