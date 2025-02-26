package Microservice.FoodAPI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Microservice.FoodAPI.Repository.PagamentosRepository;
import jakarta.persistence.EntityNotFoundException;
import Microservice.FoodAPI.DTO.PagamentoDTO;
import Microservice.FoodAPI.Entity.Pagamentos;

@Service
public class PagamentosService {

    @Autowired
    private PagamentosRepository pagamentosRepository;



    public Page<PagamentoDTO> buscarPagamentos(Pageable paginacao) {
        return pagamentosRepository.findAll(paginacao)
                .map(this::convertToDTO);
    }

    private PagamentoDTO convertToDTO(Pagamentos pagamento) {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(pagamento.getId());
        dto.setValor(pagamento.getValor());
        dto.setFormaDePagamentoID(pagamento.getFormaDePagamentoID());
        dto.setStatus(pagamento.getStatus());
        return dto;
    } //converter tudo para Dto (segurnaça dos meus dados)

    public PagamentoDTO buscarPagamentoPorId(Long id) {
        Pagamentos pagamento = pagamentosRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        return convertToDTO(pagamento);
    }

    public PagamentoDTO criarPagamento(PagamentoDTO pagamentoDTO) {
        Pagamentos pagamento = new Pagamentos();
        pagamento.setValor(pagamentoDTO.getValor());
        pagamento.setFormaDePagamentoID(pagamentoDTO.getFormaDePagamentoID());
        pagamento.setStatus(pagamentoDTO.getStatus());
        Pagamentos pagamentoSalvo = pagamentosRepository.save(pagamento);
        return convertToDTO(pagamentoSalvo);
    }


    public PagamentoDTO atualizarPagamento(Long id,PagamentoDTO pagamentoDTO) {
        Pagamentos pagamento = pagamentosRepository.findById(pagamentoDTO.getId()).orElseThrow(() -> new EntityNotFoundException());
        pagamento.setValor(pagamentoDTO.getValor());
        pagamento.setFormaDePagamentoID(pagamentoDTO.getFormaDePagamentoID());
        pagamento.setId(pagamento.getId());
        pagamento.setStatus(pagamentoDTO.getStatus());
        Pagamentos pagamentoSalvo = pagamentosRepository.save(pagamento);
        return convertToDTO(pagamentoSalvo);
    }

    public void deletarPagamento(Long id) {
        pagamentosRepository.deleteById(id);
    }

}

