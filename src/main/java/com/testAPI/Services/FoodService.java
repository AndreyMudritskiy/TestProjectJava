package com.testAPI.Services;

import com.testAPI.DAL.IDbLayer;
import com.testAPI.Model.Food;

public class FoodService
{
    private IDbLayer dbLayer;

    public FoodService(IDbLayer dbLayer)
    {
        this.dbLayer = dbLayer;
    }

    public Food Get(String ID)
    {
        return dbLayer.FoodsRepository().Get(ID);
    }

    public void Insert(Food model)
    {
        dbLayer.FoodsRepository().Set(model);
    }

    public void Update(Food model)
    {
        dbLayer.FoodsRepository().Updata(model);
    }
}
