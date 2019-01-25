package model;

/**
 * Created by francisco.pereira on 26/01/2018.
 */

public class LinhaProduto {
    private int id;
    private String nome;

    public LinhaProduto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public LinhaProduto( String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
