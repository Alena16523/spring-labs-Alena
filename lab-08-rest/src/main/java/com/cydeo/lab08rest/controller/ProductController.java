package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.ProductDTO;
import com.cydeo.lab08rest.dto.ProductRequest;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllProducts(){
        List<ProductDTO> productDTOList=productService.getAllProducts();
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrived",productDTOList, HttpStatus.OK ));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(new ResponseWrapper("Product is succsessfully updated",
                productService.updateProduct(productDTO), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(new ResponseWrapper("Product is succsessfully created",
                productService.createProduct(productDTO), HttpStatus.OK));
    }

    @PostMapping("/categoryandprice")
    public ResponseEntity<ResponseWrapper> retrieveProductByCategoryAndPrice(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(new ResponseWrapper("Product is succsessfully retrieved",
                productService.retrieveProductByCategoryAndPrice(productRequest.getCategoryList(), productRequest.getPrice()), HttpStatus.OK));
    }

    @GetMapping({"/{name}"})
    public ResponseEntity<ResponseWrapper> retrieveProductByName(@PathVariable("name") String name){
        return ResponseEntity.ok(new ResponseWrapper("Product was retrieved"
        , productService.retrieveByName(name), HttpStatus.OK));
    }

    @GetMapping("/top3")
    public ResponseEntity<ResponseWrapper> retrieveProductByTop3ProductByPrice(){
        return ResponseEntity.ok(new ResponseWrapper("Top 3 Products were retrieved"
                , productService.retrieveProductByTop3ProductByPrice(), HttpStatus.OK));
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<ResponseWrapper> retrieveProductByPrice(@PathVariable("price") BigDecimal price){
        return ResponseEntity.ok(new ResponseWrapper("Product are successfully retrieved"
                , productService.countProductByPrice(price), HttpStatus.OK));
    }

    @GetMapping("/price/{price}/quantity/{quantity}")
    public ResponseEntity<ResponseWrapper> retrieveProductByPriceAndQuantity(@PathVariable("price")BigDecimal price
            , @PathVariable("quantity") Integer quantity){
        return ResponseEntity.ok(new ResponseWrapper("Product are successfully retrieved"
                , productService.retrieveProductByPriceAndQuantity(price,quantity), HttpStatus.OK));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ResponseWrapper> retrieveByCategory(@PathVariable("id")Long categoryId){
        return ResponseEntity.ok(new ResponseWrapper("Product are successfully retrieved"
                , productService.retrieveByCategory(categoryId), HttpStatus.OK));
    }






}
