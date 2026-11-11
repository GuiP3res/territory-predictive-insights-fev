package br.com.fiap.territory.servico;
import br.com.fiap.territory.dominio.*;import br.com.fiap.territory.persistencia.*;import br.com.fiap.territory.previsao.EstrategiaPrevisao;
import org.springframework.stereotype.Service;import java.time.LocalDateTime;import java.util.*;
@Service public class ServicoMonitoramento {
 private final MedicaoRepository medicoes;private final AlertaRepository alertas;
 public ServicoMonitoramento(MedicaoRepository medicoes,AlertaRepository alertas){this.medicoes=medicoes;this.alertas=alertas;}
 public Medicao registrar(Sensor sensor,String regiao){if(!sensor.verificarFuncionamento())throw new br.com.fiap.territory.excecao.FonteIndisponivelException("Sensor indisponível: "+sensor.getId());return medicoes.save(new Medicao(sensor.getId(),sensor.retornarTipo(),sensor.unidade(),regiao,sensor.lerValor(),LocalDateTime.now()));}
 public List<Medicao> historico(String sensorId){return medicoes.findBySensorIdOrderByInstanteAsc(sensorId);}
 public List<Alerta> alertas(){return alertas.findAllByOrderByInstanteDesc();}
 public Optional<Alerta> preverEAlertar(Sensor sensor,DadosTerritorio territorio,EstrategiaPrevisao estrategia){
  List<Medicao> h=historico(sensor.getId());double previsto=estrategia.prever(h);double[] limites=territorio.getLimites(sensor.retornarTipo());
  NivelAlerta nivel;double limite;
  if(previsto>=limites[2]){nivel=NivelAlerta.CRITICO;limite=limites[2];}
  else if(previsto>=limites[1]){nivel=NivelAlerta.ALERTA;limite=limites[1];}
  else if(previsto>=limites[0]){nivel=NivelAlerta.ATENCAO;limite=limites[0];}
  else return Optional.empty();
  return Optional.of(alertas.save(new Alerta(sensor.getId(),territorio.getRegiao(),sensor.retornarTipo(),sensor.unidade(),nivel,previsto,limite,LocalDateTime.now())));
 }
}