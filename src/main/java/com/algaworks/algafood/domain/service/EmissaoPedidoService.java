package com.algaworks.algafood.domain.service;



import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.ItemPedidoRepository;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
public class EmissaoPedidoService {
    private static final String MSG_PRODUTO_INVALIDO = "Produto %s não pertence a resaturante %s ";

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    CadastroRestauranteService cadastroRestaurante;

    @Autowired
    FormaPagamentoService cadastroFormaPagamento;



    @Autowired
    CadastroProdutoService cadastroProduto;

    @Autowired
    CadastroCidadeService cadastroCidade;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Autowired
    UsuarioService cadastroUsuario;

    public Pedido buscarOuFalhar(Long pedidoId)  {

        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() ->new PedidoNaoEncontradoException(pedidoId) {
                });
    }


    @Transactional
    public Pedido emitir(Pedido pedido) {
        // Primeiro pego os atributos do Json
        Long restauranteId = pedido.getRestaurante().getId();
        Long formaPagamentoId = pedido.getFormaPagamento().getId();
        Long cidadeId = pedido.getEnderecoEntrega().getCidade().getId();

        // Busco os elementos persistidos
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
        Usuario cliente = cadastroUsuario.buscarOuFalhar(1L);
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)){
            throw new NegocioException("Restaurante nào tem forma de pagamento " + formaPagamento.getDescricao() );
        }

        // Seto os elementos persistidos
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setFormaPagamento(formaPagamento);


        List<ItemPedido> itens = pedido.getItens();
        BigDecimal totalProdutos = BigDecimal.ZERO;
        BigDecimal taxaFrete = restaurante.getTaxaFrete();

        for ( ItemPedido item : itens){

            Produto produto = cadastroProduto.buscarOuFalhar(restauranteId,
                       item.getProduto().getId());

           item.setProduto(produto);
           item.atribuiValoresItem();

           totalProdutos = totalProdutos.add(item.getPrecoTotal());
           item.setPedido(pedido);

        }
        pedido.setSubTotal(totalProdutos);
        pedido.setTaxaFrete(taxaFrete);
        pedido.setValorTotal(taxaFrete.add(totalProdutos));
        return pedidoRepository.save(pedido);
    }

   /*
   // ALGAWORKS
    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }
    */
    // ALGAWORKS
    private void validarPedido(Pedido pedido) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = cadastroUsuario.buscarOuFalhar(pedido.getCliente().getId());
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = cadastroProduto.buscarOuFalhar(
                    pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }


}
