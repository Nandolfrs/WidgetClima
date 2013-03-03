package com.nandolfrs.widgetclima;

public class Cidade {
	private String nome;		//Nome da cidade
	private String estado;		//Estado da cidade
	private String temp;		//Temperatura atual
	private String condicao;	//Condição do clima
	private String data;		//Data do dia
	private String max;			//Temperatura maxima
	private String min;			//Temperatura minima
	private String umidade;		//Umidade do ar
	private String vento;		//Velocidade do vento
	private String codigo;		//Codigo do clima
	private int codigoInt;		//Codigo do clima em inteiro
	
	
	//Construtor vazio
	public Cidade() {
		
	}		
	
	
	//Metodo para converter a temperatura de Fahrenheit para Celsius
	public String converteTemp(String tempFah) {		
		
		int temp;				//Armazena a temperatura em inteiro
		String tempCel;		//Armazena em string a temperatura convertida
		
		//Converte a temperatura
		temp = Integer.valueOf(tempFah);
        temp = (int) ((temp - 32) / (1.8));
        tempCel = String.valueOf(temp);
        tempCel += "º";
        
        //Retorna a nova temperatura
        return tempCel;
        
	}
	
	
	//Metodo para converter a velocidade de milhas para quilometros
	public String converteVel(String velMilhas) {
		
		double velocidade;		//Armazena a velocidade em double
		String velKm;			//Armazena em string a velocidade convertida
		
		//Converte a velocidade
		velocidade = Float.valueOf(velMilhas);
		velocidade = velocidade * 1.609;		
		velKm = String.format("%.2f", velocidade);
		velKm += " km/h";
		
		//Retorna a nova velocidade
		return velKm;
		
	}
	
	
	//Metodo para definir as condições do tempo
	public void defineCondicao(String codigo) {
		
		//Analisa o codigo fornecido pelo forecast para definir a condição e um novo codigo
		//que sera usado para setar o icone do widget
		
		if(codigo.equals("32") || codigo.equals("34") || codigo.equals("36")) {
			setCondicao("Tempo aberto");
			setCodigoInt(1);
		}
		else if(codigo.equals("31") || codigo.equals("33")) {
			setCondicao("Tempo aberto");
			setCodigoInt(2);
		}
		else if(codigo.equals("26") || codigo.equals("28") || codigo.equals("30") || codigo.equals("44")) {
			setCondicao("Tempo nublado");
			setCodigoInt(3);
		}
		else if(codigo.equals("27") || codigo.equals("29")) {
			setCondicao("Tempo nublado");
			setCodigoInt(4);
		}
		else if(codigo.equals("9") || codigo.equals("11") || codigo.equals("12") || codigo.equals("17") || codigo.equals("40")) {
			setCondicao("Tempo chuvoso");
			setCodigoInt(5);
		}
		else if(codigo.equals("1") || codigo.equals("3") || codigo.equals("4") || codigo.equals("37") || codigo.equals("38") || codigo.equals("39") || codigo.equals("45") || codigo.equals("47")) {
			setCondicao("Tempestade com raios");
			setCodigoInt(6);
		}
		else if(codigo.equals("7") || codigo.equals("13") || codigo.equals("15") || codigo.equals("16") || codigo.equals("18") || codigo.equals("41") || codigo.equals("42") || codigo.equals("43") || codigo.equals("46")) {
			setCondicao("Nevando");
			setCodigoInt(7);
		}
		else {
			setCondicao("Tempo desconhecido");
			setCodigoInt(8);
		}
		
	}	
	
	
	// *** METODOS GETTERS AND SETTERS ***
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		String celsius = converteTemp(temp);
		this.temp = celsius;
	}
	
	public void setTemp2(String temp) {
		this.temp = temp;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		String celsius = converteTemp(max);
		this.max = celsius;
	}
	
	public void setMax2(String max) {
		this.max = max;
	}
	
	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		String celsius = converteTemp(min);
		this.min = celsius;
	}
	
	public void setMin2(String min) {
		this.min = min;
	}
	
	public String getUmidade() {
		return umidade;
	}

	public void setUmidade(String umidade) {
		umidade += "%";
		this.umidade = umidade;
	}
	
	public void setUmidade2(String umidade) {
		this.umidade = umidade;
	}
	
	public String getVento() {
		return vento;
	}

	public void setVento(String vento) {
		String km = converteVel(vento);
		this.vento = km;
	}
	
	public void setVento2(String vento) {
		this.vento = vento;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
		defineCondicao(codigo);		
	}
	
	public int getCodigoInt() {
		return codigoInt;
	}

	public void setCodigoInt(int codigo) {
		this.codigoInt = codigo;
	}
	
}
