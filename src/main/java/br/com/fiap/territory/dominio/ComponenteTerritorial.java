package br.com.fiap.territory.dominio;
public abstract class ComponenteTerritorial {
 private final String id,nome,regiao; protected String status;
 protected ComponenteTerritorial(String id,String nome,String regiao){this.id=id;this.nome=nome;this.regiao=regiao;this.status="ATIVO";}
 public String getId(){return id;}public String getNome(){return nome;}public String getRegiao(){return regiao;}public String getStatus(){return status;}
 public abstract String verificarStatus();
}