package com.example.demo.controller;

import com.example.demo.model.Adiantamento;
import com.example.demo.model.Lancamento;
import com.example.demo.model.Muda;
import com.example.demo.repository.AdiantamentoRepository;
import com.example.demo.repository.LancamentoRepository;
import com.example.demo.repository.MudaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
// ESTES SÃO OS IMPORTS QUE ESTAVAM FALTANDO
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProdutoController {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private MudaRepository mudaRepository;

    @Autowired 
    private AdiantamentoRepository adiantamentoRepository;

    @GetMapping("/") 
    public String exibirApresentacao() {
        return "index"; 
    }

    @GetMapping("/menu")
    public String exibirMenu() {
        return "menu"; 
    }
    
    @GetMapping("/pesquisa")
    public String pesquisar(@RequestParam(value = "termo", required = false) String termo, Model model) {
        if (termo != null && !termo.isEmpty()) {
            model.addAttribute("termoDigitado", termo);
            
            try {
                Integer id = Integer.parseInt(termo);
                model.addAttribute("resultadosLancamentos", repository.findByIdResponsavel(id));
                model.addAttribute("resultadosAdiantamentos", adiantamentoRepository.findByIdResponsavel(id));
            } catch (NumberFormatException e) {
                model.addAttribute("resultadosLancamentos", repository.findByUsuarioNomeContainingIgnoreCase(termo));
                model.addAttribute("resultadosAdiantamentos", adiantamentoRepository.findByUsuarioNomeContainingIgnoreCase(termo));
            }
        }
        return "pesquisa";
    }

    @GetMapping("/mudas")
    public String listarMudas(Model model) {
        List<Muda> listaMudas = mudaRepository.findAll();
        model.addAttribute("mudas", listaMudas);
        return "mudas";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam String tipo, 
                         @RequestParam Integer idResponsavel, 
                         @RequestParam String nomeProduto, 
                         @RequestParam Double preco,
                         @RequestParam String usuario) {
    
        Lancamento lancamento = new Lancamento();
        lancamento.setTipo(tipo);
        lancamento.setIdResponsavel(idResponsavel); 
        lancamento.setNomeProduto(nomeProduto);
        lancamento.setPreco(preco);
        lancamento.setUsuarioNome(usuario);
        
        repository.save(lancamento);
        return "redirect:/menu"; 
    }

    @GetMapping("/relatorio-geral")
    public String exibirRelatorioGeral(Model model) {
    List<Lancamento> todosLancamentos = repository.findAll();
    List<Adiantamento> todosAdiantamentos = adiantamentoRepository.findAll();

    Map<Integer, String> nomesMap = new HashMap<>();
    Map<Integer, Double> somaDespesas = new HashMap<>();
    Map<Integer, Double> somaAdiantamentos = new HashMap<>();
    Map<Integer, List<Lancamento>> detalhesDespesas = new HashMap<>();
    Map<Integer, List<Adiantamento>> detalhesAdiantamentos = new HashMap<>();

    for (Adiantamento a : todosAdiantamentos) {
        Integer id = a.getIdResponsavel();
        if (id != null) {
            nomesMap.put(id, a.getNomeRecebeu()); 
            somaAdiantamentos.put(id, somaAdiantamentos.getOrDefault(id, 0.0) + (a.getValor() != null ? a.getValor() : 0.0));
            detalhesAdiantamentos.computeIfAbsent(id, k -> new ArrayList<>()).add(a);
        }
    }

    for (Lancamento l : todosLancamentos) {
        Integer id = l.getIdResponsavel();
        if (id != null) {
            nomesMap.putIfAbsent(id, l.getUsuarioNome());
            somaDespesas.put(id, somaDespesas.getOrDefault(id, 0.0) + (l.getPreco() != null ? l.getPreco() : 0.0));
            detalhesDespesas.computeIfAbsent(id, k -> new ArrayList<>()).add(l);
        }
    }

    model.addAttribute("ids", nomesMap.keySet());
    model.addAttribute("nomesMap", nomesMap);
    model.addAttribute("somaDespesas", somaDespesas);
    model.addAttribute("somaAdiantamentos", somaAdiantamentos);
    model.addAttribute("detalhesDespesas", detalhesDespesas); 
    model.addAttribute("detalhesAdiantamentos", detalhesAdiantamentos);

        return "relatorio_geral";
    }

    @PostMapping("/excluir-despesa")
    public String excluirDespesa(@RequestParam("id") Long id) {
    repository.deleteById(id);
        return "redirect:/relatorio-geral";
    }

    @PostMapping("/excluir-adiantamento")
    public String excluirAdiantamento(@RequestParam("id") Long id) {
    adiantamentoRepository.deleteById(id);
        return "redirect:/relatorio-geral";
    }
}