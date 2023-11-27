package com.crudvendedor.crudvendedor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VendedorDTO {
    private Long id;
    private String nombre;

    @Min(value = 18, message = "El vendedor debe ser mayor de edad")
    private byte edad;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fecha;

    private Long tipo;
    private TipoDTO tipoVendedor;

    public VendedorDTO() {
    }

    public VendedorDTO(Long id, String nombre, byte edad, Date fecha, Long tipo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.fecha = fecha;
        this.tipo = tipo;
    }
}
