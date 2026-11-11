package br.com.fiap.territory.dominio;
import java.util.List;
public class EstacaoMonitoramento extends ComponenteTerritorial {
 private final List<Sensor> sensores;
 public EstacaoMonitoramento(String id,String nome,String regiao,List<Sensor> sensores){super(id,nome,regiao);this.sensores=List.copyOf(sensores);}
 public List<Sensor> getSensores(){return sensores;}
 @Override public String verificarStatus(){status=sensores.stream().allMatch(Sensor::verificarFuncionamento)?"ATIVO":"DEGRADADO";return status;}
}