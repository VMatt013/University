package org.example;

public abstract class Virag implements Noveny {
    private String nev;
    private String sziromSzine;
    private boolean fotoszintetizal;

    protected Virag(String nev, String sziromSzine) {
        this.nev = nev;
        this.sziromSzine = sziromSzine;
    }
    public String getNev() {
        return nev;
    }

    public String getSziromSzine() {
        return sziromSzine;
    }

    public boolean isFotoszintetizal() {
        return fotoszintetizal;
    }

    public void setFotoszintetizal(boolean fotoszintetizal) {
        this.fotoszintetizal = fotoszintetizal;
    }
}
