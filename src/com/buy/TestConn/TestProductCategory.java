package com.buy.TestConn;

import com.buy.dao.product.IProductCategory;
import com.buy.dao.product.ProductCategoryImpl;
import com.buy.entity.EasybuyProductCategory;
import com.buy.service.product.IProductCategoryService;
import com.buy.service.product.ProductCategoryServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestProductCategory{
@Test
    public void testProductCategory(){
    IProductCategoryService service=new ProductCategoryServiceImpl();
        List<EasybuyProductCategory> productCategories=service.queryAllPorductCategory("0");
        for(EasybuyProductCategory category : productCategories)
        {
            System.out.println(category.getName());
        }
    }
}
