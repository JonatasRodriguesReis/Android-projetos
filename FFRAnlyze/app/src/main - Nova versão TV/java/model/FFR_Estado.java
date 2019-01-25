package model;

import java.io.Serializable;

/**
 * Created by Manoel on 28/01/2018.
 */

public class FFR_Estado implements Serializable{
    private String estado;
    private String ffr_Atual;
    private String ffr_Anterior;
    private float improved;


    public FFR_Estado(String estado, String ffr_atual, String ffr_anterior) {
        this.estado = estado;
        ffr_Atual = ffr_atual;
        ffr_Anterior = ffr_anterior;
        this.calcular_Improved();
    }

    public String getFfr_Atual() {
        return ffr_Atual;
    }
    public void setFfr_Atual(String ffr_Atual) {
        this.ffr_Atual = ffr_Atual;
    }

    public String getFfr_Anterior() {
        return ffr_Anterior;
    }

    public void setFfr_Anterior(String ffr_anterior){
        this.ffr_Anterior = ffr_anterior;
    }

    public String getEstado() {
        return estado;
    }

    private void calcular_Improved(){
        float atual = Float.parseFloat(this.ffr_Atual);
        float anterior = Float.parseFloat(this.ffr_Anterior);
        float diferenca = anterior - atual;
        this.improved = (diferenca*100)/Float.parseFloat(this.ffr_Anterior);
    }

    public float getImproved() {
        return improved;
    }
}
