package org.example;

import java.util.Objects;

public class Cipo {
    private String modellszam;
    private double meret;
    private String szin;
    private String evszak;

    public Cipo(String modellszam, double meret, String szin, String evszak) {
        this.modellszam = modellszam;
        this.meret = meret;
        this.szin = szin;
        this.evszak = evszak;
    }

    @Override
    public String toString() {
        return "Cipo{" +
                "modellszam='" + modellszam + '\'' +
                ", meret=" + meret +
                ", szin='" + szin + '\'' +
                ", evszak='" + evszak + '\'' +
                '}';
    }

    public String getModellszam() {
        return modellszam;
    }

    public double getMeret() {
        return meret;
    }

    public String getSzin() {
        return szin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cipo cipo = (Cipo) o;
        return Double.compare(meret, cipo.meret) == 0 && Objects.equals(modellszam, cipo.modellszam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modellszam, meret);
    }

}