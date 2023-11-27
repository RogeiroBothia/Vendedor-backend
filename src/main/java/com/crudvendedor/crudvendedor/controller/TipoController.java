package com.crudvendedor.crudvendedor.controller;

import com.crudvendedor.crudvendedor.dto.TipoDTO;
import com.crudvendedor.crudvendedor.service.TipoService;
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
        methods = {RequestMethod.GET}
)
@RequestMapping("/tipos")
public class TipoController {

    @Autowired
    TipoService tipoService;
    @GetMapping
    public ResponseEntity<Object> findAll(){
        Map<String,Object> datos = new LinkedHashMap<>();
        try{
            List<TipoDTO> listaTipos = tipoService.findAll();
            datos.put("error", null);
            datos.put("message", "¡Proceso Exitoso!");
            datos.put("datos",listaTipos);
        }catch (Exception e) {
            datos.put("error", e.getMessage());
            datos.put("message", "Ha ocurrido un error mostrar los tipos.");
            datos.put("datos",null);
            return new ResponseEntity<Object>(datos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(datos, HttpStatus.OK);
    }
}
