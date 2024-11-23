package org.example;

public class Tantargy {
    private String kod;
    private String nev;
    private int feleve;
    private int kredit;
    private Szak szak;

    public Tantargy(String kod, String nev, int feleve, int kredit, Szak szak) {
        this.kod = kod;
        this.nev = nev;
        this.feleve = feleve;
        this.kredit = kredit;
        this.szak = szak;
    }

    public String getKod() {
        return kod;
    }

    public String getNev() {
        return nev;
    }

    public int getFeleve() {
        return feleve;
    }

    public int getKredit() {
        return kredit;
    }

    public Szak getSzak() {
        return szak;
    }

    @Override
    public String toString() {
        return "Tantargy{" +
                "kod='" + kod + '\'' +
                ", nev='" + nev + '\'' +
                ", feleve=" + feleve +
                ", kredit=" + kredit +
                ", szak=" + szak +
                '}';
    }
}
