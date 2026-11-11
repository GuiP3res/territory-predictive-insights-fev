package br.com.fiap.territory.previsao;
import br.com.fiap.territory.dominio.Medicao;import java.util.List;
public class PrevisaoMediaMovel extends EstrategiaPrevisao {
 public PrevisaoMediaMovel(int janela){super(janela);}
 @Override public double prever(List<Medicao> h){exigir(h,janela);return media(h,h.size()-janela,h.size());}
}