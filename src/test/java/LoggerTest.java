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

    private static final Logger logger = Logger.getLogger(LoggerTest.class.getName());

    private Catalogo catalogo;
    private Pedido pedido;

    @BeforeAll
    public static void configurarLogger() {
        try {
            logger.setUseParentHandlers(false); // Desativa os handlers padrão

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new SimpleFormatter());
        //Criando meu arquivo para salvar os loggers
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
            Produto produto1 = Mockito.mock(Produto.class);
            Produto produto2 = Mockito.mock(Produto.class);

            Mockito.when(produto1.getId()).thenReturn(1);
            Mockito.when(produto1.getNome()).thenReturn("Produto 1");
            Mockito.when(produto1.getPreco()).thenReturn(50.0f);

            Mockito.when(produto2.getId()).thenReturn(2);
            Mockito.when(produto2.getNome()).thenReturn("Produto 2");
            Mockito.when(produto2.getPreco()).thenReturn(75.0f);

            logger.info("Criando produtos: Produto 1 e Produto 2.");

            catalogo.adicionarProduto(produto1);
            logger.info("Produto 1 adicionado ao catálogo.");

            catalogo.adicionarProduto(produto2);
            logger.info("Produto 2 adicionado ao catálogo.");

            assertEquals(produto1, catalogo.buscarProdutoPorId(1));
            assertEquals(produto2, catalogo.buscarProdutoPorId(2));

            logger.info("Produtos encontrados no catálogo com sucesso.");
        } catch (Exception e) {
            logger.warning("Erro no testeAdicionarProdutosAoCatalogo: " + e.getMessage());
        }
    }

    @Test
    public void testeCriarPedidoComMultiplosProdutos() {
        logger.info("Iniciando teste: testeCriarPedidoComMultiplosProdutos");

        try {
            Produto produto1 = Mockito.mock(Produto.class);
            Produto produto2 = Mockito.mock(Produto.class);

            Mockito.when(produto1.getId()).thenReturn(1);
            Mockito.when(produto1.getPreco()).thenReturn(50.0f);
            Mockito.when(produto1.toString()).thenReturn("ID: 1, Nome: Produto 1, Preço: R$ 50.0");

            Mockito.when(produto2.getId()).thenReturn(2);
            Mockito.when(produto2.getPreco()).thenReturn(75.0f);
            Mockito.when(produto2.toString()).thenReturn("ID: 2, Nome: Produto 2, Preço: R$ 75.0");

            logger.info("Adicionando produtos ao pedido: Produto 1 e Produto 2.");

            pedido.adicionarProduto(produto1);
            logger.info("Produto 1 adicionado ao pedido.");

            pedido.adicionarProduto(produto2);
            logger.info("Produto 2 adicionado ao pedido.");

            assertTrue(pedido.toString().contains("Produto 1"));
            assertTrue(pedido.toString().contains("Produto 2"));

            logger.info("Produtos verificados no pedido com sucesso.");
        } catch (Exception e) {
            logger.warning("Erro no testeCriarPedidoComMultiplosProdutos: " + e.getMessage());
        }
    }

    @Test
    public void testeCalcularTotalPedidoComDesconto() {
        logger.info("Iniciando teste: testeCalcularTotalPedidoComDesconto");

        try {
            Produto produto1 = Mockito.mock(Produto.class);
            Produto produto2 = Mockito.mock(Produto.class);

            Mockito.when(produto1.getPreco()).thenReturn(50.0f);
            Mockito.when(produto2.getPreco()).thenReturn(75.0f);

            pedido.adicionarProduto(produto1);
            logger.info("Produto 1 adicionado ao pedido.");

            pedido.adicionarProduto(produto2);
            logger.info("Produto 2 adicionado ao pedido.");

            float total = pedido.calcularTotal();
            logger.info("Total do pedido calculado (com desconto): " + total);

            assertEquals(112.5f, total, 0.01f);
        } catch (Exception e) {
            logger.warning("Erro no testeCalcularTotalPedidoComDesconto: " + e.getMessage());
        }
    }

    @Test
    public void testeCalcularTotalPedidoSemDesconto() {
        logger.info("Iniciando teste: testeCalcularTotalPedidoSemDesconto");

        try {
            Produto produto = Mockito.mock(Produto.class);
            Mockito.when(produto.getPreco()).thenReturn(50.0f);

            pedido.adicionarProduto(produto);
            logger.info("Produto adicionado ao pedido.");

            float total = pedido.calcularTotal();
            logger.info("Total do pedido calculado (sem desconto): " + total);

            assertEquals(50.0f, total, 0.01f);
        } catch (Exception e) {
            logger.warning("Erro no testeCalcularTotalPedidoSemDesconto: " + e.getMessage());
        }
    }
}
