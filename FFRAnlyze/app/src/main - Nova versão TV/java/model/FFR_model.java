package model;

import java.io.Serializable;

/**
 * Created by francisco.pereira on 26/01/2018.
 */

public class FFR_model implements Serializable{
    private Data dataAtual;
    private Data dataAnterior;
    private NomeProduto nomeProduto;
    private LinhaProduto linhaProduto;
    private float ffr_Anterior;
    private float ffr_Atual;
    private float improved;


    public FFR_model(Data dataAtual, Data dataAnterior, NomeProduto nomeProduto, LinhaProduto linhaProduto, float ffr_anterior, float ffr_atual, float improved) {
        this.dataAtual = dataAtual;
        this.dataAnterior = dataAnterior;
        this.nomeProduto = nomeProduto;
        this.linhaProduto = linhaProduto;
        this.ffr_Anterior = ffr_anterior;
        this.ffr_Atual = ffr_atual;
        this.improved = improved;
    }

    public Data getDataAtual() {
        return dataAtual;
    }

    public Data getDataAnterior() {
        return dataAnterior;
    }

    public NomeProduto getNomeProduto() {
        return nomeProduto;
    }

    public LinhaProduto getLinhaProduto() {
        return linhaProduto;
    }

    public float getFfr_Anterior() {
        return ffr_Anterior;
    }

    public float getFfr_Atual() {
        return ffr_Atual;
    }

    public float getImproved() {
        return improved;
    }
}
