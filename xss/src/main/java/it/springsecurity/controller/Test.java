package it.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
public class Test {

	@PostMapping(value = "/test/create", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Dto create(@RequestBody Dto dto) {
		return dto;
	}

	@PutMapping(value = "/test/update", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Dto update(@RequestBody Dto dto) {
		return dto;
	}

	@PatchMapping(value = "/test/patch", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Dto patch(@RequestBody Dto dto) {
		return dto;
	}

	@DeleteMapping(value = "/test/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
	}

	@GetMapping(value = "/test/findById/{id}")
	public ResponseEntity<Dto> findById(@PathVariable String id) {
		Dto dto = new Dto();
		dto.setData(id);
		return new ResponseEntity<Dto>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/test/findById2", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> findById2(@PathParam(value = "id") String id) {
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}
}
