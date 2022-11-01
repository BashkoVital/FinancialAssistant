package com.bashko.itmo.services;

import com.bashko.itmo.entities.Product;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.ExpensesCategoryRepository;
import com.bashko.itmo.repositories.ProductRepository;
import com.bashko.itmo.services.interfaceService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceJpaImpl implements ProductService {

    private ProductRepository productRepository;
    private ExpensesCategoryRepository expensesCategoryRepository;

    @Autowired
    public ProductServiceJpaImpl(ProductRepository productRepository, ExpensesCategoryRepository expensesCategoryRepository) {
        this.productRepository = productRepository;
        this.expensesCategoryRepository = expensesCategoryRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllByUserId(Long id) {
        return productRepository.findAllByExpensesCategoryFinAssistUserId(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void save(Product product) {
        Product productTemp = new Product();
        productTemp.setTitleProduct(product.getTitleProduct());
        productTemp.setCostProduct(product.getCostProduct());
        productTemp.setDateProduct(new Date());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        productTemp.setExpensesCategory(expensesCategoryRepository.getExpensesCategoryByTitleExpCatAndFinAssistUserId("Product", user.getId()));
        productRepository.save(productTemp);
    }

    @Override
    public double getSumCostProductByUserId(Long id) {
        double count = 0;
        for (Product product : productRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            count += product.getCostProduct();
        }
        return count;
    }
}
