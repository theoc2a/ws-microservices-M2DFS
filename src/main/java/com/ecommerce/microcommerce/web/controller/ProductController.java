package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.exceptions.ProduitIntrouvableException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;


@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    private static Map<Integer, Product> productList = new HashMap<Integer, Product>() {
        {
            put(1, new Product(1, "Ordinateur portable", 350, 230));
            put(2, new Product(2, "Aspirateur Robot", 500, 300));
            put(3, new Product(3, "Table de Ping Pong", 750, 350));
        }
    };

    //Récupérer la liste des produits
    @GetMapping(value = "/Produits")
    public Map<Integer, Product> listeProduits() {
        return productList;
    }


    //Récupérer un produit par son Id
    @GetMapping(value = "/afficherUnProduit/{id}")
    public Product afficherUnProduit(@PathVariable int id) {
        Product product = productList.get(id);
        return product;
    }

    //ajouter un produit
    @PostMapping(value = "/Produits")
    public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product) {

        Product productAdded =  productDao.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // supprimer un produit
    @GetMapping(value = "/supprimerUnProduit/{id}")
    public void supprimerProduit(@PathVariable int id) {
        productList.remove(id);
    }

    // Mettre à jour un produit
    @GetMapping(value = "/modifUnProduit/{id}/{type}/}{valeur}")
    public void updateProduit(@PathVariable int id, @PathVariable int type, @PathVariable String valeur) {
        Product product = productList.get(id);
        Product productN = new Product();
            if(type == 1) {
                productN = product;
                productN.setNom(valeur);
                productList.entrySet();
            }else if(type == 2) {
                int i = Integer.parseInt(valeur);
                product.setPrix(i);

            }else if(type == 3) {
                int i = Integer.parseInt(valeur);
                product.setPrixAchat(i);

            }
        }


    //Pour les tests
    @GetMapping(value = "test/produits/{prix}")
    public List<Product>  testeDeRequetes(@PathVariable int prix) {
        return productDao.chercherUnProduitCher(400);
    }

        @GetMapping(value = "/margProduit/{id}")
    public int calculerMargeProduit(@PathVariable int id){
        Product product = productList.get(id);
        int pProduct = product.getPrix();
        int paProduct = product.getPrixAchat();
        int marge = pProduct - paProduct;

        return marge;
    }

    @GetMapping(value = "/trieProduit")
    public List<Product> trierProduitsParOrdreAlphabetique(){
        List<Product> p = new ArrayList<Product>(productList.values());
        p.sort(Comparator.comparing(Product::getNom));
        return p;
    }

}
