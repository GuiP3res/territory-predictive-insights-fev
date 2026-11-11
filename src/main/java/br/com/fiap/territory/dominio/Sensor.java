package br.com.fiap.territory.dominio;
public interface Sensor {String getId();String retornarTipo();String unidade();double lerValor();boolean verificarFuncionamento();}