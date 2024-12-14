import org.example.Catalogo;
import org.example.Produto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.example.Pedido;

import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {

    private static final Logger logger = Logger.getLogger(IntegracaoTest.class.getName());

    private Catalogo catalogo;
    private Pedido pedido;

    @BeforeAll
    public static void configurarLogger() {
        try {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new SimpleFormatter());

            FileHandler fileHandler = new FileHandler("meu.log", true);
            fileHandler.setEncoding("UTF-8");
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());

            // Adicionar handlers ao logger
            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);

            // Configuração do Logger
            logger.setLevel(Level.ALL);

            logger.info("Logger configurado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao configurar o logger: " + e.getMessage());
        }
    }

    @BeforeEach
    public void setup() {
        try {
            catalogo = new Catalogo();
            pedido = new Pedido("Cliente Teste");
            logger.info("Setup inicial concluído: Catalogo e Pedido criados.");
        } catch (Exception e) {
            logger.warning("Erro durante o setup: " + e.getMessage());
        }
    }

    @Test
    public void testeAdicionarProdutosAoCatalogo() {
        logger.info("Iniciando teste: testeAdicionarProdutosAoCatalogo");

        try {
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

            logger.info("Produtos adicionados ao catálogo.");

            // Verificar se os produtos foram adicionados corretamente
            assertEquals(produto1, catalogo.buscarProdutoPorId(1));
            assertEquals(produto2, catalogo.buscarProdutoPorId(2));

            logger.info("Teste testeAdicionarProdutosAoCatalogo concluído com sucesso.");
        } catch (Exception e) {
            logger.warning("Erro no testeAdicionarProdutosAoCatalogo: " + e.getMessage());
        }
    }

    @Test
    public void testeCriarPedidoComMultiplosProdutos() {
        logger.info("Iniciando teste: testeCriarPedidoComMultiplosProdutos");

        try {
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

            logger.info("Produtos adicionados ao pedido.");

            // Verificar se os produtos foram adicionados ao pedido
            assertTrue(pedido.toString().contains("Produto 1"));
            assertTrue(pedido.toString().contains("Produto 2"));

            logger.info("Teste testeCriarPedidoComMultiplosProdutos concluído com sucesso.");
        } catch (Exception e) {
            logger.warning("Erro no testeCriarPedidoComMultiplosProdutos: " + e.getMessage());
        }
    }

    @Test
    public void testeCalcularTotalPedidoComDesconto() {
        logger.info("Iniciando teste: testeCalcularTotalPedidoComDesconto");

        try {
            // Mock de produtos
            Produto produto1 = Mockito.mock(Produto.class);
            Produto produto2 = Mockito.mock(Produto.class);

            // Configurar os mocks
            Mockito.when(produto1.getPreco()).thenReturn(50.0f);
            Mockito.when(produto2.getPreco()).thenReturn(75.0f);

            // Adicionar produtos ao pedido
            pedido.adicionarProduto(produto1);
            pedido.adicionarProduto(produto2);

            logger.info("Produtos adicionados ao pedido.");

            // Calcular total do pedido
            float total = pedido.calcularTotal();

            // Verificar o valor total com desconto
            assertEquals(112.5f, total, 0.01f); // Desconto de 10% aplicado

            logger.info("Total calculado com desconto: " + total);
        } catch (Exception e) {
            logger.warning("Erro no testeCalcularTotalPedidoComDesconto: " + e.getMessage());
        }
    }

    @Test
    public void testeCalcularTotalPedidoSemDesconto() {
        logger.info("Iniciando teste: testeCalcularTotalPedidoSemDesconto");

        try {
            // Mock de produto
            Produto produto = Mockito.mock(Produto.class);

            // Configurar o mock
            Mockito.when(produto.getPreco()).thenReturn(50.0f);

            // Adicionar produto ao pedido
            pedido.adicionarProduto(produto);

            logger.info("Produto adicionado ao pedido.");

            // Calcular total do pedido
            float total = pedido.calcularTotal();

            // Verificar o valor total sem desconto
            assertEquals(50.0f, total, 0.01f);

            logger.info("Total calculado sem desconto: " + total);
        } catch (Exception e) {
            logger.warning("Erro no testeCalcularTotalPedidoSemDesconto: " + e.getMessage());
        }
    }
}
