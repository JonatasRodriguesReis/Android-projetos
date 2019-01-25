package model;

import java.io.Serializable;

/**
 * Created by francisco.pereira on 26/01/2018.
 */

public class Data implements Serializable{
    private int id;
    private String no;
    private String mes;
    private String dia;


    public Data(int id, String dia, String mes,String no) {
        this.id = id;
        this.no = no;
        this.mes = mes;
        this.dia = dia;
    }

    public Data(String dia, String mes,String no ) {
        this.no = no;
        this.mes = mes;
        this.dia = dia;
    }

    public Data(){
        this.dia = "";
        this.mes = "";
        this.no = "";
    }

    public String getNo() {
        return no;
    }

    public int getId() {
        return id;
    }

    public String getMes() {
        return mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia){
        this.dia = dia;
    }

    public void setMes(String mes){
        this.mes = mes;
    }

    public void setNo(String ano){
        this.no = ano;
    }
}
