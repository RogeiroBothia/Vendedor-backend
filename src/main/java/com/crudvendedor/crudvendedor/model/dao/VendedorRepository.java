package com.crudvendedor.crudvendedor.model.dao;

import com.crudvendedor.crudvendedor.dto.VendedorDTO;
import com.crudvendedor.crudvendedor.model.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor,Long> {

    @Query(value = "SELECT new com.crudvendedor.crudvendedor.dto.VendedorDTO(v.id, v.nombre, v.edad, v.fecha," +
            "v.tipoVendedor.id ) FROM Vendedor v",nativeQuery = false)
    List<VendedorDTO> getAll();

}
