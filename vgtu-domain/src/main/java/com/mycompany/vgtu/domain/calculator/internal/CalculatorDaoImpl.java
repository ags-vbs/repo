package com.mycompany.vgtu.domain.calculator.internal;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.mycompany.vgtu.domain.calculator.CalculatorDao;
import com.mycompany.vgtu.domain.calculator.CalculatorJpa;
import com.mycompany.vgtu.domain.BasicDaoImpl;

@Singleton
@Transactional
public class CalculatorDaoImpl extends BasicDaoImpl<CalculatorJpa, Long> implements CalculatorDao{

    public CalculatorDaoImpl() {
        super(CalculatorJpa.class);
    }
    

}
