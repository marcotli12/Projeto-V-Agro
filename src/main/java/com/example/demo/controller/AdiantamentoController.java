package com.example.demo.controller;

import com.example.demo.model.Adiantamento;
import com.example.demo.repository.AdiantamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdiantamentoController {

    @Autowired
    private AdiantamentoRepository adiantamentoRepository;

    @GetMapping("/novoAdiantamento")
    public String abrirFormularioAdiantamento(Model model) {
        model.addAttribute("adiantamento", new Adiantamento());
        return "adiantamento";
    }

   @PostMapping("/salvar-adiantamento")
    public String salvarAdiantamento(@RequestParam Integer idResponsavel, 
                                 @RequestParam Double valor,
                                 @RequestParam String nomeRecebeu) {
    Adiantamento adiant = new Adiantamento();
    adiant.setIdResponsavel(idResponsavel); 
    adiant.setValor(valor);
    adiant.setNomeRecebeu(nomeRecebeu);
    
    adiantamentoRepository.save(adiant);
        return "redirect:/menu";
    }
}