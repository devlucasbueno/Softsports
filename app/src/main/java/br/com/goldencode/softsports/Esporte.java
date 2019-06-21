package br.com.goldencode.softsports;

public class Esporte {

    private int cod_esporte;
    private String nome_esporte;

    public Esporte(String nome_esporte) {
        this.nome_esporte = nome_esporte;
    }

    public Esporte(int cod_esporte, String nome_esporte) {
        this.cod_esporte = cod_esporte;
        this.nome_esporte = nome_esporte;
    }

    public int getCod_esporte() {
        return cod_esporte;
    }

    public void setCod_esporte(int cod_esporte) {
        this.cod_esporte = cod_esporte;
    }

    public String getNome_esporte() {
        return nome_esporte;
    }

    public void setNome_esporte(String nome_esporte) {
        this.nome_esporte = nome_esporte;
    }

}
