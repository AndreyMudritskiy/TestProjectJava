package com.testAPI.Controllers;

import com.testAPI.Model.Food;
import com.testAPI.Services.FoodService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Foods")
public class FoodsController extends BaseController
{

    //@Autowired
    private FoodService service = new FoodService(dblayer);    // Autowired не заводится

    @RequestMapping(
            value = "/Ping",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String Ping()
    {
        return "OK";
    }

    @RequestMapping(
            value = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Food get(String ID)
    {
        return service.Get(ID);
    }

    @RequestMapping(
            value = "/post",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void post(Food model)
    {
        service.Update(model);
    }

    @RequestMapping(
            value = "/put",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void put(Food model)
    {
        service.Insert(model);
    }


}
