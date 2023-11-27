package com.crudvendedor.crudvendedor.service.impl;

import com.crudvendedor.crudvendedor.dto.TipoDTO;
import com.crudvendedor.crudvendedor.model.dao.TipoRepository;
import com.crudvendedor.crudvendedor.model.entity.Tipo;
import com.crudvendedor.crudvendedor.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServiceImpl implements TipoService {
    @Autowired
    TipoRepository tipoRepository;
    @Override
    public List<TipoDTO> findAll() {
        return tipoRepository.getAll();
    }
}
