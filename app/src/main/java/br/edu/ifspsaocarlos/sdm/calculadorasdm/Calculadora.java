package br.edu.ifspsaocarlos.sdm.calculadorasdm;

import android.util.Log;

import br.edu.ifspsaocarlos.sdm.calculadorasdm.servicos.RaizQuadrada;

public class Calculadora {
    private static final Calculadora ourInstance = new Calculadora();
    private float operando;
    private float operandoPorcentagem;
    private int operacaoAnterior;
    private int esperaProximoValor = 0;
    private int qualOperacaoPorcentagem;

    public static final int RESULTADO = -1;
    public static final int ADICAO = 0;
    public static final int SUBTRACAO = 1;
    public static final int MULTIPLICACAO = 2;
    public static final int DIVISAO = 3;
    public static final int PORCENTAGEM = 4;
    public static final int LIMPARTELA = 5;
    public static final int RAIZ_QUADRADA = 6;
    public static Calculadora getInstance() {
        return ourInstance;
    }
    private Calculadora() {
        operando = 0.0f;
        //operacao = RESULTADO;
    }

    public float calcula(float valor, int operacao) {
        //Log.i("calcula ",String.valueOf(operacao));
        if(operacao==LIMPARTELA){
            operando = 0.0f;
            esperaProximoValor = 0;
        }
        else if(operacao==RAIZ_QUADRADA){
            RaizQuadrada rq = new RaizQuadrada();
            operando = rq.calcularRaiz(valor);
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
            operandoPorcentagem = valor;
        }

        return operando;
    }

    public void resultado(float valor){
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
        else if(operacaoAnterior==PORCENTAGEM) {
            if(qualOperacaoPorcentagem==SUBTRACAO){
                operando = operandoPorcentagem - operando;
            }
            else if(qualOperacaoPorcentagem==ADICAO){
                operando = operandoPorcentagem + operando;
            }
            else if(qualOperacaoPorcentagem==DIVISAO){
                operando = operandoPorcentagem / operando;
            }
            else if(qualOperacaoPorcentagem==MULTIPLICACAO){
                operando = operandoPorcentagem * operando;
            }
        }
        esperaProximoValor = 1;
    }

    public void calcular(float valor, int operacao){
        Log.i("operandoPorcentagem calc ", String.valueOf(operandoPorcentagem));
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
            else if(operacao==PORCENTAGEM){
                operando = (operandoPorcentagem*valor)/100;
                qualOperacaoPorcentagem = operacaoAnterior;
                operacaoAnterior = PORCENTAGEM;
            }
        }else{
            esperaProximoValor = 0;
            operacaoAnterior = operacao;
        }
    }

}
