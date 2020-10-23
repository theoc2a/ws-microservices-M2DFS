package com.ecommerce.circuitbreaker.circuitbreaker.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductControllerCB {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/Produits", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod2")
    public String getProducts()
    {
        System.out.println("Getting products details");

        String response = restTemplate.exchange("http://localhost:9090/Produits",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        System.out.println("Response Body " + response);

        return "Products Details " + response;
    }

    @RequestMapping(value = "/afficherUnProduit/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getProductById(@PathVariable int id)
    {
        System.out.println("Getting product details for " + id);

        String response = restTemplate.exchange("http://localhost:9090/afficherUnProduit/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, id).getBody();

        System.out.println("Response Body " + response);

        return "Product Id -  " + id + " [ Product Details " + response+" ]";
    }

    @RequestMapping(value = "/margProduit/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getProductsAndMarge(@PathVariable int id)
    {
        System.out.println("Getting product details");

        String response = restTemplate.exchange("http://localhost:9090/margProduit",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        System.out.println("Response Body " + response);

        return " Products Details " + response;
    }

    @RequestMapping(value = "/trieProduit", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod2")
    public String getProductsSorted()
    {
        System.out.println("Getting products details");

        String response = restTemplate.exchange("http://localhost:9090/trieProduit  ",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        System.out.println("Response Body " + response);

        return "Products Details " + response;
    }

    public String  fallbackMethod(int id){

        return "Fallback response:: No product details available temporarily";
    }

    public String  fallbackMethod2(){

        return "Fallback response:: No products details available temporarily";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
