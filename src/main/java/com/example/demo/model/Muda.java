package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Muda {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    
    private String variedade;
    private double precoUnitario;
    private int estoque;

    public Muda() {
    }

    public Muda(int id, String variedade, double precoUnitario, int estoque) {
        this.id = id;
        this.variedade = variedade;
        this.precoUnitario = precoUnitario;
        this.estoque = estoque;
    }

    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }

    public String getVariedade() { 
        return variedade;
    }
    
    public void setVariedade(String variedade) { 
        this.variedade = variedade; 
    }

    public double getPrecoUnitario() { 
        return precoUnitario; 
    }
    
    public void setPrecoUnitario(double precoUnitario) { 
        this.precoUnitario = precoUnitario; 
    }

    public int getEstoque() { 
        return estoque; 
    }
    
    public void setEstoque(int estoque) { 
        this.estoque = estoque; 
    }
}