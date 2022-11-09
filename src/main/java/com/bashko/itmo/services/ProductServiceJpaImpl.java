package com.bashko.itmo.services;

import com.bashko.itmo.entities.Product;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.ExpensesCategoryRepository;
import com.bashko.itmo.repositories.ProductRepository;
import com.bashko.itmo.services.interfaceService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
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

    @Override
    public List<Product> findAllByDateMonthAndUserId(Long id) {
        Calendar calendar = Calendar.getInstance();
        List<Product> productList = new LinkedList<>();
        for (Product product : productRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            if (product.getDateProduct().getMonth() == calendar.get(Calendar.MONTH)) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public double getSumCostProductByDateMonthAndUserId(Long id) {
        double count = 0;
        Calendar calendar = Calendar.getInstance();
        for (Product product : productRepository.findAllByExpensesCategoryFinAssistUserId(id)) {
            if (product.getDateProduct().getMonth() == calendar.get(Calendar.MONTH)) {
                count += product.getCostProduct();
            }
        }
        return count;
    }
}
