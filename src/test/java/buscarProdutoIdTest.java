import org.example.Catalogo;
import org.example.Pedido;
import org.example.Produto;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class buscarProdutoIdTest {
    @Test
    public void testBuscarProdutoPorIdExistente() {
        // Criando um catálogo
        Catalogo catalogo = new Catalogo();

        // Adicionando produtos
        Produto produto1 = new Produto(1, "Camisa", 50.0f);
        Produto produto2 = new Produto(2, "Calça", 80.0f);
        catalogo.adicionarProduto(produto1);
        catalogo.adicionarProduto(produto2);

        // Buscando produto pelo ID existente
        Produto buscado = catalogo.buscarProdutoPorId(1);
        assertNotNull(buscado);
        assertEquals("Camisa", buscado.getNome());
        }

    @Test
     public void testBuscarProdutoPorIdInexistente() {
        // Criando um catálogo
        Catalogo catalogo = new Catalogo();
        // Adicionando produtos
        Produto produto1 = new Produto(1, "Camisa", 50.0f);
        Produto produto2 = new Produto(2, "Calça", 80.0f);
        catalogo.adicionarProduto(produto1);
        catalogo.adicionarProduto(produto2);

         // Buscando produto por um ID inexistente
        Produto inexistente = catalogo.buscarProdutoPorId(99);
        assertNull(inexistente);
    }
}


