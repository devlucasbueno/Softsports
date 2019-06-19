package br.com.goldencode.softsports;

public class Usuario {

    private int codUsuario;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String esporteFavorito;
    private String midiasSociais;

    public Usuario(){ }

    public Usuario(String nome, String sobrenome, String email, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEsporteFavorito() {
        return esporteFavorito;
    }

    public void setEsporteFavorito(String esporteFavorito) {
        this.esporteFavorito = esporteFavorito;
    }

    public String getMidiasSociais() {
        return midiasSociais;
    }

    public void setMidiasSociais(String midiasSociais) {
        this.midiasSociais = midiasSociais;
    }
}
