package br.edu.ifspsaocarlos.sdm.calculadorasdm.servicos;

import android.util.Log;


public class Soma {

    private Float operando;

    public String somando(String valor){
        Log.i("Valor: ",valor);
        operando += Float.parseFloat(valor);
        String resultado = String.valueOf(operando);
        return resultado;
    }


}
