package br.com.gabriel;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gabriel.model.Demand;
import br.com.gabriel.model.Product;
import br.com.gabriel.model.User;
import br.com.gabriel.repository.DemandRepository;
import br.com.gabriel.repository.ProductRepository;
import br.com.gabriel.repository.UserRepository;

@SpringBootApplication
public class SprintEvaluation4Application implements CommandLineRunner {

	private ProductRepository productRepository;
	private DemandRepository demandRepository;
	private UserRepository userRepository;

	@Autowired
	public SprintEvaluation4Application(ProductRepository productRepository, DemandRepository demandRepository,
			UserRepository userRepository) {
		this.productRepository = productRepository;
		this.demandRepository = demandRepository;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SprintEvaluation4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product arroz = new Product("Arroz", 15D);
		Product café = new Product("Café", 10D);
		Product feijão = new Product("Feijão", 12D);
		
		productRepository.saveAll(Arrays.asList(arroz, café, feijão));
		
		Demand demand1 = new Demand(Arrays.asList(arroz, café, feijão));
		Demand demand2 = new Demand(Arrays.asList(arroz, café));
		Demand demand3 = new Demand(Arrays.asList(arroz));
		
		demandRepository.saveAll(Arrays.asList(demand1, demand2, demand3));
		
		User user = new User("gabriel", "$2a$10$ot6/wiQtd57q0c8X.HvVyuXl7E.75Mc9ujjC5xMSf//WuJ1Uch.c6");
		userRepository.save(user);
	}

}
