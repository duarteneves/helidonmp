package pt.dneves.cloud.helidon.mp.manager;

import javax.enterprise.context.ApplicationScoped;

import pt.dneves.cloud.helidon.mp.model.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class ProductManager {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    private AtomicInteger productIdGenerator = new AtomicInteger(0);

    private ConcurrentMap<String, Product> inMemoryStore = new ConcurrentHashMap<>();

    
    public ProductManager() {
    	
        Product product = new Product();
        
        product.setId(getNextId());
        product.setName("product " + product.getId());
        
        inMemoryStore.put(product.getId(), product);

        
        product = new Product();
        
        product.setId(getNextId());
        product.setName("product " + product.getId());
        
        inMemoryStore.put(product.getId(), product);
        
    }

    private String getNextId() {
        String date = LocalDate.now().format(formatter);
        return String.format("%04d-%s", productIdGenerator.incrementAndGet(), date);
    }

    public Product get(String id) {
        return inMemoryStore.get(id);
    }

    public List<Product> getAll() {
        return new ArrayList<>(inMemoryStore.values());
    }
    
}
