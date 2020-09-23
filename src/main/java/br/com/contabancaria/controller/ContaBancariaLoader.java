package br.com.contabancaria.controller;

import br.com.contabancaria.entity.ContaBancaria;
import br.com.contabancaria.repository.ContaBancariaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class ContaBancariaLoader {

	private static final String CONTAS_BANCARIAS_PATH = "data/contas_bancarias.csv";

	private final ContaBancariaRepository repository;

	public ContaBancariaLoader(ContaBancariaRepository repository) {
		this.repository = repository;
	}

	public void loadContaBancaria() throws IOException, URISyntaxException {
		if(!importado()){
			importarContaBancaria();
		} else {
			throw new RuntimeException("Contas bancárias já importadas");
		}
	}

	private boolean importado() {
		return repository.existsById(1l);
	}

	private void importarContaBancaria() throws URISyntaxException, IOException {
		List<String> lines = openCsv();
		lines.stream().skip(1)
				.forEach(line -> {
					line = normalizeLine(line);
					ContaBancaria conta = parseLine(line);
					repository.save(conta);
				});
	}

	private String normalizeLine(String line) {
		char[] chars = line.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if(chars[i] == '"'){
				while(++i < chars.length && chars[i] != '"'){
					if(chars[i] == ','){
						chars[i] = '.';
					}
				}
			}
		}
		return new String(chars);
	}

	private ContaBancaria parseLine(String line) {
		String[] cb = line.replace("\"", "").split(",");
		ContaBancaria entity = new ContaBancaria();
		entity.setNome(cb[0]);
		entity.setNumeroConta(Long.parseLong(cb[1]));
		entity.setAgencia(Long.parseLong(cb[2]));
		entity.setChequeEspecialLiberado(Integer.parseInt(cb[3]));
		entity.setSaldo(parseBigDecimal(cb[4]));
		entity.setChequeEspecial(parseBigDecimal(cb[5]));
		entity.setTaxa(parseBigDecimal(cb[6]));

		return entity;
	}

	private BigDecimal parseBigDecimal(String value) {
		while(StringUtils.countMatches(value, ".") > 1){
			value = StringUtils.replaceOnce(value, ".", "");
		}
		return new BigDecimal(value);
	}

	private List<String> openCsv() throws URISyntaxException, IOException {
		URL resource = getClass().getClassLoader().getResource(CONTAS_BANCARIAS_PATH);
		if (resource == null) {
			throw new IllegalArgumentException("CSV not found!");
		} else {
			Path path = Paths.get(resource.toURI());
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			return lines;
		}
	}

}

