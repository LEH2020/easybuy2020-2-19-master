package com.buy.service.product;

import com.buy.entity.EasybuyProductCategory;

import java.util.List;

public interface IProductCategoryService {
    //获取商品的一级分类
    List<EasybuyProductCategory> queryAllPorductCategory(String parentId);
}
