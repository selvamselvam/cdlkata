package uk.co.cdl.checkout.service;

import org.springframework.stereotype.Service;
import uk.co.cdl.checkout.entity.Stock;

import java.util.List;

@Service
public interface StockService {
    public List<Stock> getProductStocks();
    public void addProductStock(Stock stock);
    public Stock getProductStock(String productName);
}
