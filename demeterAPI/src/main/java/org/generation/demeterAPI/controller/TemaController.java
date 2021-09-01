package org.generation.demeterAPI.controller;

import java.util.List;

import org.generation.demeterAPI.model.Tema;
import org.generation.demeterAPI.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tema")
@CrossOrigin("*")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity <List<Tema>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Tema> GetById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).
				orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity <List<Tema>> GetByCategoria(@PathVariable String categoria){
		return ResponseEntity.ok(repository.findAllByCategoriaContainingIgnoreCase(categoria));
	}
	
	@GetMapping("/filtro/{filtro}")
	public ResponseEntity <List<Tema>> GetByFiltro(@PathVariable String filtro){
		return ResponseEntity.ok(repository.findAllByFiltroContainingIgnoreCase(filtro));
	}

	@GetMapping("/localidade/{localidade}")
	public ResponseEntity <List<Tema>> GetByLocalidade(@PathVariable String localidade){
		return ResponseEntity.ok(repository.findAllByLocalidadeContainingIgnoreCase(localidade));
	}
	
	@PostMapping
	public ResponseEntity <Tema> Post (@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity <Tema> Put (@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
	}
	
	@DeleteMapping("/{id}")
	public void Delete (@PathVariable long id) {
		repository.deleteById(id);
	}
}
