package br.com.goldencode.softsports;

import java.sql.Date;
import java.sql.Time;

public class Evento {

    private String nomeEvento;
    private java.sql.Date dataCriacao;
    private java.sql.Date dataEvento;
    private String esporte;
    private String observacoes;
    private String local;
    private Time hr_inicio;
    private Time hr_termino;
    private int nr_participantes;

    public Evento(){}

    public Evento(String nomeEvento, Date dataCriacao, Date dataEvento, String observacoes, String local, Time hr_inicio, Time hr_termino, int nr_participantes) {
        this.nomeEvento = nomeEvento;
        this.dataCriacao = dataCriacao;
        this.dataEvento = dataEvento;
        this.observacoes = observacoes;
        this.local = local;
        this.hr_inicio = hr_inicio;
        this.hr_termino = hr_termino;
        this.nr_participantes = nr_participantes;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getEsporte() {
        return esporte;
    }

    public void setEsporte(String esporte) {
        this.esporte = esporte;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Time getHr_inicio() {
        return hr_inicio;
    }

    public void setHr_inicio(Time hr_inicio) {
        this.hr_inicio = hr_inicio;
    }

    public Time getHr_termino() {
        return hr_termino;
    }

    public void setHr_termino(Time hr_termino) {
        this.hr_termino = hr_termino;
    }

    public int getNr_participantes() {
        return nr_participantes;
    }

    public void setNr_participantes(int nr_participantes) {
        this.nr_participantes = nr_participantes;
    }
}
