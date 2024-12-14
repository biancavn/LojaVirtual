import org.example.Pedido;
import org.example.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class semDescontoTest {
    @Test
    public void testCalculoTotalPedidoSemDesconto() {
        // Criando um pedido
        Pedido pedido = new Pedido("Cliente Teste");

        // Adicionando produtos
        Produto produto1 = new Produto(1, "Camisa", 50.0f);
        Produto produto2 = new Produto(2, "Calça", 30.0f);
        pedido.adicionarProduto(produto1);
        pedido.adicionarProduto(produto2);

        // Verificando o total sem desconto
        float total = pedido.calcularTotal();
        assertEquals(80.0f, total, 0.01f);
    }

}
