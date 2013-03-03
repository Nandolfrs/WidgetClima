package com.nandolfrs.widgetclima;

import com.nandolfrs.widgetclima.R;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyWidget extends AppWidgetProvider{
	
	//Instanciando classe cidade
	public static Cidade cidade = new Cidade();
	//Verifica se os dados ja carregaram
	public static boolean pronto;


	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		
		//Definindo quantidade de widget na tela
		ComponentName thisWidget = new ComponentName(context, MyWidget.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		
		//Percorrendo todas as instancias do widget
		for (int widgetId : allWidgetIds) {
			
			//Executou onUpdate
			Log.d("com.nandolfrs.widgetclima", "Metodo OnUpdate");
			
			//Cria a RemoteView para alterar o layout do widget
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
			
			//Bloco Try
	    	try {
				
				//Chamando metodos de atualização dos dados em segundo plano
	    		new Connection().execute();
	    		
	    		//Contador
				int count = 0;
				//Seta a variavel Pronto para false
	    		pronto = false;
	    		
	    		//Verifica se a atualização ja foi realizada
	    		while(pronto == false && count < 9) {
	    			
	    			//Espera um segundo
	    			Thread.sleep(1000);
	    			count++;
	    			
	    			//Dados carregando
	    			Log.d("com.nandolfrs.widgetclima", "Carregando...");
	    			
	    		}	
	    		
	    		//Verifica se a atualização ocorreu com sucesso
	    		if(pronto == true) {
		    		
	    			//Dados carregados
		    		Log.d("com.nandolfrs.widgetclima", "Dados carregados!");
		    		
					//Alterando layout	    		
					views.setTextViewText(R.id.cidade, cidade.getNome());
					views.setTextViewText(R.id.temperatura, cidade.getTemp());
					views.setTextViewText(R.id.data, cidade.getData());
					
					//Verifica condição para alterar icone
					switch(cidade.getCodigoInt()) {
						case 1: 
							views.setImageViewResource(R.id.icone, R.drawable.tempo_claro_dia);
							break;
						case 2:
							views.setImageViewResource(R.id.icone, R.drawable.tempo_claro_noite);
							break;
						case 3:
							views.setImageViewResource(R.id.icone, R.drawable.tempo_nublado_dia);
							break;
						case 4:
							views.setImageViewResource(R.id.icone, R.drawable.tempo_nublado_noite);
							break;
						case 5:
							views.setImageViewResource(R.id.icone, R.drawable.tempo_chuvoso);
							break;
						case 6:
							views.setImageViewResource(R.id.icone, R.drawable.tempo_raio);
							break;
						case 7:
							views.setImageViewResource(R.id.icone, R.drawable.tempo_nevando);
							break;
						case 8:
							views.setImageViewResource(R.id.icone, R.drawable.tempo_indefinido);
							break;
					}				
					
	    		} else { //Atualização mal sucedida
	    			
	    			//Dados não carregados
		    		Log.d("com.nandolfrs.widgetclima", "Dados não carregados");
		    		
		    		//Alterando layout		
					views.setTextViewText(R.id.cidade, "Cidade");
					views.setTextViewText(R.id.temperatura, "--º");
					views.setTextViewText(R.id.data, "Data");
					views.setImageViewResource(R.id.icone, R.drawable.tempo_indefinido);
					
					//Anulando campos da Classe Cidade
					cidade.setNome("Dados não carregados");
					cidade.setEstado("");
					cidade.setTemp2("--");
					cidade.setMax2("");
					cidade.setMin2("");
					cidade.setCondicao("Tempo desconhecido");
					cidade.setUmidade2("");
					cidade.setVento2("");
					
					//Exibe mensagem de problema durante a atualização
					Toast.makeText(context, "Ocorreu um problema durante o processo de atualização do WidgetClima!", Toast.LENGTH_LONG).show();
					
	    		}
				
	    	//Caso ocorra uma exceção
			} catch (Exception e) {
												
				//Exibe mensagem de problema durante a atualização
				Toast.makeText(context, "Ocorreu um problema durante o processo de atualização do WidgetClima!", Toast.LENGTH_LONG).show();
				e.printStackTrace();
				
			}
	    	
	    	//Intenção para abrir a activity de informacões
	        Intent it = new Intent("INFO");
	        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, it, 0);
	        views.setOnClickPendingIntent(R.id.widget, pendingIntent);
	        
	        //Gravando alterações de layout
	        appWidgetManager.updateAppWidget(widgetId, views);
	        
		}		        
	}
	
	
	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		Log.d("com.nandolfrs.widgetclima", "Metodo OnEnable");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		Log.d("com.nandolfrs.widgetclima", "Metodo OnReceive");
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Log.d("com.nandolfrs.widgetclima", "Metodo OnDeleted");
	}
	
	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		Log.d("com.nandolfrs.widgetclima", "Metodo OnDisabled");
	}
	
}
