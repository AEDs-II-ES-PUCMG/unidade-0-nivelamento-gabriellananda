public class ProdutoNaoPerecivel extends Produto {

    //Construtor completo. Causa exceção em caso de valores inválidos.
    public ProdutoNaoPerecivel(String desc, double precoCusto, double margemLucro) {
        super(desc, precoCusto, margemLucro);
    }

    //Construtor com margem de lucro padrão (20%). Causa exceção em caso de valores inválidos.
    public ProdutoNaoPerecivel(String desc, double precoCusto) {
        super(desc, precoCusto);
    }

    //Retorna o valor de venda do produto, considerando seu preço de custo e margem de lucro.
    @Override
    public double valorVenda(){
        return precoCusto * (1 + margemLucro);
    }
}
