package uk.co.cdl.checkout.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.co.cdl.checkout.entity.Stock;
import uk.co.cdl.checkout.service.StockService;

import java.util.*;


@Slf4j
@Service
public class StockServiceImpl implements StockService {

    private List<Stock> stockList = new ArrayList<>();

    @Override
    public List<Stock> getProductStocks() {

        if(stockList.isEmpty())
            return Collections.emptyList();

        return stockList;


    }

    @Override
    public void addProductStock(Stock stock) {

        if(Objects.isNull(stock))
            throw new IllegalStateException("Not valid stock");

        stockList.add(stock);
    }

    @Override
    public Stock getProductStock(String productName) {

        if(Objects.isNull(productName))
            throw new IllegalStateException("Not valid product name");

        Stock retStock = null;

        for(Stock stock : stockList){
            if(stock.getProduct().getName().equals(productName))
                retStock = stock;
        }

        return retStock;
    }
}
