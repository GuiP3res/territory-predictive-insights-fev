package br.com.fiap.territory.dominio;
import java.util.Random;
public class SensorPluviometrico extends SensorSimulado {public SensorPluviometrico(String id,Random random){super(id,random);}public String retornarTipo(){return "CHUVA";}public String unidade(){return "mm";}public double lerValor(){return gerar(0,120);}}