package br.com.fiap.territory;
import br.com.fiap.territory.app.SistemaMonitoramento;import br.com.fiap.territory.dominio.Medicao;import br.com.fiap.territory.persistencia.MedicaoRepository;
import org.junit.jupiter.api.Test;import org.junit.jupiter.api.io.TempDir;import org.springframework.boot.WebApplicationType;import org.springframework.boot.builder.SpringApplicationBuilder;
import java.nio.file.Path;import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
class PersistenciaReinicioTest {
 @TempDir Path pasta;
 @Test void medicoesContinuamDisponiveisAposReiniciarAplicacao(){
  String url="jdbc:h2:file:"+pasta.resolve("teste-territorio").toAbsolutePath()+";DB_CLOSE_ON_EXIT=FALSE";
  try(var primeiraExecucao=iniciar(url)){
   var repository=primeiraExecucao.getBean(MedicaoRepository.class);
   repository.save(new Medicao("CH-TESTE","CHUVA","mm","Regiao teste",42.5,LocalDateTime.of(2026,11,11,10,0)));
   assertEquals(1,repository.findBySensorIdOrderByInstanteAsc("CH-TESTE").size());
  }
  try(var segundaExecucao=iniciar(url)){
   var historico=segundaExecucao.getBean(MedicaoRepository.class).findBySensorIdOrderByInstanteAsc("CH-TESTE");
   assertEquals(1,historico.size());assertEquals(42.5,historico.get(0).getValor());
  }
 }
 private org.springframework.context.ConfigurableApplicationContext iniciar(String url){
  return new SpringApplicationBuilder(SistemaMonitoramento.class).web(WebApplicationType.NONE)
   .properties("spring.datasource.url="+url,"spring.jpa.hibernate.ddl-auto=update","spring.main.banner-mode=off").run();
 }
}