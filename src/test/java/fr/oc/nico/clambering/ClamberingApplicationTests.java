package fr.oc.nico.clambering;

import fr.oc.nico.clambering.entity.Essai;
import fr.oc.nico.clambering.repository.EssaiRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClamberingApplicationTests {

	@Autowired
	EssaiRepository essaiRepository;

	@Test
	public void contextLoads() {

	}


	@Test
	public void test() {
		Essai test = new Essai();
		test.setName("4test");

		essaiRepository.save(test);
		System.out.println("pause");
	}
}
