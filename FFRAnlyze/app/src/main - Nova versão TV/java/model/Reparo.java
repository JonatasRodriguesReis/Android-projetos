package model;

/**
 * Created by francisco.pereira on 26/01/2018.
 */

public class Reparo {
    private int id;
    private int idItem;
    private int idCidade;
    private int idEstado;
    private int idAutorizada;
    private int idNomeProduto;
    private int idLinha_Produto;

    public Reparo(int id, int idItem, int idCidade, int idEstado, int idAutorizada, int idNomeProduto, int idLinha_produto) {
        this.id = id;
        this.idItem = idItem;
        this.idCidade = idCidade;
        this.idEstado = idEstado;
        this.idAutorizada = idAutorizada;
        this.idNomeProduto = idNomeProduto;
        idLinha_Produto = idLinha_produto;
    }


    public int getId() {
        return id;
    }

    public int getIdItem() {
        return idItem;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public int getIdAutorizada() {
        return idAutorizada;
    }

    public int getIdNomeProduto() {
        return idNomeProduto;
    }

    public int getIdLinha_Produto() {
        return idLinha_Produto;
    }
}
