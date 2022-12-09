package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.ProductDTO;
import com.cydeo.lab08rest.entity.Category;
import com.cydeo.lab08rest.entity.Product;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.ProductRepository;
import com.cydeo.lab08rest.service.CategoryService;
import com.cydeo.lab08rest.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceIMpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;

    public ProductServiceIMpl(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList=productRepository.findAll();
        return productList.stream()
                .map(product->mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product =productRepository.save(mapperUtil.convert(productDTO, new Product()));
        return mapperUtil.convert(product, new ProductDTO());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product =productRepository.save(mapperUtil.convert(productDTO, new Product()));
        return mapperUtil.convert(product, new ProductDTO());
    }

    @Override
    public List<ProductDTO> retrieveProductByCategoryAndPrice(List<Long> categoryList, BigDecimal price) {
        return productRepository.retrieveProductListByCategory(categoryList, price).stream()
                .map(product->mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO retrieveByName(String name) {
        return mapperUtil.convert(productRepository.findFirstByName(name), new ProductDTO());
    }

    @Override
    public List<ProductDTO> retrieveProductByTop3ProductByPrice() {
        return productRepository.findTop3ByOrderByPriceDesc().stream()
                .map(product->mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public Integer countProductByPrice(BigDecimal price) {
        return productRepository.countProductByPriceGreaterThan(price);
    }

    @Override
    public List<ProductDTO> retrieveProductByPriceAndQuantity(BigDecimal price, Integer quantity) {
        return productRepository.retrieveProductListGreaterThanPriceAndLowerThanRemainingQuantity(price,
                quantity).stream().map(product -> mapperUtil.convert(product,
                new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> retrieveByCategory(Long categoryId) {
        return productRepository.retrieveProductListByCategory(categoryId)
                .stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO()))
                .collect(Collectors.toList());
    }
}
