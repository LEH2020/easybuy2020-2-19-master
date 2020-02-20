package com.buy.service.product;

import com.buy.dao.product.IProductCategory;
import com.buy.dao.product.ProductCategoryImpl;
import com.buy.entity.EasybuyProductCategory;

import java.util.List;

public class ProductCategoryServiceImpl implements IProductCategoryService{
        private IProductCategory productCategory=new ProductCategoryImpl();
    @Override
    public List<EasybuyProductCategory> queryAllPorductCategory(String parentId) {
        parentId="0";
        return productCategory.queryAllPorductCategory(parentId);
    }
}
