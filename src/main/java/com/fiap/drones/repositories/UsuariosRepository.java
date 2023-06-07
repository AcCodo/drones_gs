package com.fiap.drones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fiap.drones.models.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, String>{
	
	@Query("select us from Usuario us where us.username = ?1 and us.password = ?2")
	public List<Usuario> findByUNPW(String username, String password);
	
}

