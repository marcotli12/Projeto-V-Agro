package com.example.demo.controller;

import com.example.demo.model.Adiantamento;
import com.example.demo.repository.AdiantamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdiantamentoController {

    @Autowired
    private AdiantamentoRepository adiantamentoRepository;

    @GetMapping("/novoAdiantamento")
    public String abrirFormularioAdiantamento(Model model) {
        model.addAttribute("adiantamento", new Adiantamento());
        return "adiantamento";
    }

    @PostMapping("/salvarAdiantamento")
    public String salvarAdiantamento(Adiantamento adiantamento) {
        
    adiantamento.setUsuarioNome("Admin"); 
    
    adiantamentoRepository.save(adiantamento);
    
        return "redirect:/menu"; 
    }
}