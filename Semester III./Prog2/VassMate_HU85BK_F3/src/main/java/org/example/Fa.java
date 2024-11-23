package org.example;

public class Fa implements Noveny {
    private String termes;
    private boolean orokzold;
    private boolean fotoszintetizal;

    public String getTermes() {
        return termes;
    }

    public boolean isOrokzold() {
        return orokzold;
    }

    public Fa(String termes, boolean orokzold) {
        this.termes = termes;
        this.orokzold = orokzold;
    }

    @Override
    public boolean isFotoszintetizal() {
        return true;
    }

    public void setFotoszintetizal(boolean fotoszintetizal) {
        this.fotoszintetizal = fotoszintetizal;
    }
}
