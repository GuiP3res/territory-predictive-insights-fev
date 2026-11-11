package br.com.fiap.territory.dominio;
import jakarta.persistence.*;import java.time.LocalDateTime;
@Entity public class Alerta {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String sensorId,regiao,tipo,unidade;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private NivelAlerta nivel;
 private double valorPrevisto,limite;
 @Column(nullable=false) private LocalDateTime instante;
 protected Alerta(){}
 public Alerta(String sensorId,String regiao,String tipo,String unidade,NivelAlerta nivel,double valorPrevisto,double limite,LocalDateTime instante){this.sensorId=sensorId;this.regiao=regiao;this.tipo=tipo;this.unidade=unidade;this.nivel=nivel;this.valorPrevisto=valorPrevisto;this.limite=limite;this.instante=instante;}
 public Long getId(){return id;}public String getSensorId(){return sensorId;}public String getRegiao(){return regiao;}public String getTipo(){return tipo;}public String getUnidade(){return unidade;}public NivelAlerta getNivel(){return nivel;}public double getValorPrevisto(){return valorPrevisto;}public double getLimite(){return limite;}public LocalDateTime getInstante(){return instante;}
}