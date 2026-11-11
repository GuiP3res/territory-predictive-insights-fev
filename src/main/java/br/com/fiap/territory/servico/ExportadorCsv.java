package br.com.fiap.territory.servico;
import br.com.fiap.territory.dominio.Medicao;import java.nio.file.*;import java.nio.charset.StandardCharsets;import java.io.*;import java.util.List;
public class ExportadorCsv {
 public void exportar(List<Medicao> medicoes,Path destino)throws IOException{
  if(destino.getParent()!=null)Files.createDirectories(destino.getParent());
  try(BufferedWriter out=Files.newBufferedWriter(destino,StandardCharsets.UTF_8)){
   out.write("sensor_id,tipo,valor,unidade,regiao,data_hora");out.newLine();
   for(Medicao m:medicoes){out.write(String.join(",",q(m.getSensorId()),q(m.getTipo()),Double.toString(m.getValor()),q(m.getUnidade()),q(m.getRegiao()),q(m.getInstante().toString())));out.newLine();}
  }
 }
 private String q(String s){return "\""+s.replace("\"","\"\"")+"\"";}
}