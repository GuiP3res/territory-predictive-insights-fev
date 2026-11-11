package br.com.fiap.territory;
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
import br.com.fiap.territory.dominio.*;import br.com.fiap.territory.excecao.*;import java.time.LocalDateTime;import java.util.*;
class DominioTest {
 @Test void medicaoValida(){Medicao m=new Medicao("a","CHUVA","mm","Região",12,LocalDateTime.now());assertEquals(12,m.getValor());}
 @Test void medicaoNegativaLancaExcecao(){assertThrows(DadosInvalidosException.class,()->new Medicao("a","CHUVA","mm","Região",-1,LocalDateTime.now()));}
 @Test void limitesInvalidosSaoRejeitados(){DadosTerritorio d=new DadosTerritorio("Região");assertThrows(DadosInvalidosException.class,()->d.alterarLimites("CHUVA",90,20,100,"1234"));}
 @Test void sensorIndisponivelLancaExcecao(){SensorPluviometrico s=new SensorPluviometrico("s",new Random(1));s.setFuncionando(false);assertThrows(FonteIndisponivelException.class,s::lerValor);}
 @Test void estacaoUsaPolimorfismo(){Sensor s=new SensorNivelRio("n",new Random(1));ComponenteTerritorial c=new EstacaoMonitoramento("e","Estação","R",List.of(s));assertEquals("ATIVO",c.verificarStatus());}
}