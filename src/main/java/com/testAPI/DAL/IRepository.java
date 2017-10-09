package com.testAPI.DAL;

public interface IRepository<T>
{
    T Get(String ID);
    void Set(T model);
    void Updata(T model);
}
