package fr.oc.nico.clambering;

import fr.oc.nico.clambering.entity.Test;
import fr.oc.nico.clambering.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClamberingApplication implements CommandLineRunner {

	@Autowired
	TestRepository testRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClamberingApplication.class, args);
		System.out.println("L'application a démarré");

	}


	@Override
	public void run(String... args) throws Exception {

		Test test = new Test();
		test.setName("test");

		testRepository.save(test);
		System.out.println(">>> Creation instance 'Test'");

	}
}
