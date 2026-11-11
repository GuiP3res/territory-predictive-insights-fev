package br.com.fiap.territory;
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
import br.com.fiap.territory.dominio.*;import br.com.fiap.territory.previsao.*;import br.com.fiap.territory.servico.*;import java.time.LocalDateTime;import java.util.*;
class PrevisaoTest {
 List<Medicao> h(double... valores){List<Medicao> l=new ArrayList<>();for(double v:valores)l.add(new Medicao("a","CHUVA","mm","R",v,LocalDateTime.now()));return l;}
 @Test void mediaMovelCalculaUltimasLeituras(){assertEquals(30,new PrevisaoMediaMovel(3).prever(h(1,20,30,40)));}
 @Test void comparacaoProjetaDiferencaDasMedias(){assertEquals(55,new PrevisaoComparacao(2).prever(h(10,20,30,40)));}
 @Test void historicoInsuficienteImpedePrevisao(){assertThrows(IllegalStateException.class,()->new PrevisaoMediaMovel(5).prever(h(10,20)));}
 @Test void factoryEscolheClassesDistintas(){FabricaPrevisao f=new FabricaPrevisao();assertInstanceOf(PrevisaoMediaMovel.class,f.criar(FabricaPrevisao.Tipo.MEDIA_MOVEL,5));assertInstanceOf(PrevisaoComparacao.class,f.criar(FabricaPrevisao.Tipo.COMPARACAO,5));}
}