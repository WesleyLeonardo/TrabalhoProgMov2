package com.example.wesleyleonardodasilvatrabalho3.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


//faz o entity ter o campo login como chave primaria e nonnull para nao ser nulo

@Entity(indices = {@Index(value = {"login"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    private int idLogin;

    private String login;
    private String senha;
    private String nome;

    public User(String login, String senha, String nome) {
        setLogin(login);
        setSenha(senha);
        setNome(nome);
    }

    //Getters e Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }
}
