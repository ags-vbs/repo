package com.mycompany.vgtu.domain.calculator;

import com.mycompany.vgtu.domain.BasicEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "calculator")
public class CalculatorJpa extends BasicEntity {

    private String name;
    private String link;

    public CalculatorJpa() {
    }

    public CalculatorJpa(String name, String link) {
        this.name = name;
        this.link = link;
    }   
}
