package com.crudvendedor.crudvendedor.model.dao;

import com.crudvendedor.crudvendedor.dto.TipoDTO;
import com.crudvendedor.crudvendedor.model.entity.Tipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TipoRepository extends ListCrudRepository<Tipo,Long> {

    @Query(value = "SELECT new com.crudvendedor.crudvendedor.dto.TipoDTO(t.id, t.descripcion) FROM Tipo t",nativeQuery = false)
    List<TipoDTO> getAll();
}
