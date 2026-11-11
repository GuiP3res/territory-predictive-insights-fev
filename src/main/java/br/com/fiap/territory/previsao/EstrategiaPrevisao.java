package br.com.fiap.territory.previsao;
import br.com.fiap.territory.dominio.Medicao;import java.util.List;
public abstract class EstrategiaPrevisao {
 protected final int janela;
 protected EstrategiaPrevisao(int janela){if(janela<1)throw new IllegalArgumentException("Janela inválida");this.janela=janela;}
 public abstract double prever(List<Medicao> historico);
 protected void exigir(List<Medicao> historico,int minimo){if(historico.size()<minimo)throw new IllegalStateException("Histórico insuficiente: necessárias "+minimo+" leituras");}
 protected double media(List<Medicao> h,int inicio,int fim){return h.subList(inicio,fim).stream().mapToDouble(Medicao::getValor).average().orElseThrow();}
}