package cn.coolink.controller.bu;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.bu.BuCarDriver;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.bu.CarDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Title: truck-platform
 * @Package: cn.coolink.controller.bu
 * @Description:
 * @author: xhj
 * @date 2018/9/27 19:05
 */
@Controller
@RequestMapping("bu/carDriver")
public class CarDriverController {

    @Autowired
    private CarDriverService carDriverService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<BuCarDriver> getById(Long id) {
        return ResultBean.querySuccess(carDriverService.getExtById(id));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<BuCarDriver> getList4Page(BuCarDriver bean, Page page) {
        return carDriverService.getList4Page(bean, page);
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<BuCarDriver>> getList(BuCarDriver bean) {
        return ResultBean.querySuccess(carDriverService.getList(bean));
    }


    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/carDriverList");
        return modelAndView;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(carDriverService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(carDriverService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(BuCarDriver bean) {
        return ResultBean.saveSuccess(carDriverService.save(bean));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/carDriverAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/carDriverEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/carDriverForm");
        return modelAndView;
    }

}
