package br.com.simulacao.credito.service;

import br.com.simulacao.credito.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService {

    @Autowired
    protected RequestUtils requestUtils;
}