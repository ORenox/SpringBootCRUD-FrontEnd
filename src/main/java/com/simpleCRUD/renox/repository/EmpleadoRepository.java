package com.simpleCRUD.renox.repository;

import com.simpleCRUD.renox.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {


    //método para buscar por email
    Optional<Empleado> findByEmail(String email);

    //método para buscar por nombre
    List<Empleado> findByNombreContainingIgnoreCase(String nombre);

    //método para buscar por apellido
    List<Empleado> findByApellidoContainingIgnoreCase(String apellido);

    boolean existsByEmail(String email);


}
