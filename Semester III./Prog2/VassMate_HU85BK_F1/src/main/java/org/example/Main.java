package org.example;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        Scanner sc = new Scanner(system.in);
        TreeSet<Cipo> Cipok = new TreeSet<Cipo>();
        C
        for(int i = 0; i < n; i++){
            String l = sc.nextLine();
            String line = "123;10.1;kék;tél";
            String[] tmp = line.split(";");
            Cipok.add(new Cipo(tmp[0],Double.parseDouble(tmp[1]), tmp[2], tmp[3]));
        }
        for(Cipo i : Cipok){
            System.out.println(i);
        }
    }
}