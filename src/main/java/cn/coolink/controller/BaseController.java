package cn.coolink.controller;

import cn.coolink.utils.DateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public class BaseController {

    @InitBinder
    private void initBinder(HttpServletRequest request,
                            ServletRequestDataBinder binder) throws Exception{
        binder.registerCustomEditor(Date.class, new DateEditor());
    }
}
