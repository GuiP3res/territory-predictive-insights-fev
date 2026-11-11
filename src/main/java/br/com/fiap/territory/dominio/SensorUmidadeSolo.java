package br.com.fiap.territory.dominio;
import java.util.Random;
public class SensorUmidadeSolo extends SensorSimulado {public SensorUmidadeSolo(String id,Random random){super(id,random);}public String retornarTipo(){return "UMIDADE_SOLO";}public String unidade(){return "%";}public double lerValor(){return gerar(0,100);}}