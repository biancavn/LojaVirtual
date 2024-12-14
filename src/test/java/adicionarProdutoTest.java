import org.example.Catalogo;
import org.example.Produto;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class adicionarProdutoTest {
    @Test
    public void testAdicionarEBuscarProdutoCatalogo() {
        // Criando um catálogo
        Catalogo catalogo = new Catalogo();

        // Adicionando produtos
        Produto produto1 = new Produto(1, "Camisa", 50.0f);
        Produto produto2 = new Produto(2, "Calça", 80.0f);
        catalogo.adicionarProduto(produto1);
        catalogo.adicionarProduto(produto2);

        // Capturando a saída do método listarProdutos()
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Verificando se os produtos foram adicionados corretamente
        catalogo.listarProdutos();

        // Converte a saída para uma string e conta as linhas
        String output = outputStream.toString();
        String[] linhas = output.split(System.lineSeparator());

        assertEquals(2, linhas.length);

        // Buscando produto pelo ID
        Produto buscado = catalogo.buscarProdutoPorId(1);
        assertNotNull(buscado);
        assertEquals("Camisa", buscado.getNome());

        // Buscando um ID inexistente
        Produto inexistente = catalogo.buscarProdutoPorId(99);
        assertNull(inexistente);

        // Restaurando a saída padrão do console
        System.setOut(System.out);
    }
}
