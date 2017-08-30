package br.edu.ifspsaocarlos.sdm.calculadorasdm;

import android.util.Log;

public class Calculadora {
    private static final Calculadora ourInstance = new Calculadora();
    private float operando;
    private float operandoPorcentagem;
    private int operacao;
    private int operacaoAnterior;
    private int calculoFinalPorcentagem = 0;
    private int esperaProximoValor = 0;

    public static final int RESULTADO = -1;
    public static final int ADICAO = 0;
    public static final int SUBTRACAO = 1;
    public static final int MULTIPLICACAO = 2;
    public static final int DIVISAO = 3;
    public static final int PORCENTAGEM = 4;
    public static final int LIMPARTELA = 5;
    public static Calculadora getInstance() {
        return ourInstance;
    }
    private Calculadora() {
        operando = 0.0f;
        //operacao = RESULTADO;
    }

    public float calcula(float valor, int operacao) {
        if(operacao==LIMPARTELA){
            Log.i("Limpou","memo");
            operando = 0.0f;
            esperaProximoValor = 0;
        }
        else if(operando!=0.0f){
            if(operacao==RESULTADO){
                resultado(valor);
            }else{
                calcular(valor, operacao);
            }
        }else{
            //só vai rodar aqui a primeira vez
            operando = valor;
            operacaoAnterior = operacao;
        }

        return operando;
    }

    public void resultado(float valor){
      //  Log.i("operacaoAnterior resultado ", String.valueOf(operacaoAnterior));
        if(operacaoAnterior==SUBTRACAO) {
            operando -= valor;
        }
        else if(operacaoAnterior==ADICAO) {
            operando += valor;
        }
        else if(operacaoAnterior==MULTIPLICACAO) {
            operando *= valor;
        }
        else if(operacaoAnterior==DIVISAO) {
            operando /= valor;
        }
        esperaProximoValor = 1;
    }

    public void calcular(float valor, int operacao){
      //  Log.i("operacao calcular ", String.valueOf(operacao));
        if (esperaProximoValor == 0) {
            if(operacao==SUBTRACAO){
                operando -= valor;
                operacaoAnterior = operacao;
            }
            else if(operacao==ADICAO){
                operando += valor;
                operacaoAnterior = operacao;
            }
            else if(operacao==MULTIPLICACAO){
                operando *= valor;
                operacaoAnterior = operacao;
            }
            else if(operacao==DIVISAO){
                operando /= valor;
                operacaoAnterior = operacao;
            }
        }else{
            esperaProximoValor = 0;
            operacaoAnterior = operacao;
        }
    }

    public float calculoFinalPorcentagem(int calculoFinalPorcentagem, int operacao, float valor){
        if(calculoFinalPorcentagem==1) {
            calculoFinalPorcentagem = 0;
            return operandoPorcentagem - operando;
        }else if(operando!=0.0f){
            //Log.i("operando switch ", String.valueOf(operando));
            //Log.i("this.operacao switch ", String.valueOf(this.operacao));
            //Log.i("operacao switch ", String.valueOf(operacao));
            if (operacao == -1) {
                Log.i("operacaoAnterior res ", String.valueOf(operacaoAnterior));
                Log.i("operacao res ", String.valueOf(operacao));
              //  operacaoAnterior = operacao;
               // this.operacao = operacao;
                //operando = valor;
                //perandoPorcentagem = valor;
                //return operando;
            }
            switch (operacaoAnterior) {
                case ADICAO:
                    operando += valor;
                    break;
                case SUBTRACAO:
                    Log.i("operando SUBTRACAO ", String.valueOf(operando));
                    operando -= valor;
                   //operacaoAnterior = -1;
                    break;
                case MULTIPLICACAO:
                    operando *= valor;
                    break;
                case DIVISAO:
                    operando /= valor;
                    break;
                default:
                    break;
            }
        }else{
            operando = valor;
            operacaoAnterior = operacao;
        }

        return operando;
    }
}
