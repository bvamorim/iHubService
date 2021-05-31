package com.ihub.api.databuilder;

import java.util.Calendar;

import com.ihub.api.model.domainobject.Product;

/**
 * <h1>ProductDataBuilder</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
public class ProductDataBuilder {


    public static Product getTV(){

        Product product = new Product();
        product.setName("TV");
        product.setPrice(500d);
        product.setSku(String.valueOf(Calendar.getInstance().getTimeInMillis()));

        return product;
    }
}
