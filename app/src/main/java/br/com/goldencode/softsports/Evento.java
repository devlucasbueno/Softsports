package br.com.goldencode.softsports;

public class Evento {

    //lembrar de setar a fk_esporte na classe de criar evento
    private int cod_evento;
    private int fk_esporte;
    private String titulo;
    private String esporte;
    private String local;
    private String dataEvento;
    private String hrInicio;
    private String hrTermino;
    private String descricao;

    //Construtores
    
    public Evento(String titulo, String esporte, String local, String dataEvento, String hrInicio, String hrTermino, String descricao) {
        this.titulo = titulo;
        this.esporte = esporte;
        this.local = local;
        this.dataEvento = dataEvento;
        this.hrInicio = hrInicio;
        this.hrTermino = hrTermino;
        this.descricao = descricao;
    }

    public Evento(int cod_evento, int fk_esporte, String titulo, String esporte, String local, String dataEvento, String hrInicio, String hrTermino, String descricao) {
        this.cod_evento = cod_evento;
        this.fk_esporte = fk_esporte;
        this.titulo = titulo;
        this.esporte = esporte;
        this.local = local;
        this.dataEvento = dataEvento;
        this.hrInicio = hrInicio;
        this.hrTermino = hrTermino;
        this.descricao = descricao;
    }

    //Getters and Setters
    public int getCod_evento() {
        return cod_evento;
    }

    public void setCod_evento(int cod_evento) {
        this.cod_evento = cod_evento;
    }

    public int getFk_esporte() {
        return fk_esporte;
    }

    public void setFk_esporte(int fk_esporte) {
        this.fk_esporte = fk_esporte;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEsporte() {
        return esporte;
    }

    public void setEsporte(String esporte) {
        this.esporte = esporte;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getHrInicio() {
        return hrInicio;
    }

    public void setHrInicio(String hrInicio) {
        this.hrInicio = hrInicio;
    }

    public String getHrTermino() {
        return hrTermino;
    }

    public void setHrTermino(String hrTermino) {
        this.hrTermino = hrTermino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
