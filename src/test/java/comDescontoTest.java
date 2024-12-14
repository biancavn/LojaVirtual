import org.example.Produto;
import org.example.Pedido;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class comDescontoTest {
    @Test
    public void testCalculoTotalPedidoComDesconto() {
        // Criando um pedido
        Pedido pedido = new Pedido("Cliente Teste");

        // Adicionando produtos
        Produto produto1 = new Produto(1, "Camisa", 50.0f);
        Produto produto2 = new Produto(2, "Tênis", 120.0f);
        pedido.adicionarProduto(produto1);
        pedido.adicionarProduto(produto2);

        // Verificando o total com desconto
        float total = pedido.calcularTotal();
        assertEquals(153.0f, total, 0.01f); // 10% de desconto em 170
    }
}
