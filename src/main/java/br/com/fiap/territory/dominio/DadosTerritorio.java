package br.com.fiap.territory.dominio;
import br.com.fiap.territory.excecao.DadosInvalidosException;
import java.util.Map;import java.util.HashMap;
public class DadosTerritorio {
 private final String regiao;private final Map<String,double[]> limites=new HashMap<>();
 public DadosTerritorio(String regiao){this.regiao=regiao;limites.put("CHUVA",new double[]{30,60,90});limites.put("NIVEL_RIO",new double[]{2,3.5,5});limites.put("UMIDADE_SOLO",new double[]{60,80,95});}
 public String getRegiao(){return regiao;}
 public double[] getLimites(String tipo){double[] l=limites.get(tipo);if(l==null)throw new DadosInvalidosException("Tipo desconhecido: "+tipo);return l.clone();}
 public void alterarLimites(String tipo,double atencao,double alerta,double critico,String codigo){
  String esperado=System.getenv().getOrDefault("TERRITORY_ACCESS_CODE","1234");
  if(!esperado.equals(codigo))throw new DadosInvalidosException("Código de acesso incorreto");
  if(!limites.containsKey(tipo)||!Double.isFinite(atencao)||!Double.isFinite(alerta)||!Double.isFinite(critico)||atencao<0||atencao>=alerta||alerta>=critico)throw new DadosInvalidosException("Limiares inválidos: informe 0 <= atenção < alerta < crítico");
  limites.put(tipo,new double[]{atencao,alerta,critico});
 }
}