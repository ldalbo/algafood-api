package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.*;


import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {
    private static final String MSG_RESTAURANTE_EM_USO = "O Restaurante %s está em uso";
    private static final String MSG_PRODUTO_INVALIDO = "Produto %s não pertence a resaturante %s ";
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private FormaPagamentoService cadastroFormaPagamento;

    @Transactional
    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        // Caso não existam, lança exceção
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
        Cidade cidade  =  cadastroCidade.buscarOuFalhar(cidadeId);

        restaurante.getEndereco().setCidade(cidade);
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void exluir(Long restauranteId){
        try{
            restauranteRepository.deleteById(restauranteId);
            restauranteRepository.flush();
        }
        catch (EmptyResultDataAccessException e){
            throw new RestauranteNaoEncontradoException( restauranteId);
        }
        catch (DataIntegrityViolationException e){
            throw  new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
        }
    }




    public Restaurante buscarOuFalhar(Long id){
        return restauranteRepository.findById(id)
                .orElseThrow(() ->new RestauranteNaoEncontradoException(
                        id));
    }


    public Produto buscarOuFalharProduto(Long produtoId, Long restauranteId  )  {


        return produtoRepository.findByIdAndRestauranteId(produtoId,restauranteId)
                .orElseThrow(() ->new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_INVALIDO,produtoId,restauranteId)) {
                });

    }

    @Transactional
    public void ativar(Long restauranteId ){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        // Não precisa de save, pois o JPA já faz o commit
        restauranteAtual.ativar();
    }


    @Transactional
    public void inativar(Long restauranteId ){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        // Não precisa de save, pois o JPA já faz o commit
        restauranteAtual.inativar();
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId){
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
        restaurante.adicionarFormaPagamento(formaPagamento);
    }
    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId){
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
        restaurante.removerFormaPagamento(formaPagamento);
    }
    @Transactional
    public Produto adicionarProduto(Long restauranteId, Produto produto){
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        produto.setRestaurante(restaurante);
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizarProdutoRestaurante(Produto produto ){
        return produtoRepository.save(produto);
    }






}
