package com.testAPI.DAL.GoogleSheets;
import com.testAPI.DAL.GoogleSheets.Repository.FoodsRepository;
import com.testAPI.DAL.IDbLayer;
import com.testAPI.DAL.IRepository;
import com.testAPI.Model.Food;

public class DbLayer implements IDbLayer {

    private IRepository<Food> _foodRepository;

    @Override
    public IRepository<Food> FoodsRepository() {
        if(_foodRepository == null)
            _foodRepository = new FoodsRepository();

        return _foodRepository;
    }
}
