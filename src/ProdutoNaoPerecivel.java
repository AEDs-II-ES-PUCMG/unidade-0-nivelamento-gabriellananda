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

    /**
    * Gera uma linha de texto a partir dos dados do produto. Preço e margem de lucro vão formatados com 2 casas
    decimais.
    * @return Uma string no formato "1; descrição;preçoDeCusto;margemDeLucro"
    */
    @Override
    public String gerarDadosTexto() {
        /*Você deve implementar aqui a lógica que monta a String com os atributos do objeto ProdutoNaoPerecivel,
        respeitando o formato do arquivo de dados. */
        String precoFormatado = String.format("%.2f", precoCusto).replace(",",".");
        String margemFormatada = String.format("%.2f", margemLucro).replace(",",".");
        return String.format("1;%s;%s;%s", descricao, precoFormatado, margemFormatada);
    }
}
