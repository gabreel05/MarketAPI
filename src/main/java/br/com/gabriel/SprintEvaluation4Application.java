package br.com.gabriel;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import br.com.gabriel.model.Address;
import br.com.gabriel.model.Demand;
import br.com.gabriel.model.Person;
import br.com.gabriel.model.Product;
import br.com.gabriel.model.User;
import br.com.gabriel.repository.AddressRepository;
import br.com.gabriel.repository.DemandRepository;
import br.com.gabriel.repository.PersonRepository;
import br.com.gabriel.repository.ProductRepository;
import br.com.gabriel.repository.UserRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableSwagger2
public class SprintEvaluation4Application implements CommandLineRunner {

	private ProductRepository productRepository;
	private DemandRepository demandRepository;
	private UserRepository userRepository;
	private AddressRepository addressRepository;
	private PersonRepository personRepository;

	@Autowired
	public SprintEvaluation4Application(ProductRepository productRepository, DemandRepository demandRepository,
			UserRepository userRepository, AddressRepository addressRepository, PersonRepository personRepository) {
		this.productRepository = productRepository;
		this.demandRepository = demandRepository;
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
		this.personRepository = personRepository;
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
		
		Address address1 = new Address("Brasil", "Paraná", "SJP", 83015717, "Rua das flores");
		Address address2 = new Address("Brasil", "Paraná", "Curitiba", 83010571, "Rua dos cravos");
		
		Person gabriel = new Person("Gabriel", "44949381067", 3500D, "M", Arrays.asList(address1, address2));
		
		addressRepository.saveAll(gabriel.getAddresses());
		
		personRepository.save(gabriel);	
	}

}
