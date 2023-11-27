package com.crudvendedor.crudvendedor.controller;

import com.crudvendedor.crudvendedor.dto.VendedorDTO;
import com.crudvendedor.crudvendedor.service.VendedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(
        origins = "http://localhost:3000/",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE}
)
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity <Object> findAll() {

        Map<String,Object> datos = new LinkedHashMap<>();
        try{
            List<VendedorDTO> vendedores = vendedorService.getAll();
            datos.put("error", null);
            datos.put("message", "¡Proceso Exitoso!");
            datos.put("datos",vendedores);
        }catch (Exception e) {
            datos.put("error", e.getMessage());
            datos.put("message", "Ha ocurrido un error mostrar los vendedores.");
            datos.put("datos",null);
            return new ResponseEntity<Object>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(datos, HttpStatus.OK);
    }

    @GetMapping("/{vendedorId}")
    public ResponseEntity<Object> findOneById(@PathVariable Long vendedorId) {

        Map<String,Object> datos = new LinkedHashMap<>();
        try{
            VendedorDTO vendedorDTO = vendedorService.findOneById(vendedorId);
            datos.put("error", null);
            datos.put("message", "¡Proceso Exitoso!");
            datos.put("datos",vendedorDTO);
        }catch (Exception e) {
            datos.put("error", e.getMessage());
            datos.put("message", "Ha ocurrido un error al buscar el vendedor.");
            datos.put("datos",null);
            return new ResponseEntity<Object>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(datos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createOne(@RequestBody @Valid VendedorDTO vendedorDTO) {
        Map<String,Object> datos = new LinkedHashMap<>();
        try{
            vendedorService.createOne(vendedorDTO);
            datos.put("error", null);
            datos.put("message", "¡Proceso Exitoso!");
        }catch (Exception e) {
            datos.put("error", e.getMessage());
            datos.put("message", "Ha ocurrido un error al crear el vendedor.");
            return new ResponseEntity<Object>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(datos, HttpStatus.CREATED);

    }

    @PutMapping()
    public ResponseEntity<Object> updateOneById(@RequestBody @Valid VendedorDTO vendedorDTO) {

        Map<String,Object> datos = new LinkedHashMap<>();
        try{
            vendedorService.updateOne(vendedorDTO);
            datos.put("error", null);
            datos.put("message", "¡Proceso Exitoso!");
        }catch (Exception e) {
            datos.put("error", e.getMessage());
            datos.put("message", "Ha ocurrido un error al actualizar el vendedor.");
            return new ResponseEntity<Object>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(datos, HttpStatus.OK);
    }

    @DeleteMapping("/{vendedorId}")
    public ResponseEntity<Object> updateOneById(@PathVariable Long vendedorId) {

        Map<String,Object> datos = new LinkedHashMap<>();
        try{
            vendedorService.deleteOneById(vendedorId);
            datos.put("error", null);
            datos.put("message", "¡Proceso Exitoso!");
        }catch (Exception e) {
            datos.put("error", e.getMessage());
            datos.put("message", "Ha ocurrido un error al eliminar el vendedor.");
            return new ResponseEntity<Object>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
       return new ResponseEntity<Object>(datos, HttpStatus.OK);
    }
}
