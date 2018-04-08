package com.nelioalves.cursomc.services.validation;

import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by italo on 08/04/18.
 */
@Service
public class EstadoService {

    @Autowired
    public EstadoRepository repository;

    public List<Estado> findAllByOrderByNome() {
        return this.repository.findAllByOrderByNome();
    }
}
