package Microservice.FoodAPI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Microservice.FoodAPI.DTO.PagamentoDTO;
import Microservice.FoodAPI.Service.PagamentosService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentosService pagamentoService;

    @GetMapping
    public Page<PagamentoDTO> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return pagamentoService.buscarPagamentos(paginacao);
    }

    @GetMapping ("/{id}")
    public PagamentoDTO buscarPorId(@PathVariable Long id) {
        return pagamentoService.buscarPagamentoPorId(id);
    }

    @PostMapping
    public PagamentoDTO criar(@RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.criarPagamento(pagamentoDTO);
    }

    @DeleteMapping
    public void deletar(@PathVariable Long id) {
        pagamentoService.deletarPagamento(id);
    }
}