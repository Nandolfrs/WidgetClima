package com.nandolfrs.widgetclima;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends Activity {
	
	//TextViews do layout
	private TextView cidade;
	private TextView temp;
	private TextView min;
	private TextView max;
	private TextView condicao;
	private TextView umidade;
	private TextView vento;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        
        //Instanciando TextViews
        cidade = (TextView) findViewById(R.id.info_cidade);
        temp = (TextView) findViewById(R.id.info_temp);
        min = (TextView) findViewById(R.id.info_minima);
        max = (TextView) findViewById(R.id.info_maxima);
        condicao = (TextView) findViewById(R.id.info_condicao);
        umidade = (TextView) findViewById(R.id.info_umidade);
        vento = (TextView) findViewById(R.id.info_vento);
        
        
        //Setando valores da classe cidade nos TextViews
        
        if(MyWidget.cidade.getEstado() != null) {        	
        	cidade.setText(MyWidget.cidade.getNome() +" / " +MyWidget.cidade.getEstado());
        } else {
        	cidade.setText("Dados não carregados");
        }
        
        if(MyWidget.cidade.getTemp() != null) {
        	temp.setText(MyWidget.cidade.getTemp());
        } else {
        	temp.setText("--");
        }
        
        if(MyWidget.cidade.getMin() != null) {
        	min.append(" " +MyWidget.cidade.getMin());
        } else {
        	min.append("");
        }
        
        if(MyWidget.cidade.getMax() != null) {
        	max.append(" " +MyWidget.cidade.getMax());
        } else {
        	max.append("");
        }
        
        if(MyWidget.cidade.getCondicao() != null) {
        	condicao.setText(MyWidget.cidade.getCondicao());
        } else {
        	condicao.setText("Tempo desconhecido");
        }
        
        if(MyWidget.cidade.getUmidade() != null) {
        	umidade.append(" " +MyWidget.cidade.getUmidade());
        } else {
        	umidade.append("");
        }
        
        if(MyWidget.cidade.getVento() != null) {
        	vento.append(" " +MyWidget.cidade.getVento());
        } else {
        	vento.append("");
        }
        
    }
    
    //Metodo para exibir o texto do botão "Sobre"
    public void exibirSobre(View view) {
    	new AlertDialog.Builder(this).setTitle(R.string.app_name).setMessage(R.string.sobre).show();
    }
}
