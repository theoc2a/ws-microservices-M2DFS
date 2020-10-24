package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.controller.ProductController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicrocommerceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Mock
	Map<Integer, Product> productList;
	@InjectMocks
	ProductController productController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@org.testng.annotations.Test
	void testListeProduits() {
		Map<Integer, Product> result = productController.listeProduits();
		Assertions.assertEquals(new HashMap<Integer, Product>() {{
			put(0, new Product(0, "Ordinateur portable", 350, 230));
			put(1, new Product(1, "Aspirateur Robot", 500, 300));
			put(2, new Product(2, "Table de Ping Pong", 750, 350));
		}}, result);
	}

	@org.testng.annotations.Test
	void testAfficherUnProduit() {
		Product result = productController.afficherUnProduit(1);
		Assertions.assertEquals(new Product(1, "Aspirateur Robot", 500, 300), result);
	}

	@org.testng.annotations.Test
	void testAjouterProduit() {
		productController.ajouterProduit("test", 50, 1000);
	}

	@org.testng.annotations.Test
	void testSupprimerProduit() {
		productController.supprimerProduit(0);
	}

	@org.testng.annotations.Test
	void testUpdateProduit() {
		productController.updateProduit(0, 0, "valeur");
	}

	@org.testng.annotations.Test
	void testCalculerMargeProduit() {
		int result = productController.calculerMargeProduit(0);
		Assertions.assertEquals(120, result);
	}

	@org.testng.annotations.Test
	void testTrierProduitsParOrdreAlphabetique() {
		List<Product> result = productController.trierProduitsParOrdreAlphabetique();
		Assertions.assertEquals(Arrays.<Product>asList(new Product(0, "nom", 0, 0)), result);
	}

}
