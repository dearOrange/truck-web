package cn.coolink.controller.bu;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.controller.BaseController;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.bu.BuWarehouseRelease;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.bu.WarehouseReleaseService;
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
@RequestMapping("bu/warehouseRelease")
public class WarehouseReleaseController extends BaseController {

    @Autowired
    private WarehouseReleaseService warehouseReleaseService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<BuWarehouseRelease> getById(Long id) {
        return ResultBean.querySuccess(warehouseReleaseService.getExtById(id));
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<BuWarehouseRelease>> getList(BuWarehouseRelease bean) {
        return ResultBean.querySuccess(warehouseReleaseService.getList(bean));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<BuWarehouseRelease> getList4Page(BuWarehouseRelease bean, Page page) {
        return warehouseReleaseService.getList4Page(bean, page);
    }

    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseReleaseList");
        return modelAndView;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(warehouseReleaseService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(warehouseReleaseService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(BuWarehouseRelease bean) {
        return ResultBean.saveSuccess(warehouseReleaseService.save(bean));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseReleaseAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseReleaseEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseReleaseForm");
        return modelAndView;
    }

}
