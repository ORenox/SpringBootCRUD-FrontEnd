package com.simpleCRUD.renox.service;


import com.simpleCRUD.renox.entity.Empleado;
import com.simpleCRUD.renox.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    //El service contiene toda la lógica de negocio, aquí se implementa el CRUD
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    //findAll()
    public List<Empleado> findAll(){
        return empleadoRepository.findAll();
    }

    //findById()
    public Optional<Empleado> findById(Long id){
        return empleadoRepository.findById(id);
    }

    //save()
    public Empleado save(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    //update()
    public Empleado update(Long id, Empleado empleadoActualizado){

        //Verifico si existe el empleado
        Empleado empleadoExistente = empleadoRepository.findById(id).orElseThrow(()-> new RuntimeException("Empleadono encontrado con ID: "+ id));

        //Actualizar los campos

        empleadoExistente.setNombre(empleadoActualizado.getNombre());
        empleadoExistente.setApellido(empleadoActualizado.getApellido());
        empleadoExistente.setEmail(empleadoActualizado.getEmail());
        empleadoExistente.setSalario(empleadoActualizado.getSalario());
        empleadoExistente.setFechaIngreso(empleadoActualizado.getFechaIngreso());

        return empleadoRepository.save(empleadoExistente);

    }

    //delete()
    public void delete(Long id){
        //verificar si existe antes de eliminar
        if(!empleadoRepository.existsById(id)){
            throw new RuntimeException("El empleado no existe con id: "+id);
        }

        empleadoRepository.deleteById(id);
    }

    public Optional<Empleado> findByEmail(String email){
        return empleadoRepository.findByEmail(email);
    }

    //método para buscar por nombre

    public List<Empleado> findByNombre(String nombre){
        return empleadoRepository.findByNombreContainingIgnoreCase(nombre);
    }


}
