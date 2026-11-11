package br.com.fiap.territory.app;
import br.com.fiap.territory.dominio.*;import br.com.fiap.territory.servico.*;
import org.springframework.boot.*;import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.context.ConfigurableApplicationContext;
import java.util.*;import java.nio.file.Path;
@SpringBootApplication(scanBasePackages="br.com.fiap.territory") public class SistemaMonitoramento {
 public static void main(String[] args){try(ConfigurableApplicationContext ctx=SpringApplication.run(SistemaMonitoramento.class,args)){executar(ctx.getBean(ServicoMonitoramento.class));}}
 static void executar(ServicoMonitoramento servico){
  Scanner entrada=new Scanner(System.in);DadosTerritorio territorio=new DadosTerritorio("Zona Norte - área simulada");
  List<Sensor> sensores=List.of(new SensorPluviometrico("CH-01",new Random()),new SensorNivelRio("NR-01",new Random()),new SensorUmidadeSolo("US-01",new Random()));
  EstacaoMonitoramento estacao=new EstacaoMonitoramento("EST-01","Estação comunitária",territorio.getRegiao(),sensores);
  FabricaPrevisao fabrica=new FabricaPrevisao();
  while(true){System.out.println("\n=== FLOODGUARD | CITIZENTECH ===\n1 Sensores  2 Novas leituras  3 Previsões  4 Histórico  5 Alertas  6 Limiares  7 Status  8 Exportar CSV  0 Sair");
   try{String opcao=entrada.nextLine().trim();switch(opcao){
    case "0" -> {System.out.println("Encerrado.");return;}
    case "1" -> sensores.forEach(s->System.out.printf("%s (%s): %s%n",s.getId(),s.retornarTipo(),s.verificarFuncionamento()?"OK":"INDISPONÍVEL"));
    case "2" -> sensores.forEach(s->{Medicao m=servico.registrar(s,territorio.getRegiao());System.out.printf("%s: %.2f %s%n",m.getSensorId(),m.getValor(),m.getUnidade());});
    case "3" -> {System.out.print("Estratégia [1=média móvel; 2=comparação]: ");String tipo=entrada.nextLine();var estrategia=fabrica.criar("2".equals(tipo)?FabricaPrevisao.Tipo.COMPARACAO:FabricaPrevisao.Tipo.MEDIA_MOVEL,5);
     for(Sensor s:sensores){try{var h=servico.historico(s.getId());double valor=estrategia.prever(h);System.out.printf("%s previsão: %.2f %s (estimativa simplificada)%n",s.getId(),valor,s.unidade());var alerta=servico.preverEAlertar(s,territorio,estrategia);alerta.ifPresent(a->System.out.printf("ALERTA %s: %s previsto %.2f %s >= limite %.2f %s%n",a.getNivel(),a.getSensorId(),a.getValorPrevisto(),a.getUnidade(),a.getLimite(),a.getUnidade()));}catch(IllegalStateException ex){System.out.println(s.getId()+": "+ex.getMessage());}}}
    case "4" -> {System.out.print("ID do sensor (CH-01, NR-01, US-01): ");servico.historico(entrada.nextLine().trim()).forEach(m->System.out.printf("%s | %s | %.2f %s%n",m.getInstante(),m.getSensorId(),m.getValor(),m.getUnidade()));}
    case "5" -> servico.alertas().forEach(a->System.out.printf("%s | %s | %s | previsto %.2f %s | limite %.2f %s%n",a.getInstante(),a.getSensorId(),a.getNivel(),a.getValorPrevisto(),a.getUnidade(),a.getLimite(),a.getUnidade()));
    case "6" -> {System.out.print("Tipo [CHUVA/NIVEL_RIO/UMIDADE_SOLO]: ");String t=entrada.nextLine().trim().toUpperCase();System.out.print("Atenção: ");double a=Double.parseDouble(entrada.nextLine());System.out.print("Alerta: ");double b=Double.parseDouble(entrada.nextLine());System.out.print("Crítico: ");double c=Double.parseDouble(entrada.nextLine());System.out.print("Código de acesso: ");territorio.alterarLimites(t,a,b,c,entrada.nextLine());System.out.println("Limiares atualizados.");}
    case "7" -> {System.out.println(estacao.getNome()+" | "+estacao.getRegiao()+" | "+estacao.verificarStatus());for(Sensor s:sensores)System.out.printf("%s: %d medições; limites %s%n",s.getId(),servico.historico(s.getId()).size(),Arrays.toString(territorio.getLimites(s.retornarTipo())));System.out.println("Alertas armazenados: "+servico.alertas().size());}
    case "8" -> {System.out.print("ID do sensor: ");String id=entrada.nextLine().trim();Path p=Path.of("exportacoes",id.replaceAll("[^a-zA-Z0-9_-]","_")+".csv");new ExportadorCsv().exportar(servico.historico(id),p);System.out.println("CSV salvo em "+p.toAbsolutePath());}
    default -> System.out.println("Opção inválida.");
   }}catch(Exception ex){System.out.println("Não foi possível concluir: "+ex.getMessage());}
  }
 }
}