package com.crudvendedor.crudvendedor.service.impl;

import com.crudvendedor.crudvendedor.dto.TipoDTO;
import com.crudvendedor.crudvendedor.dto.VendedorDTO;
import com.crudvendedor.crudvendedor.exception.ObjectNotFoundException;
import com.crudvendedor.crudvendedor.model.dao.TipoRepository;
import com.crudvendedor.crudvendedor.model.dao.VendedorRepository;
import com.crudvendedor.crudvendedor.model.entity.Tipo;
import com.crudvendedor.crudvendedor.model.entity.Vendedor;
import com.crudvendedor.crudvendedor.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService {

    @Autowired
    VendedorRepository vendedorRepository;

    @Autowired
    TipoRepository tipoVendedorRepository;

    @Override
    public List<VendedorDTO> getAll() {
        return vendedorRepository.getAll();
    }

    @Override
    public VendedorDTO findOneById(Long vendedorId) {
        //Verifico si el vendedor existe
        Vendedor vendedorDB = vendedorRepository.findById(vendedorId)
                .orElseThrow( ()-> new ObjectNotFoundException("El vendedor "+vendedorId+" no existe en el sistema"));

        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setId(vendedorId);
        vendedorDTO.setNombre(vendedorDB.getNombre());
        vendedorDTO.setEdad(vendedorDB.getEdad());
        vendedorDTO.setTipo(vendedorDB.getTipoVendedor().getId());
        vendedorDTO.setFecha(vendedorDB.getFecha());

        vendedorDTO.setTipoVendedor(new TipoDTO(vendedorDB.getTipoVendedor().getId(),vendedorDB.getTipoVendedor().getDescripcion()));

        return vendedorDTO;

    }

    @Override
    public VendedorDTO createOne(VendedorDTO vendedorDTO) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(vendedorDTO.getNombre());
        vendedor.setEdad(vendedorDTO.getEdad());
        vendedor.setFecha(new Date());

        Tipo tipoVendedor = tipoVendedorRepository.findById(vendedorDTO.getTipo())
                .orElseThrow( ()-> new ObjectNotFoundException("El tipo de vendedor indicado no existe en el sistema"));

        vendedor.setTipoVendedor(tipoVendedor);
        vendedorRepository.save(vendedor);
        return vendedorDTO;
    }

    @Override
    public VendedorDTO updateOne(VendedorDTO vendedorDTO) {
        //Verifico si el vendedor existe
        Vendedor vendedorDB = vendedorRepository.findById(vendedorDTO.getId())
                .orElseThrow( ()-> new ObjectNotFoundException("El vendedor "+vendedorDTO.getNombre()+" no existe en el sistema"));

        //Verifico el Id del tipo de vendedor
        Tipo tipoVendedor=tipoVendedorRepository.findById(vendedorDTO.getTipo())
                .orElseThrow();

        //Actualizo el Vendedor
        vendedorDB.setNombre(vendedorDTO.getNombre());
        vendedorDB.setEdad(vendedorDTO.getEdad());
        vendedorDB.setTipoVendedor(tipoVendedor);
        vendedorRepository.save(vendedorDB);

        vendedorDTO.setEdad(vendedorDB.getEdad());
        vendedorDTO.setFecha(vendedorDB.getFecha());
        return vendedorDTO;
    }

    @Override
    public void deleteOneById(Long vendedorId) {
        //Verificar si el vendedor existe
        Vendedor vendedorDB = vendedorRepository.findById(vendedorId)
                .orElseThrow( ()-> new ObjectNotFoundException("El vendedor "+vendedorId+" no existe en el sistema"));

        vendedorRepository.deleteById(vendedorId);

    }
}
