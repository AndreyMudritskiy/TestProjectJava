package com.testAPI.Model;

import com.google.api.services.sheets.v4.model.ValueRange;

import java.util.ArrayList;
import java.util.List;

public class Food
{
    public String ID;
    public String Name;
    public String Proteins;
    public String Fats;

    public Food(String id, List<Object> row)
    {
        this.ID = id;
        this.Name = (String) row.get(0);
        this.Proteins = (String) row.get(1);
        this.Fats = (String) row.get(2);
    }

    public  ArrayList<List<Object>> toObjectList()
    {
        List<Object>  DataList = new ArrayList<>();
        DataList.add(Name);
        DataList.add(Proteins);
        DataList.add(Fats);

        ArrayList<List<Object>> list= new ArrayList<>();
        list.add(DataList);

        return list;
    }

}
