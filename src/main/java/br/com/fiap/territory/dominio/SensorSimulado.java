package br.com.fiap.territory.dominio;
import br.com.fiap.territory.excecao.FonteIndisponivelException;import java.util.Random;
public abstract class SensorSimulado implements Sensor {
 private final String id;private final Random random;private boolean funcionando=true;
 protected SensorSimulado(String id,Random random){this.id=id;this.random=random;}
 public String getId(){return id;}public boolean verificarFuncionamento(){return funcionando;}
 public void setFuncionando(boolean funcionando){this.funcionando=funcionando;}
 protected double gerar(double minimo,double maximo){if(!funcionando)throw new FonteIndisponivelException("Sensor "+id+" indisponível");return Math.round((minimo+random.nextDouble()*(maximo-minimo))*100.0)/100.0;}
}