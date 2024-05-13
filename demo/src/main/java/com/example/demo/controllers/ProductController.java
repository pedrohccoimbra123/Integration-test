package com.example.demo.controllers;

import com.example.demo.domain.ProductRepository;
import com.example.demo.domain.Products;
import com.example.demo.domain.RequestProduct;
import com.example.demo.domain.RequestProductUpdate;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    //Retorna todos os produtos presentes no banco de dados
    @GetMapping(value = "allProducts")
    public ResponseEntity getAllProducts(){
        //Method to get all the date in Products table.
        var allProducts = productRepository.findAll();
        return ResponseEntity.ok(allProducts);
    }


    //Produto com o Maior preço, deve retornar o Asymmetrical Neck Ruffle Trim Split Thigh Sequin Dress com 47.99 de preço
    @GetMapping("/products/highestprice")
    public ResponseEntity<Products> getProductWithHighestPrice() {

        Products product = productRepository.findProductWithHighestPrice();

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Ordena pelo produto que mais tem em estoque
    @GetMapping("/HighstUnitInStock")
    public ResponseEntity<List<Products>> getProductsSortedByUnitsold() {
        List<Products> sortedProducts = productRepository.findByOrderByHighestProductInStock();

        if (sortedProducts.isEmpty()) {
            throw new EntityNotFoundException("Nenhum produto encontrado");
        } else {
            return ResponseEntity.ok(sortedProducts);
        }

    }

    /*@GetMapping("/HighestUnitInStock")
    public ResponseEntity<Products> getProductWithHighestStock() {
        // Obtém o produto com o maior número em estoque
        List<Products> products = productRepository.findHighestProductInStock(PageRequest.of(0, 1));

        // Verifica se a lista não está vazia
        if (!products.isEmpty()) {
            // Obtém o primeiro produto da lista (o que tem o maior número em estoque)
            Products product = products.get(0);
            // Retorna o produto em uma resposta HTTP
            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException("Nenhum produto encontrado");
        }
    }*/

    //Criar um novo produto no estoque.
    @PostMapping(value = "addProduct")
    public ResponseEntity<String> addProduct(@RequestBody @Valid RequestProduct data) {

        Products newProduct = new Products(data);
        productRepository.save(newProduct);
        String message = "Produto adicionado com sucesso!!!";
        return ResponseEntity.ok(message);

    }


    //Delete pelo ID
    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Produto com o ID " + id + " foi excluído com sucesso.");
        } else {
            throw new EntityNotFoundException("Produto com o ID " + id + " não encontrado");
        }

    }

    //update products
    @PutMapping(value = "updateProduct")
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProductUpdate data){
        Optional<Products> optionalProduct = productRepository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Products product = optionalProduct.get();
            product.setProduct_name(data.product_name());
            product.setPrice(data.price());
            product.setStock(data.stock());

            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }



}
