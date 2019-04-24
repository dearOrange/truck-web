package cn.coolink.controller;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.LoginDTO;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.service.sys.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @Title: truck-platform
 * @Package: cn.coolink.controller
 * @Description:
 * @author: zfk
 * @date 2018/8/8 10:34
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "doLogin")
    @ResponseBody
    public ResultBean<LoginDTO> doLogin(LoginDTO login, HttpServletResponse response){
        ResultBean<LoginDTO> r =  loginService.login(login);
        if(r.getCode()==200){
            return ResultBean.querySuccess(r.getData());
        }
        return ResultBean.buildError(r.getMsg(),r.getMsg(),null);
    }

    @RequestMapping(value="doLogout",method= RequestMethod.POST)
    @ResponseBody
    public ResultBean<Boolean> userLogout(String token, HttpServletResponse response) {
        return loginService.loginOut(token);
    }

    @EscapeLogin
    @RequestMapping("index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("welcome")
    public ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("")
    public ModelAndView login(){
        //判断是还登录过。
        //return index();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/toerror")
    public ModelAndView toerror(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }

//    @RequestMapping("exception")
//    @ResponseBody
//    public ResultBean getException() throws Exception{
//        throw new BusinessException("自定义");
//    }

    @RequestMapping(value = "ex403",method = RequestMethod.GET)
    public ModelAndView ex403(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("403");
        return modelAndView;
    }

    @RequestMapping(value = "ex404",method = RequestMethod.GET)
    public ModelAndView ex404(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404");
        return modelAndView;
    }

    @RequestMapping(value = "ex500",method = RequestMethod.GET)
    public ModelAndView ex500(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/**/500");
        return modelAndView;
    }
}
