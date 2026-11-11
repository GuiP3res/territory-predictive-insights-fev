package br.com.fiap.territory.dominio;
import java.util.Random;
public class SensorNivelRio extends SensorSimulado {public SensorNivelRio(String id,Random random){super(id,random);}public String retornarTipo(){return "NIVEL_RIO";}public String unidade(){return "m";}public double lerValor(){return gerar(0,6);}}