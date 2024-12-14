import org.example.Catalogo;
import org.example.Pedido;
import org.example.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IntegracaoTest {

    private Catalogo catalogoMock;
    private Pedido pedido;

    @BeforeEach
    public void setup() {
        // Criar um mock de Catalogo
        catalogoMock = mock(Catalogo.class);
        pedido = new Pedido("Cliente Teste");
    }

    @Test
    public void testeFluxoCatalogo() {
        // Criar produtos simulados
        Produto produto1 = new Produto(1, "Produto 1", 50.0f);
        Produto produto2 = new Produto(2, "Produto 2", 75.0f);
        Produto produto3 = new Produto(3, "Produto 3", 30.0f);

        // Configurar o comportamento do mock
        when(catalogoMock.buscarProdutoPorId(1)).thenReturn(produto1);
        when(catalogoMock.buscarProdutoPorId(2)).thenReturn(produto2);
        when(catalogoMock.buscarProdutoPorId(3)).thenReturn(produto3);

        // Verificar se o mock retorna os produtos corretamente
        assertEquals(produto1, catalogoMock.buscarProdutoPorId(1));
        assertEquals(produto2, catalogoMock.buscarProdutoPorId(2));
        assertEquals(produto3, catalogoMock.buscarProdutoPorId(3));

        // Adicionar produtos ao pedido (produto1 e produto2 com desconto)
        pedido.adicionarProduto(produto1);
        pedido.adicionarProduto(produto2);

        // Verificar a descrição do pedido
        assertTrue(pedido.toString().contains("Produto 1"));
        assertTrue(pedido.toString().contains("Produto 2"));

        // Calcular o total do pedido (com desconto)
        float totalComDesconto = pedido.calcularTotal();
        assertEquals(112.5f, totalComDesconto, 0.01f);

        // Criar um novo pedido com apenas produto3 (sem desconto)
        Pedido pedidoSemDesconto = new Pedido("Cliente Sem Desconto");
        pedidoSemDesconto.adicionarProduto(produto3);

        // Calcular o total do pedido (sem desconto)
        float totalSemDesconto = pedidoSemDesconto.calcularTotal();
        assertEquals(30.0f, totalSemDesconto, 0.01f);

        // Verificar interações com o mock
        verify(catalogoMock).buscarProdutoPorId(1);
        verify(catalogoMock).buscarProdutoPorId(2);
        verify(catalogoMock).buscarProdutoPorId(3);
    }
}
