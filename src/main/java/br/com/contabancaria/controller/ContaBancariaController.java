package br.com.contabancaria.controller;

import br.com.contabancaria.entity.ContaBancaria;
import br.com.contabancaria.entity.ContaBancariaDTO;
import br.com.contabancaria.service.ContaBancariaService;
import br.com.contabancaria.util.ListaPaginada;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/conta-bancaria")
public class ContaBancariaController {

	private final ContaBancariaLoader contaBancariaLoader;
	private final ContaBancariaService contaBancariaService;

	public ContaBancariaController(ContaBancariaLoader contaBancariaLoader, ContaBancariaService contaBancariaService) {
		this.contaBancariaLoader = contaBancariaLoader;
		this.contaBancariaService = contaBancariaService;
	}

	@PostMapping("/load")
	public ResponseEntity load() {
		try {
			contaBancariaLoader.loadContaBancaria();
			return ResponseEntity.ok().build();
		} catch (Exception ex){
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity inserir(@RequestBody ContaBancaria contaBancaria){
		try {
			contaBancariaService.insert(contaBancaria);
			return ResponseEntity.ok().build();
		} catch (Exception ex){
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ContaBancariaDTO detail(@PathVariable("id") Long id){
		return contaBancariaService.detalhar(id);
	}

	@GetMapping("/obter/{numero-conta}")
	public ContaBancaria obter(@PathVariable("numero-conta") String numeroConta){
		return contaBancariaService.obter(numeroConta);
	}

	@GetMapping("/list")
	public ListaPaginada<ContaBancariaDTO> list(ListarContaBancariaDTO listar){
		return contaBancariaService.list(listar);
	}

	@DeleteMapping("/{id})")
	public void delete(@PathVariable("id") Long id){
		this.contaBancariaService.delete(id);
	}

}
