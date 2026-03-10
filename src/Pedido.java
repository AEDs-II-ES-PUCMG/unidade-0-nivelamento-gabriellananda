import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {

	/** Quantidade máxima de produtos de um pedido */
	private static final int MAX_PRODUTOS = 10;
	
	/** Porcentagem de desconto para pagamentos à vista */
	private static final double DESCONTO_PG_A_VISTA = 0.15;
	
	/** Vetor para armazenar os items do pedido */
	private ItemDePedido[] itemDePedidos;
	
	/** Data de criação do pedido */
	private LocalDate dataPedido;
	
	/** Indica a quantidade total de produtos no pedido até o momento */
	private int quantProdutos = 0;
	
	/** Indica a forma de pagamento do pedido sendo: 1, pagamento à vista; 2, pagamento parcelado */
	private int formaDePagamento;
	
	/** Construtor do pedido.
	 *  Deve criar o vetor de produtos do pedido, 
	 *  armazenar a data e a forma de pagamento informadas para o pedido. 
	 */  
	public Pedido(LocalDate dataPedido, int formaDePagamento) {
		
		itemDePedidos = new ItemDePedido[MAX_PRODUTOS];
		quantProdutos = 0;
		this.dataPedido = dataPedido;
		this.formaDePagamento = formaDePagamento;
	}
	
	/**
     * Inclui um produto neste pedido e aumenta a quantidade de produtos armazenados no pedido até o momento.
     * @param novo O produto a ser incluído no pedido
     * @return true/false indicando se a inclusão do produto no pedido foi realizada com sucesso.
     */
	public boolean incluirProduto(ItemDePedido novo) {
		
		if (quantProdutos < MAX_PRODUTOS) {
			itemDePedidos[quantProdutos++] = novo;
			return true;
		}
		return false;
	}
	
	/**
     * Calcula e retorna o valor final do pedido (soma do valor de venda de todos os produtos do pedido).
     * Caso a forma de pagamento do pedido seja à vista, aplica o desconto correspondente.
     * @return Valor final do pedido (double)
     */
	public double valorFinal() {
		
		double valorPedido = 0;
		
		for (int i = 0; i < quantProdutos; i++) {
			valorPedido += itemDePedidos[i].valorDeVenda();
		}
		
		if (formaDePagamento == 1) {
			valorPedido = valorPedido * (1.0 - DESCONTO_PG_A_VISTA);
		}
		return valorPedido;
	}
	
	/**
     * Representação, em String, do pedido.
     * Contém um cabeçalho com sua data e o número de produtos no pedido.
     * Depois, em cada linha, a descrição de cada produto do pedido.
     * Ao final, mostra a forma de pagamento, o percentual de desconto (se for o caso) e o valor a ser pago pelo pedido.
     * Exemplo:
     * Data do pedido: 25/08/2025
     * Pedido com 2 produtos.
     * Produtos no pedido:
     * NOME: Iogurte: R$ 8.00
     * Válido até: 29/08/2025
     * NOME: Guardanapos: R$ 2.75
     * Pedido pago à vista. Percentual de desconto: 15,00%
     * Valor total do pedido: R$ 10.75 
     * @return Uma string contendo dados do pedido conforme especificado (cabeçalho, detalhes, forma de pagamento,
     * percentual de desconto - se for o caso - e valor a pagar)
     */
	@Override
	public String toString() {
		
		StringBuilder stringPedido = new StringBuilder();
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		stringPedido.append("Data do pedido:" + formatoData.format(dataPedido) + "\n");
		
		stringPedido.append("Pedido com " + quantProdutos + " produtos.\n");
		stringPedido.append("Produtos no pedido:\n");
		for (int i = 0; i < quantProdutos; i++ ) {
			stringPedido.append(itemDePedidos[i].toString() + "\n");
		}
		
		stringPedido.append("Pedido pago ");
		if (formaDePagamento == 1) {
			stringPedido.append("à vista. Percentual de desconto: " + String.format("%.2f", DESCONTO_PG_A_VISTA * 100) + "%\n");
		} else {
			stringPedido.append("parcelado.\n");
		}
		
		stringPedido.append("Valor total do pedido: R$ " + String.format("%.2f", valorFinal()));
		
		return stringPedido.toString();
	}
	
	/**
     * Igualdade de pedidos: caso possuam a mesma data. 
     * @param obj Outro pedido a ser comparado 
     * @return booleano true/false conforme o parâmetro possua a data igual ou não a este pedido.
     */
    @Override
    public boolean equals(Object obj) {
        Pedido outro = (Pedido)obj;
        return this.dataPedido.equals(outro.dataPedido);
    }

	public void mesclarPedido(Pedido outroPedido) {
		Pedido vetorOficial[];
		int contadorItensIguais=0, contadorPedidoSec=0, contadorPedidoAtual=0;

		//Verificando se têm itens iguais nos dois vetores
		for(int i=0; i < MAX_PRODUTOS; i++) {
			for(int j=0; j < MAX_PRODUTOS; j++) {
				equals(itemDePedidos);
				if(true)
				contadorItensIguais++;
			}
		}
		
		//Verificando quantos itens têm nos vetores Pedido Secundário (outroPedido) e Pedido Atual (itemDePedido)
		for(int i=0; i < MAX_PRODUTOS; i++) {
			if(outroPedido.itemDePedidos[i] != null) {
				contadorPedidoSec++;
			}
			if(itemDePedidos[i] != null) {
				contadorPedidoAtual++;
			}
		}

		//Lançando exceção, caso a mesclagem desses vetores ultrapasse o valor máximo de produtos.
		if(((contadorPedidoSec + contadorPedidoAtual) - contadorItensIguais) > MAX_PRODUTOS) {
			throw new IllegalArgumentException("Vetor ultrapassou o limite.");
		}
		else {
			for(int i = 0; i < MAX_PRODUTOS; i++) {
				vetorOficial[i] = itemDePedidos[i]; //O vetor oficial recebe os itens que estão no Pedido Atual (itemDePedido)
			}

			//Comparando o vetor oficial e o vetor de Pedido Secundario (outroPedido) e verificando se têm itens repetidos.
			//Salvando no vetor oficial os itens que não são repetidos.
			for(int i = 0; i < MAX_PRODUTOS; i++) {
				if(vetorOficial[i] != outroPedido.itemDePedidos[i]) {
					for(int j = 0; j < MAX_PRODUTOS; j++) {
						if(vetorOficial[i] == null) {
							vetorOficial[i] = outroPedido.itemDePedidos[i];
						}
					}
				}
			}
		}

		contadorItensIguais=0;
		contadorPedidoSec=0;
		contadorPedidoAtual=0;
	}

	public void imprimeRecibo() {

		for(int i=0; i < MAX_PRODUTOS; i++) {
			String.format("NOME: " + itemDePedidos.descricao);
			String.format("QUANTIDADE: " + itemDePedidos.quantidade);
			String.format("VALOR: " + itemDePedidos.precoCusto);
		}

		StringBuilder stringPedido = new StringBuilder();
		stringPedido.append("Valor total do pedido: R$ " + String.format("%.2f", valorFinal()));
	}
}