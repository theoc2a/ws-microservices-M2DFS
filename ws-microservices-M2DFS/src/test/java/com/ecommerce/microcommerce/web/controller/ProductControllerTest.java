package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class ProductControllerTest {
    @Mock
    Map<Integer, Product> productList;
    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListeProduits() {
        Map<Integer, Product> result = productController.listeProduits();
        Assertions.assertEquals(new HashMap<Integer, Product>() {{
            put(0, new Product(0, "Ordinateur portable", 350, 230));
            put(1, new Product(1, "Aspirateur Robot", 500, 300));
            put(2, new Product(2, "Table de Ping Pong", 750, 350));
        }}, result);
    }

    @Test
    void testAfficherUnProduit() {
        Product result = productController.afficherUnProduit(1);
        Assertions.assertEquals(new Product(1, "Aspirateur Robot", 500, 300), result);
    }

    @Test
    void testAjouterProduit() {
        productController.ajouterProduit("test", 50, 1000);
    }

    @Test
    void testSupprimerProduit() {
        productController.supprimerProduit(0);
    }

    @Test
    void testUpdateProduit() {
        productController.updateProduit(0, 0, "valeur");
    }

    @Test
    void testCalculerMargeProduit() {
        int result = productController.calculerMargeProduit(0);
        Assertions.assertEquals(120, result);
    }

    @Test
    void testTrierProduitsParOrdreAlphabetique() {
        List<Product> result = productController.trierProduitsParOrdreAlphabetique();
        Assertions.assertEquals(Arrays.<Product>asList(new Product(0, "nom", 0, 0)), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme