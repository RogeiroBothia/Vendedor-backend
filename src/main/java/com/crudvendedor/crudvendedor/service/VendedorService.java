package com.crudvendedor.crudvendedor.service;

import com.crudvendedor.crudvendedor.dto.VendedorDTO;

import java.util.List;

public interface VendedorService {

    List<VendedorDTO> getAll();
    VendedorDTO findOneById(Long productId);
    VendedorDTO createOne(VendedorDTO vendedorDTO);
    VendedorDTO updateOne(VendedorDTO vendedorDTO);
    void deleteOneById(Long vendedorId);
}
