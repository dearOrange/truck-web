package cn.coolink.controller.bu;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.bu.BuCarLine;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.bu.CarLineService;
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
@RequestMapping("bu/carLine")
public class CarLineController {

    @Autowired
    private CarLineService carLineService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<BuCarLine> getById(Long id) {
        return ResultBean.querySuccess(carLineService.getExtById(id));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<BuCarLine> getList4Page(BuCarLine bean, Page page) {
        return carLineService.getList4Page(bean, page);
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<BuCarLine>> getList(BuCarLine bean) {
        return ResultBean.querySuccess(carLineService.getList(bean));
    }

    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/carLineList");
        return modelAndView;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(carLineService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(carLineService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(BuCarLine bean) {
        return ResultBean.saveSuccess(carLineService.save(bean));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/carLineAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/carLineEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/carLineForm");
        return modelAndView;
    }

}
