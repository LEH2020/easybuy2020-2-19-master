package com.buy.TestConn;

import com.buy.dao.product.IProductCategory;
import com.buy.dao.product.ProductCategoryImpl;
import com.buy.entity.EasybuyProductCategory;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestProductCategory{
@Test
    public void testProductCategory(){
        IProductCategory productCategory=new ProductCategoryImpl();
        List<EasybuyProductCategory> productCategories=productCategory.queryAllPorductCategory("0");
        for(EasybuyProductCategory category : productCategories)
        {
            System.out.println(category.getName());
        }
    }
}
