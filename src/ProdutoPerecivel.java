import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoPerecivel extends Produto {
    
    // Desconto para proximidade de validade: 25%
    private static final double DESCONTO = 0.25;
    // Prazo, em dias, para conceder o desconto por proximidade da validade.
    private static final int PRAZO_DESCONTO = 7;
    //Data de validade do produto. Não pode ser anterior à data da criação ou venda.
    private LocalDate dataDeValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate validade){
        super(desc, precoCusto, margemLucro);
        if(validade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("O produto está vencido!");
        }    
        dataDeValidade = validade;
    }

    private static boolean verificaValidade(LocalDate dataValidade){
        LocalDate hoje = LocalDate.now();
        
        if(!hoje.isAfter(dataValidade)){
            return true;
        } else {
            return false;
        }
    }

    //Retorna o valor de venda do produto, considerando seu preço de custo, margem de lucro.
    @Override
    public double valorVenda() {
        double desconto = 0d;
        int diasValidade = LocalDate.now().until(dataDeValidade).getDays();

        if(diasValidade <= PRAZO_DESCONTO) {
            desconto = DESCONTO;
        }
        
        return precoCusto * (1 + margemLucro) * (1 - desconto);
    }

    //Descrição em string do produto, contendo sua descrição, o valor de venda e data de validade.
    @Override
	public String toString() {
    	
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	
        String dados = super.toString();
        dados += "\nVálido até " + formato.format(dataDeValidade);
    	
        return dados;
	}

    /**
    * Gera uma linha de texto a partir dos dados do produto. Preço e margem de lucro vão formatados com 2 casas
    decimais.
    * Data de validade vai no formato dd/mm/aaaa
    * @return Uma string no formato "2; descrição;preçoDeCusto;margemDeLucro;dataDeValidade"
    */
    @Override
    public String gerarDadosTexto() {
        /*Você deve implementar aqui a lógica que monta a String com os atributos do objeto ProdutoPerecivel,
        respeitando o formato do arquivo de dados. */
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(("dd/MM/yyyy"));
        String precoFormatado = String.format("%.2f", precoCusto).replace(",",".");
        String margemFormatada = String.format("%.2f", margemLucro).replace(",",".");
        String dataFormatada = formato.format(dataDeValidade);
        return String.format("2;%s;%s;%s", descricao, precoFormatado, margemFormatada);
    }
}   


