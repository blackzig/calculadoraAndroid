package br.edu.ifspsaocarlos.sdm.calculadorasdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.edu.ifspsaocarlos.sdm.calculadorasdm.servicos.Soma;

public class CalculadoraSDMActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView lcd;
    private Button zeroBtn, umBtn, doisBtn, tresBtn, quatroBtn, cincoBtn,
            seisBtn, seteBtn, oitoBtn, noveBtn;
    private Button adicaoBtn, subtracaoBtn, multiplicacaoBtn, divisaoBtn,
            resultadoBtn, pontoBtn, porcetagemBtn, limpezaTotalBtn,
            limpezaParcialBtn, raizQuadradaBtn;
    private boolean concatenaLcd;
    private String resultado;
    private Calculadora calculadora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_sdm);
        lcd = (TextView) findViewById(R.id.lcd);
        lcd.setText(getString(R.string.zero));
        concatenaLcd = false;
        calculadora = Calculadora.getInstance();
        zeroBtn = (Button) findViewById(R.id.btn_zero);
        zeroBtn.setOnClickListener(this);
        umBtn = (Button) findViewById(R.id.btn_um);
        umBtn.setOnClickListener(this);
        doisBtn = (Button) findViewById(R.id.btn_dois);
        doisBtn.setOnClickListener(this);
        tresBtn = (Button) findViewById(R.id.btn_tres);
        tresBtn.setOnClickListener(this);
        quatroBtn = (Button) findViewById(R.id.btn_quatro);
        quatroBtn.setOnClickListener(this);
        cincoBtn = (Button) findViewById(R.id.btn_cinco);
        cincoBtn.setOnClickListener(this);
        seisBtn = (Button) findViewById(R.id.btn_seis);
        seisBtn.setOnClickListener(this);
        seteBtn = (Button) findViewById(R.id.btn_sete);
        seteBtn.setOnClickListener(this);
        oitoBtn = (Button) findViewById(R.id.btn_oito);
        oitoBtn.setOnClickListener(this);
        noveBtn = (Button) findViewById(R.id.btn_nove);noveBtn.setOnClickListener(this);
        adicaoBtn = (Button) findViewById(R.id.btn_adicao);
        adicaoBtn.setOnClickListener(this);
        subtracaoBtn = (Button) findViewById(R.id.btn_subtracao);
        subtracaoBtn.setOnClickListener(this);
        multiplicacaoBtn = (Button) findViewById(R.id.btn_multiplicacao);
        multiplicacaoBtn.setOnClickListener(this);
        divisaoBtn = (Button) findViewById(R.id.btn_divisao);
        divisaoBtn.setOnClickListener(this);
        pontoBtn = (Button) findViewById(R.id.btn_ponto);
        pontoBtn.setOnClickListener(this);
        resultadoBtn = (Button) findViewById(R.id.btn_resultado);
        resultadoBtn.setOnClickListener(this);
        porcetagemBtn = (Button) findViewById(R.id.btn_porcentagem);
        porcetagemBtn.setOnClickListener(this);
        limpezaTotalBtn = (Button) findViewById(R.id.btn_limpeza_total);
        limpezaTotalBtn.setOnClickListener(this);
        limpezaParcialBtn = (Button) findViewById(R.id.btn_limpeza_parcial);
        limpezaParcialBtn.setOnClickListener(this);
        raizQuadradaBtn = (Button) findViewById(R.id.btn_raiz_quadrada);
        raizQuadradaBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Button botao = (Button) view;
        if (botao == zeroBtn || botao == umBtn || botao == doisBtn ||
                botao == tresBtn || botao == quatroBtn || botao == cincoBtn ||
                botao == seisBtn || botao == seteBtn || botao == oitoBtn ||
                botao == noveBtn) {
            if (!concatenaLcd) {
                lcd.setText("");
            }
            lcd.append(String.valueOf(botao.getText().toString()));
            concatenaLcd = true;
        }
        else {
            if (botao == pontoBtn) {
                if (!lcd.getText().toString().contains(getString(R.string.ponto))){
                    if (!concatenaLcd) {
                        lcd.setText(getString(R.string.zero));
                    }
                    lcd.append(getString(R.string.ponto));
                    concatenaLcd = true;
                }
            }
            else {
                if (botao == adicaoBtn) {
                    lcd.setText(Float.toString(calculadora.calcula(
                            Float.parseFloat(lcd.getText().toString()),
                            Calculadora.ADICAO)));
                    concatenaLcd = false;
                }
                else if (botao == subtracaoBtn) {
                    lcd.setText(Float.toString(calculadora.calcula(
                            Float.parseFloat(lcd.getText().toString()),
                            Calculadora.SUBTRACAO)));
                    concatenaLcd = false;
                }
                else if (botao == multiplicacaoBtn) {
                    lcd.setText(Float.toString(calculadora.calcula(
                            Float.parseFloat(lcd.getText().toString()),
                            Calculadora.MULTIPLICACAO)));
                    concatenaLcd = false;
                }
                else if (botao == divisaoBtn) {
                    lcd.setText(Float.toString(calculadora.calcula(
                            Float.parseFloat(lcd.getText().toString()),
                            Calculadora.DIVISAO)));
                    concatenaLcd = false;
                }
                else if (botao == porcetagemBtn) {
                    lcd.setText(Float.toString(calculadora.calcula(
                            Float.parseFloat(lcd.getText().toString()),
                            Calculadora.PORCENTAGEM)));
                    concatenaLcd = false;
                }
                else if (botao == resultadoBtn) {
                    lcd.setText(Float.toString(calculadora.calcula(
                            Float.parseFloat(lcd.getText().toString()),
                            Calculadora.RESULTADO)));
                    concatenaLcd = false;
                }
                else if (botao == limpezaTotalBtn) {
                    lcd.setText(Float.toString(calculadora.calcula(
                            Float.parseFloat(lcd.getText().toString()),
                            Calculadora.LIMPARTELA)));
                    lcd.setText("");
                    concatenaLcd = false;
                }
                else if (botao == limpezaParcialBtn) {
                    String valor = lcd.getText().toString();
                    lcd.setText(valor.substring (0, valor.length() - 1));
                }
                else if (botao == raizQuadradaBtn) {
                    lcd.setText(Float.toString(calculadora.calcula(
                            Float.parseFloat(lcd.getText().toString()),
                            Calculadora.RAIZ_QUADRADA)));
                    concatenaLcd = false;
                }
            }
        }
    }

}
