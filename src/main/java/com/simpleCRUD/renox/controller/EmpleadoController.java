package com.simpleCRUD.renox.controller;


import com.simpleCRUD.renox.dto.EmpleadoDTO;
import com.simpleCRUD.renox.entity.Empleado;
import com.simpleCRUD.renox.repository.EmpleadoRepository;
import com.simpleCRUD.renox.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor //para no usar un constructor para empleado service
@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "http://localhost:3000") // ← Agrega esta línea
public class EmpleadoController {
    //El Controller maneja las peticiones HTTP y expone nuestra API REST.
    private final EmpleadoService empleadoService;

    //Get-obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> getAllEmpleados(){
        List<Empleado> empleados = empleadoService.findAll();
        List<EmpleadoDTO> empleadosDTO = empleados.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(empleadosDTO);
    }
    //get-obtener empleado por id
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> getEmpleadoById(@PathVariable Long id){

       return empleadoService.findById(id)
               .map(empleado -> ResponseEntity.ok(convertToDTO(empleado)))
                .orElse(ResponseEntity.notFound().build());
    }

    //post-crear nuevo empleado

    @PostMapping
    public ResponseEntity<EmpleadoDTO> createEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO){
        Empleado empleado = convertToEntity(empleadoDTO);
        Empleado nuevoEmpleado = empleadoService.save(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(nuevoEmpleado));
    }

    //put-actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO empleadoDTO){
        try{
            Empleado empleado = convertToEntity(empleadoDTO);
            Empleado empleadoActualizado = empleadoService.update(id,empleado);
            return ResponseEntity.ok(convertToDTO(empleadoActualizado));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //delete-borrar empleado

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id){
        try {
            empleadoService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    private EmpleadoDTO convertToDTO(Empleado empleado){
        return new EmpleadoDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getEmail(),
                empleado.getSalario(),
                empleado.getFechaIngreso()
        );
    }

    private Empleado convertToEntity(EmpleadoDTO empleadoDTO){
        Empleado empleado = new Empleado();
        empleado.setId(empleadoDTO.getId());
        empleado.setApellido(empleadoDTO.getApellido());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setEmail(empleadoDTO.getEmail());
        empleado.setSalario(empleadoDTO.getSalario());
        empleado.setFechaIngreso(empleadoDTO.getFechaIngreso());

        return empleado;
    }

}
