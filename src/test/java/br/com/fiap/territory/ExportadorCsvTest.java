package br.com.fiap.territory;
import org.junit.jupiter.api.Test;import org.junit.jupiter.api.io.TempDir;import static org.junit.jupiter.api.Assertions.*;import br.com.fiap.territory.servico.ExportadorCsv;import br.com.fiap.territory.dominio.Medicao;import java.nio.file.*;import java.time.LocalDateTime;import java.util.*;
class ExportadorCsvTest {@TempDir Path pasta;
 @Test void exportaCsvComCabecalhoEValor()throws Exception{Path destino=pasta.resolve("historico.csv");new ExportadorCsv().exportar(List.of(new Medicao("s","CHUVA","mm","R",12,LocalDateTime.now())),destino);String csv=Files.readString(destino);assertTrue(csv.contains("sensor_id,tipo,valor"));assertTrue(csv.contains("12.0"));}
}