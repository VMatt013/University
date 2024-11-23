package org.example;

public class Hovirag extends Virag implements VedettNoveny {

    private int birosagErteke;

    public Hovirag(int birosagErteke) {
        super("Hóvirág","Fehér");
        this.birosagErteke = birosagErteke;
    }

    @Override
    public boolean isFotoszintetizal() {
        return true;
    }

    @Override
    public int getBirsagErteke() {
        return this.birosagErteke;
    }
}
