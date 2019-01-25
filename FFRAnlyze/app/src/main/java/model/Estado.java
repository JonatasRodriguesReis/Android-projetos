package model;

/**
 * Created by francisco.pereira on 26/01/2018.
 */

public class Estado {
    private int id;
    private String nome;

    public Estado(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Estado(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
