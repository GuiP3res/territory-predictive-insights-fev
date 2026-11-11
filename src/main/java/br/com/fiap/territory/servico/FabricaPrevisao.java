package br.com.fiap.territory.servico;
import br.com.fiap.territory.previsao.*;
public class FabricaPrevisao {
 public enum Tipo{MEDIA_MOVEL,COMPARACAO}
 public EstrategiaPrevisao criar(Tipo tipo,int janela){return switch(tipo){case MEDIA_MOVEL -> new PrevisaoMediaMovel(janela);case COMPARACAO -> new PrevisaoComparacao(janela);};}
}