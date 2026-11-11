package br.com.fiap.territory;
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import static org.mockito.Mockito.*;import static org.mockito.ArgumentMatchers.any;
import br.com.fiap.territory.dominio.*;import br.com.fiap.territory.persistencia.*;import br.com.fiap.territory.servico.*;import br.com.fiap.territory.previsao.*;
import java.time.LocalDateTime;import java.util.*;
class ServicoMonitoramentoTest {
 @Test void alertaCriticoEhPersistidoComMock(){
  MedicaoRepository mr=mock(MedicaoRepository.class);AlertaRepository ar=mock(AlertaRepository.class);
  Sensor s=new SensorPluviometrico("CH-01",new Random(1));List<Medicao> historico=new ArrayList<>();for(int i=0;i<5;i++)historico.add(new Medicao("CH-01","CHUVA","mm","R",100,LocalDateTime.now()));
  when(mr.findBySensorIdOrderByInstanteAsc("CH-01")).thenReturn(historico);when(ar.save(any(Alerta.class))).thenAnswer(inv->inv.getArgument(0));
  var resultado=new ServicoMonitoramento(mr,ar).preverEAlertar(s,new DadosTerritorio("R"),new PrevisaoMediaMovel(5));
  assertTrue(resultado.isPresent());assertEquals(NivelAlerta.CRITICO,resultado.orElseThrow().getNivel());verify(ar).save(any(Alerta.class));
 }
 @Test void abaixoDoLimiarNaoEmiteAlerta(){MedicaoRepository mr=mock(MedicaoRepository.class);AlertaRepository ar=mock(AlertaRepository.class);Sensor s=new SensorPluviometrico("CH-01",new Random(1));List<Medicao> h=new ArrayList<>();for(int i=0;i<5;i++)h.add(new Medicao("CH-01","CHUVA","mm","R",5,LocalDateTime.now()));when(mr.findBySensorIdOrderByInstanteAsc("CH-01")).thenReturn(h);assertTrue(new ServicoMonitoramento(mr,ar).preverEAlertar(s,new DadosTerritorio("R"),new PrevisaoMediaMovel(5)).isEmpty());verify(ar,never()).save(any());}
}