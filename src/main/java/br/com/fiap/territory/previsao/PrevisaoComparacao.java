package br.com.fiap.territory.previsao;
import br.com.fiap.territory.dominio.Medicao;import java.util.List;
public class PrevisaoComparacao extends EstrategiaPrevisao {
 public PrevisaoComparacao(int janela){super(janela);}
 @Override public double prever(List<Medicao> h){exigir(h,janela*2);int n=h.size();double anterior=media(h,n-2*janela,n-janela),atual=media(h,n-janela,n);return Math.max(0,atual+(atual-anterior));}
}