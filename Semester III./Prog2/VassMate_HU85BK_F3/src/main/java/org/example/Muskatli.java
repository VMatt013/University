package org.example;

public class Muskatli extends Virag {
    private boolean futoTipusu;

    public Muskatli(String nev, String sziromSzine,boolean futoTipusu) {
        super(nev,sziromSzine);
        this.futoTipusu = futoTipusu;
    }

    public boolean isFutoTipusu() {
        return futoTipusu;
    }
}
