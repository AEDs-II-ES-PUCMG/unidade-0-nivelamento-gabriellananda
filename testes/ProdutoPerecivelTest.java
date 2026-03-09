import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProdutoPerecivelTest {
    Produto produto;
    @BeforeEach
    public void prepare(){
        produto = new ProdutoPerecivel("Perecível teste", 100, 0.1,
        LocalDate.now().plusDays(10));
    }

    @Test
    public void calculaPrecoSemDescontoCorretamente(){
        assertEquals(110.0, produto.valorDeVenda(), 0.01);
    }
        
    @Test
        public void calculaPrecoComDescontoCorretamente(){
        produto = new ProdutoPerecivel("Perecível teste", 100, 0.1,
        LocalDate.now().plusDays(2));
        assertEquals(110.0 * 0.75, produto.valorDeVenda(), 0.01);
    }

    @Test
    public void naoCriaProdutoForaDaValidade(){
        assertThrows(IllegalArgumentException.class, () -> new
        ProdutoPerecivel("teste", 5, 1, LocalDate.now().minusDays(2)));
    }

    @Test
    public void criarCorretamenteAPartirDeTexto(){
        String linhaDados = "2;Produto perecível do arquivo;10.0;0.2;25/10/2025";
        produto = Produto.criarDoTexto(linhaDados);
        String desc = produto.toString();
        assertTrue(desc.contains("Produto perecível do arquivo") &&
        desc.contains("R$ 12,00") && desc.contains("25/10/2025"));
    }

    @Test
    public void criaDadosEmTextoCorretamente(){
        String dataFormatada =
        DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now().plusDays(10));
        assertEquals("2;Perecível teste;100.00;0.10;"+dataFormatada,
        produto.gerarDadosTexto());
    }
}