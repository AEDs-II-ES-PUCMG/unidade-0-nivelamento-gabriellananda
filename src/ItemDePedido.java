public class ItemDePedido {

    // Atributos encapsulados
    protected Produto produto;
    protected int quantidade;
    protected double precoVenda;

    /**
     * Construtor da classe ItemDePedido.
     * O precoVenda deve ser capturado do produto no momento da criação do item,
     * garantindo que alterações futuras no preço do produto não afetem este pedido.
     */
    public ItemDePedido(Produto produto, int quantidade, double precoVenda) {
        this.produto = produto;
		this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public double calcularSubtotal() {
        return 0;
    }

    // --- Sobrescrita do método equals ---

    /**
     * Compara a igualdade entre dois itens de pedido.
     * A regra de negócio define que dois itens são iguais se possuírem o mesmo Produto.
     */
    @Override
    public boolean equals(Object obj) {

        if(!(produto instanceof obj){
            return false;
        }

        Produto outro = (Produto)obj;
        return this.produto.equals(outro);
    }
}
