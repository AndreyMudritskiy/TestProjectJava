package com.testAPI.Controllers;

import com.testAPI.DAL.GoogleSheets.DbLayer;
import com.testAPI.DAL.IDbLayer;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    // не ясно как прикрутить что то типа Ninject, Autowired не заводится.. пока прокинем так
    public IDbLayer dblayer = new DbLayer();

}
