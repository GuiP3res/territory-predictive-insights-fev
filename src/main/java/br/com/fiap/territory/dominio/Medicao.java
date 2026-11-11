package br.com.fiap.territory.dominio;
import jakarta.persistence.*;import java.time.LocalDateTime;
@Entity public class Medicao {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String sensorId,tipo,unidade,regiao;
 @Column(nullable=false) private double valor;
 @Column(nullable=false) private LocalDateTime instante;
 protected Medicao(){}
 public Medicao(String sensorId,String tipo,String unidade,String regiao,double valor,LocalDateTime instante){
  if(!Double.isFinite(valor)||valor<0)throw new br.com.fiap.territory.excecao.DadosInvalidosException("Medição inválida: "+valor);
  this.sensorId=sensorId;this.tipo=tipo;this.unidade=unidade;this.regiao=regiao;this.valor=valor;this.instante=instante;
 }
 public Long getId(){return id;}public String getSensorId(){return sensorId;}public String getTipo(){return tipo;}public String getUnidade(){return unidade;}public String getRegiao(){return regiao;}public double getValor(){return valor;}public LocalDateTime getInstante(){return instante;}
}