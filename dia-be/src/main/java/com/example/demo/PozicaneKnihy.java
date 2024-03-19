package com.example.demo;

public class PozicaneKnihy {
    int id;
    int clovekId;
    int knihaId;

    public PozicaneKnihy(int id, int clovekId, int knihaId) {
        this.id = id;
        this.clovekId = clovekId;
        this.knihaId = knihaId;
    }

    public int getKniha() {
        return knihaId;
    }

    public int getLudia() {
        return clovekId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClovekId(int clovekId) {
        this.clovekId = clovekId;
    }

    public void setKnihaId(int knihaId) {
        this.knihaId = knihaId;
    }

//    public void setLudia(Ludia ludia) {
//        this.ludia = ludia;
//    }
//
//    public void setKniha(Kniha kniha) {
//        this.kniha = kniha;
//    }
    //public String getNazovKnihy(){return this.kniha.nazov;}
    //public int getIdKnihy(){return this.kniha.id;}
    //public String getMenoAutora(){return this.kniha.autor;}
    //public String getMenoPoziciavatela(){return this.ludia.meno;}
}
