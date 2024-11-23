package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Szak szak = new Szak("szakNev","001",2);
        ArrayList<Tantargy> targyak = new ArrayList<Tantargy>();
        for(int i = 1; i < 11; i++){
            targyak.add(new Tantargy("000"+(char)i,"Nev"+(char)i,1,20,szak));
        }
        targyak.sort(Comparator.comparing(Szak::nev).thenComparing(Szak::kod));
        TreeMap<Szak,ArrayList<Tantargy>> list = new TreeMap<Szak,ArrayList<Tantargy>>();
        list.put(szak,targyak);
    }
}

