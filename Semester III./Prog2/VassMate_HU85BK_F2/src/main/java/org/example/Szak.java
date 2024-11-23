package org.example;

public class Szak implements Comparable{
    private String nev;
    private  String kod;
    private int felevekSzama;

    public Szak(String nev, String kod, int felevekSzama) {
        this.nev = nev;
        this.kod = kod;
        this.felevekSzama = felevekSzama;
    }

    public String getNev() {
        return nev;
    }

    public String getKod() {
        return kod;
    }

    public int getFelevekSzama() {
        return felevekSzama;
    }

    @Override
    public String toString() {
        return "Szak{" +
                "nev='" + nev + '\'' +
                ", kod='" + kod + '\'' +
                ", felevekSzama=" + felevekSzama +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public int compareTo(Szak o) {
        if(o.kod.equals(this.kod)){
            return 1;
        }
        return 0;
    }
}
