package br.com.goldencode.softsports;

import java.util.ArrayList;
import java.util.List;

public class Evento {

    private int cod_evento;
    private String nomeEvento;

    public Evento(String nomeEvento, String esporte, String dataCriacao) {
        this.nomeEvento = nomeEvento;
        this.esporte = esporte;
        this.dataCriacao = dataCriacao;
    }

    public Evento(String nomeEvento, String esporte, String dataCriacao, String dataEvento, String observacoes, String local, String hr_inicio, String hr_termino) {
        this.nomeEvento = nomeEvento;
        this.esporte = esporte;
        this.dataCriacao = dataCriacao;
        this.dataEvento = dataEvento;
        this.observacoes = observacoes;
        this.local = local;
        this.hr_inicio = hr_inicio;
        this.hr_termino = hr_termino;
    }

    private String esporte;
    private String dataCriacao;
    private String dataEvento;
    private int fk_esporte;
    private String observacoes;
    private String local;
    private String hr_inicio;
    private String hr_termino;
    private int nr_participantes;

    public Evento(){}

    public Evento(String nomeEvento, String dataCriacao, String dataEvento, int fk_esporte, String observacoes, String local, String hr_inicio, String hr_termino, int nr_participantes) {
        this.nomeEvento = nomeEvento;
        this.dataCriacao = dataCriacao;
        this.dataEvento = dataEvento;
        this.fk_esporte = fk_esporte;
        this.observacoes = observacoes;
        this.local = local;
        this.hr_inicio = hr_inicio;
        this.hr_termino = hr_termino;
        this.nr_participantes = nr_participantes;
    }

    public Evento(int cod_evento, String nomeEvento, String dataCriacao, String dataEvento, int fk_esporte, String observacoes, String local, String hr_inicio, String hr_termino, int nr_participantes) {
        this.cod_evento = cod_evento;
        this.nomeEvento = nomeEvento;
        this.dataCriacao = dataCriacao;
        this.dataEvento = dataEvento;
        this.fk_esporte = fk_esporte;
        this.observacoes = observacoes;
        this.local = local;
        this.hr_inicio = hr_inicio;
        this.hr_termino = hr_termino;
        this.nr_participantes = nr_participantes;
    }

    public String getEsporte() {
        return esporte;
    }

    public void setEsporte(String esporte) {
        this.esporte = esporte;
    }

    public int getCod_evento() {
        return cod_evento;
    }

    public void setCod_evento(int cod_evento) {
        this.cod_evento = cod_evento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public int getFk_esporte() {
        return fk_esporte;
    }

    public void setFk_esporte(int fk_esporte) {
        this.fk_esporte = fk_esporte;
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

    public String getHr_inicio() {
        return hr_inicio;
    }

    public void setHr_inicio(String hr_inicio) {
        this.hr_inicio = hr_inicio;
    }

    public String getHr_termino() {
        return hr_termino;
    }

    public void setHr_termino(String hr_termino) {
        this.hr_termino = hr_termino;
    }

    public int getNr_participantes() {
        return nr_participantes;
    }

    public void setNr_participantes(int nr_participantes) {
        this.nr_participantes = nr_participantes;
    }

    public static List<Evento> getEvento(Evento evento){
        List<Evento> ArrayListEvento = new ArrayList<>();

        ArrayListEvento.add(new Evento(evento.getNomeEvento(), evento.getDataCriacao(), evento.getDataEvento(), evento.getFk_esporte(),evento.getObservacoes(), evento.getLocal(), evento.getHr_inicio(), evento.getHr_termino(), evento.getNr_participantes()));

        return ArrayListEvento;

    }

}
