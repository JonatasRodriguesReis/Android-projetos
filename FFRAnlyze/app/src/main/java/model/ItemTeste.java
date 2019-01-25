package model;

/**
 * Created by Manoel on 17/02/2018.
 */

public class ItemTeste {
    private String nome;
    private String anoAnterior;
    private String anoAtual;
    private String valorAnterior;
    private String valorAtual;
    private String improved;
    private String valorAnteriorS;
    private String valorAtualS;
    private String improvedS;

    public ItemTeste(String nome, String anoAnterior, String anoAtual, String valorAnterior, String valorAtual, String improved, String valorAnteriorS, String valorAtualS, String improvedS) {
        this.nome = nome;
        this.anoAnterior = anoAnterior;
        this.anoAtual = anoAtual;
        this.valorAnterior = valorAnterior;
        this.valorAtual = valorAtual;
        this.improved = improved;
        this.valorAnteriorS = valorAnteriorS;
        this.valorAtualS = valorAtualS;
        this.improvedS = improvedS;
    }



    public String getNome() {
        return nome;
    }

    public String getAnoAnterior() {
        return anoAnterior;
    }

    public String getAnoAtual() {
        return anoAtual;
    }

    public void setAnoAtual(String anoAtual) {
        this.anoAtual = anoAtual;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public String getValorAtual() {
        return valorAtual;
    }

    public String getImproved() {
        return improved;
    }

    public String getValorAnteriorS() {
        return valorAnteriorS;
    }

    public String getValorAtualS() {
        return valorAtualS;
    }

    public String getImprovedS() {
        return improvedS;
    }
}
