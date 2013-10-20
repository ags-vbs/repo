package com.mycompany.vgtu.domain.modules;

import com.google.inject.AbstractModule;
import com.mycompany.vgtu.domain.calculator.CalculatorDao;
import com.mycompany.vgtu.domain.calculator.internal.CalculatorDaoImpl;

public class DomainModule extends AbstractModule {

    @Override
    protected void configure() {        
        bind(CalculatorDao.class).to(CalculatorDaoImpl.class);
    }    
}
