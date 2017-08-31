package br.edu.ifspsaocarlos.sdm.calculadorasdm.servicos;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RaizQuadrada {
    //até 9.999 vão dar dois algarismo
    Float operando = 0.0f;
    Integer potencias[] = {1,4,9,16,25,36,49,64,81};
    Integer contador = 1;
    Integer algarismoDezena = 0;
    Integer algarismoUnidade = 0;
    List<String> grupoDeNumeros = new ArrayList<>();

    public Float calcularRaiz(float valor){

        String valorSTR = String.valueOf(valor);
        int posicaoDoPonto = valorSTR.indexOf(".");

        String valoresDepoisDoPonto = valorSTR.substring(posicaoDoPonto+1,valorSTR.length());
        String valoresAntesDoPonto = valorSTR.substring(0,posicaoDoPonto);
        Integer quantidadeDeNumeros = valoresAntesDoPonto.length();


        if(quantidadeDeNumeros==1){

        }else{
            String ultimoNumeroAntesDoPonto = valorSTR.substring(valoresAntesDoPonto.length()-1,valoresAntesDoPonto.length());
            String primeiroNumero = null;

            if(quantidadeDeNumeros==3){
                primeiroNumero = valorSTR.substring(0,1);
                calcularRaizDeNumerosDeTreEQuatroDigitos(primeiroNumero, ultimoNumeroAntesDoPonto, valoresDepoisDoPonto);
            }else if(quantidadeDeNumeros==4){
                primeiroNumero = valorSTR.substring(0,2);
                calcularRaizDeNumerosDeTreEQuatroDigitos(primeiroNumero, ultimoNumeroAntesDoPonto, valoresDepoisDoPonto);
            }else{
                numerosComMaisDeQuatroDigitos(valoresAntesDoPonto, valoresDepoisDoPonto);
            }
        }

        return operando;
    }

    private void calcularRaizDeNumerosDeTreEQuatroDigitos(String primeiroNumero, String ultimoNumeroAntesDoPonto, String valoresDepoisDoPonto){
        Integer primeiroNumeroInt = Integer.parseInt(primeiroNumero);

        if(valoresDepoisDoPonto.equals("0")){
            //raízes não exatas
            if(ultimoNumeroAntesDoPonto.equals("2") || ultimoNumeroAntesDoPonto.equals("3") ||
                    ultimoNumeroAntesDoPonto.equals("7") || ultimoNumeroAntesDoPonto.equals("8")){

            }else{
                //raízes exatas
                calcularRaiz(primeiroNumeroInt, ultimoNumeroAntesDoPonto);
            }
        }
    }

    private void calcularRaiz(Integer primeiroNumeroInt, String ultimoNumeroAntesDoPonto){
        for(Integer i: potencias){
            if(i<primeiroNumeroInt){
                algarismoDezena = contador++;
            }else{
                break;
            }
        }

        if(ultimoNumeroAntesDoPonto.equals("1")){
            numeroTerminaComUm(primeiroNumeroInt);
        }
        else if(ultimoNumeroAntesDoPonto.equals("4")){
            numeroTerminaComQuatro(primeiroNumeroInt);
        }
        else if(ultimoNumeroAntesDoPonto.equals("5")){
            algarismoUnidade = 5;
        }
        else if(ultimoNumeroAntesDoPonto.equals("6")){
            numeroTerminaComSeis(primeiroNumeroInt);
        }
        else if(ultimoNumeroAntesDoPonto.equals("9")){
            numeroTerminaComNove(primeiroNumeroInt);
        }

        String algarismoDezenaSTR = String.valueOf(algarismoDezena);
        String algarismoUnidadeSTR = String.valueOf(algarismoUnidade);
        String juntarAlgarismos = algarismoDezenaSTR.concat(algarismoUnidadeSTR);
        operando = Float.parseFloat(juntarAlgarismos);
        contador = 1;
    }

    private void numeroTerminaComUm(Integer primeiroNumeroInt){
        Integer verificador = algarismoDezena*(algarismoDezena+1);
        if(primeiroNumeroInt<verificador){
            algarismoUnidade = 1;
        }else{
            algarismoUnidade = 9;
        }
    }

    private void numeroTerminaComQuatro(Integer primeiroNumeroInt){
        Integer verificador = algarismoDezena*(algarismoDezena+1);
        if(primeiroNumeroInt<verificador){
            algarismoUnidade = 2;
        }else{
            algarismoUnidade = 8;
        }
    }

    private void numeroTerminaComSeis(Integer primeiroNumeroInt){
        Integer verificador = algarismoDezena*(algarismoDezena+1);
        if(primeiroNumeroInt<verificador){
            algarismoUnidade = 4;
        }else{
            algarismoUnidade = 6;
        }
    }

    private void numeroTerminaComNove(Integer primeiroNumeroInt){
        Integer verificador = algarismoDezena*(algarismoDezena+1);
        if(primeiroNumeroInt<verificador){
            algarismoUnidade = 3;
        }else{
            algarismoUnidade = 7;
        }
    }

    private void numerosComMaisDeQuatroDigitos(String valoresAntesDoPonto, String valoresDepoisDoPonto){
        //https://www.youtube.com/watch?v=3K9F9haugUI
        separarNumeroEmGrupos(valoresAntesDoPonto, valoresDepoisDoPonto);

        List<Integer> divisor = new ArrayList<>();
       // List<Integer> numerosParaDividir =

        for(String s: grupoDeNumeros){

            Integer contadorLocal = procurarNumeroEmPotencias(s);

            Log.i("divisor ", String.valueOf(contadorLocal));
            divisor.add(contadorLocal);

            Integer numeroParaSubtrair = potencias[contadorLocal-1];
            Log.i("numeroParaSubtrair ", String.valueOf(numeroParaSubtrair));

            String primeiroNumeroDoGrupo = grupoDeNumeros.get(0);
            Integer numeroDoGrupoInt = Integer.parseInt(primeiroNumeroDoGrupo);

            Integer resultadoSubtracao = numeroDoGrupoInt - numeroParaSubtrair;
            Log.i("resultadoSubtracao ",String.valueOf(resultadoSubtracao));

            String segundoNumeroDoGrupo = grupoDeNumeros.get(1);
            Integer segundoNumeroDoGrupoInt = Integer.parseInt(segundoNumeroDoGrupo);


        }

    }

    private Integer procurarNumeroEmPotencias(String s){
        Integer contadorLocal = 0;
        for(Integer i: potencias){
            Integer numero = Integer.parseInt(s);
            if(i<=numero){
                contadorLocal++;
            }else{
                break;
            }
        }

        return contadorLocal;
    }

    private void separarNumeroEmGrupos(String valoresAntesDoPonto, String valoresDepoisDoPonto){
        if(valoresDepoisDoPonto.equals("0")){
            Integer parOuImpar = valoresAntesDoPonto.length()%2;

            Integer primeiraRodada = 0;
            String grupo = null;

            if(parOuImpar==0){
                for(int i=0; i<valoresAntesDoPonto.length(); i++){
                    if(primeiraRodada==0){
                        primeiraRodada=1;
                        grupo = valoresAntesDoPonto.substring(0, 2);
                    }else{
                        grupo = valoresAntesDoPonto.substring(i, i+2);
                    }
                    i++;
                    grupoDeNumeros.add(grupo);
                }
            }else{
                for(int i=0; i<valoresAntesDoPonto.length(); i++){
                    if(primeiraRodada==0){
                        primeiraRodada=1;
                        grupo = valoresAntesDoPonto.substring(0, 1);
                    }else{
                        grupo = valoresAntesDoPonto.substring(i, i+2);
                        i++;
                    }
                    grupoDeNumeros.add(grupo);
                }
            }
        }
    }

}
