package com.simpleCRUD.renox.controller;


import com.simpleCRUD.renox.entity.Empleado;
import com.simpleCRUD.renox.repository.EmpleadoRepository;
import com.simpleCRUD.renox.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor //para no usar un constructor para empleado service
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    //El Controller maneja las peticiones HTTP y expone nuestra API REST.
    private final EmpleadoService empleadoService;

    //Get-obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados(){
        List<Empleado> empleados = empleadoService.findAll();
        return ResponseEntity.ok(empleados);
    }
    //get-obtener empleado por id
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id){
       return empleadoService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //post-crear nuevo empleado

    @PostMapping
    public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado){
        Empleado nuevoEmpleado = empleadoService.save(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    //put-actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleado){
        try{
            Empleado empleadoActualizado = empleadoService.update(id,empleado);
            return ResponseEntity.ok(empleadoActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //delete-borrar empleado

    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> deleteEmpleado(@PathVariable Long id){
        try {
            empleadoService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

}
