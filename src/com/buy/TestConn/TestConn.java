package com.buy.TestConn;

import com.buy.utils.DataSourceUtil;

public class TestConn {
    public static void main(String[] args) {
        DataSourceUtil dataSourceUtil=new DataSourceUtil();
        dataSourceUtil.getConn();
    }
}
