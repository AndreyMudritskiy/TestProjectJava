package com.testAPI.DAL;

import com.testAPI.Model.Food;

public interface IDbLayer {

    IRepository<Food> FoodsRepository();
}
