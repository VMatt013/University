package org.example;

import java.time.LocalDate;

public class Konyv {
    private String konyvAzon;
    private String cim;
    private String isbn;
    private String kiado;
    private LocalDate kiadasDatuma;
    private Integer ar;
    private String tema;
    private Integer oldalszam;
    public Konyv(String konyvAzon, String cim, String isbn, String kiado, LocalDate kiadasDatuma, Integer ar, String tema,
                 Integer oldalszam) {
        super();
        this.konyvAzon = konyvAzon;
        this.cim = cim;
        this.isbn = isbn;
        this.kiado = kiado;
        this.kiadasDatuma = kiadasDatuma;
        this.ar = ar;
        this.tema = tema;
        this.oldalszam = oldalszam;
    }
    public String getKonyvAzon() {
        return konyvAzon;
    }
    public String getCim() {
        return cim;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getKiado() {
        return kiado;
    }
    public LocalDate getKiadasDatuma() {
        return kiadasDatuma;
    }
    public Integer getAr() {
        return ar;
    }
    public String getTema() {
        return tema;
    }
    public Integer getOldalszam() {
        return oldalszam;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Konyv [konyvAzon=");
        builder.append(konyvAzon);
        builder.append(", cim=");
        builder.append(cim);
        builder.append(", isbn=");
        builder.append(isbn);
        builder.append(", kiado=");
        builder.append(kiado);
        builder.append(", kiadasDatuma=");
        builder.append(kiadasDatuma);
        builder.append(", ar=");
        builder.append(ar);
        builder.append(", tema=");
        builder.append(tema);
        builder.append(", oldalszam=");
        builder.append(oldalszam);
        builder.append("]");
        return builder.toString();
    }



}

