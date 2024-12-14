
import org.example.Catalogo;
import org.example.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.example.Pedido;


import static org.junit.jupiter.api.Assertions.*;

public class IntegracaoTest {

    private Catalogo catalogo;
    private Pedido pedido;

    @BeforeEach
    public void setup() {
        catalogo = new Catalogo();
        pedido = new Pedido("Cliente Teste");
    }

    @Test
    public void testeAdicionarProdutosAoCatalogo() {
        // Mock de produtos
        Produto produto1 = Mockito.mock(Produto.class);
        Produto produto2 = Mockito.mock(Produto.class);

        // Configurar os mocks
        Mockito.when(produto1.getId()).thenReturn(1);
        Mockito.when(produto1.getNome()).thenReturn("Produto 1");
        Mockito.when(produto1.getPreco()).thenReturn(50.0f);

        Mockito.when(produto2.getId()).thenReturn(2);
        Mockito.when(produto2.getNome()).thenReturn("Produto 2");
        Mockito.when(produto2.getPreco()).thenReturn(75.0f);

        // Adicionar produtos ao catálogo
        catalogo.adicionarProduto(produto1);
        catalogo.adicionarProduto(produto2);

        // Verificar se os produtos foram adicionados corretamente
        assertEquals(produto1, catalogo.buscarProdutoPorId(1));
        assertEquals(produto2, catalogo.buscarProdutoPorId(2));
    }

    @Test
    public void testeCriarPedidoComMultiplosProdutos() {
        // Mock de produtos
        Produto produto1 = Mockito.mock(Produto.class);
        Produto produto2 = Mockito.mock(Produto.class);

        // Configurar os mocks
        Mockito.when(produto1.getId()).thenReturn(1);
        Mockito.when(produto1.getPreco()).thenReturn(50.0f);
        Mockito.when(produto1.toString()).thenReturn("ID: 1, Nome: Produto 1, Preço: R$ 50.0");

        Mockito.when(produto2.getId()).thenReturn(2);
        Mockito.when(produto2.getPreco()).thenReturn(75.0f);
        Mockito.when(produto2.toString()).thenReturn("ID: 2, Nome: Produto 2, Preço: R$ 75.0");

        // Adicionar produtos ao pedido
        pedido.adicionarProduto(produto1);
        pedido.adicionarProduto(produto2);

        // Verificar se os produtos foram adicionados ao pedido
        assertTrue(pedido.toString().contains("Produto 1"));
        assertTrue(pedido.toString().contains("Produto 2"));
    }


    @Test
    public void testeCalcularTotalPedidoComDesconto() {
        // Mock de produtos
        Produto produto1 = Mockito.mock(Produto.class);
        Produto produto2 = Mockito.mock(Produto.class);

        // Configurar os mocks
        Mockito.when(produto1.getPreco()).thenReturn(50.0f);
        Mockito.when(produto2.getPreco()).thenReturn(75.0f);

        // Adicionar produtos ao pedido
        pedido.adicionarProduto(produto1);
        pedido.adicionarProduto(produto2);

        // Calcular total do pedido
        float total = pedido.calcularTotal();

        // Verificar o valor total com desconto
        assertEquals(112.5f, total, 0.01f); // Desconto de 10% aplicado
    }

    @Test
    public void testeCalcularTotalPedidoSemDesconto() {
        // Mock de produto
        Produto produto = Mockito.mock(Produto.class);

        // Configurar o mock
        Mockito.when(produto.getPreco()).thenReturn(50.0f);

        // Adicionar produto ao pedido
        pedido.adicionarProduto(produto);

        // Calcular total do pedido
        float total = pedido.calcularTotal();

        // Verificar o valor total sem desconto
        assertEquals(50.0f, total, 0.01f);
    }
}
