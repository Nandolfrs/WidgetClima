package com.nandolfrs.widgetclima;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import android.os.AsyncTask;


//Classe extendendo AsyncTask para atualização dos dados em segundo plano
public class Connection extends AsyncTask<Object, Object, Object> {
	
	//String para armazenar o conteudo do xml
	private String site;
	//String para armazenar o conteudo do xml
	private Document srcDoc;
	
	
	@Override
    protected Object doInBackground(Object... arg0) {
           
		try {
			
			//Dados carregando
			MyWidget.pronto = false;
			
			//String recebe o conteudo do site
			site = forecastYahooWeather();
			
			//Document recebe o conteudo da string
			srcDoc = convertStringToDocument(site);
			
			//Seta as variaveis da classe
			setAttributtes(srcDoc);
			
			//Dados carregados
			MyWidget.pronto = true;
				
			//Retornando
			return null;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
			
		}
           
	 }
	
	
	//Metodo para armazenar o conteudo do forecast Yahoo em uma string
	public String forecastYahooWeather(){
		
		//String para retornar o resultado
    	String result = "";
    	//String com a url da cidade
    	String path = "http://weather.yahooapis.com/forecastrss?w=455821";  	
    	
    	//Acessando a url
    	HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(path);
        
        //Armazenando conteudo da pagina na string result
        try {
        	HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
        	
        	if (httpEntity != null) {
        		InputStream inputStream = httpEntity.getContent();
        		Reader in = new InputStreamReader(inputStream);
        		BufferedReader bufferedreader = new BufferedReader(in);
        		StringBuilder stringBuilder = new StringBuilder();
        		
        		String stringReadLine = null;

        		while ((stringReadLine = bufferedreader.readLine()) != null) {
        			stringBuilder.append(stringReadLine + "\n");	
        		}
        		
        		result = stringBuilder.toString();	
        	}

		} catch (ClientProtocolException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        //Retorna a string com o resultado
        return result;
        
    }	
	
		
	//Metodo para converter a string em um Document
	public Document convertStringToDocument(String src){
				
		//Document que receberá o conteudo da string
    	Document dest = null;
    	
    	//Instanciando parser para conversão
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder parser;

    	//Convertendo string em Document
    	try {
    		parser = dbFactory.newDocumentBuilder();
			dest = parser.parse(new ByteArrayInputStream(src.getBytes()));
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//Retornando o Document
    	return dest;
    	
    }	
	
		
	//Metodo para percorrer o Document setando os atributos da classe
	public void setAttributtes(Document srcDoc) {
		
		//Percorre elemento location
		Node locationNode = srcDoc.getElementsByTagName("yweather:location").item(0);
		MyWidget.cidade.setNome(locationNode.getAttributes().getNamedItem("city").getNodeValue().toString());
		MyWidget.cidade.setEstado(locationNode.getAttributes().getNamedItem("region").getNodeValue().toString());
		
		//Percorre elemento wind
		Node windNode = srcDoc.getElementsByTagName("yweather:wind").item(0);		
		MyWidget.cidade.setVento(windNode.getAttributes().getNamedItem("speed").getNodeValue().toString());
		
		//Percorre elemento atmosphere
		Node atmosphereNode = srcDoc.getElementsByTagName("yweather:atmosphere").item(0);		
		MyWidget.cidade.setUmidade(atmosphereNode.getAttributes().getNamedItem("humidity").getNodeValue().toString());
		
		//Percorre elemento condition
		Node conditionNode = srcDoc.getElementsByTagName("yweather:condition").item(0);
		MyWidget.cidade.setTemp(conditionNode.getAttributes().getNamedItem("temp").getNodeValue().toString());
		MyWidget.cidade.setCodigo(conditionNode.getAttributes().getNamedItem("code").getNodeValue().toString());
	
		//Percorre elemento forecast
		Node forecastNode = srcDoc.getElementsByTagName("yweather:forecast").item(0);		
		MyWidget.cidade.setData(forecastNode.getAttributes().getNamedItem("date").getNodeValue().toString());
		MyWidget.cidade.setMin(forecastNode.getAttributes().getNamedItem("low").getNodeValue().toString());
		MyWidget.cidade.setMax(forecastNode.getAttributes().getNamedItem("high").getNodeValue().toString());
		
	}

}
