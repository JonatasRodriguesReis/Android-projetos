package model;

/**
 * Created by Jonatas Reis on 04/02/2018.
 */

public class FFR {
    private String Atual;
    private String Anterior;
    private String Improved;


    public FFR(String atual, String anterior, String improved) {
        Atual = atual;
        Anterior = anterior;
        Improved = improved;
    }

    public String getAtual() {
        return Atual;
    }

    public String getAnterior() {
        return Anterior;
    }

    public String getImproved() {
        return Improved;
    }
}
