package cn.coolink.controller.bu;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.bu.BuWarehouseDetail;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.bu.WarehouseDetailService;
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
@RequestMapping("bu/warehouseDetail")
public class WarehouseDetailController {

    @Autowired
    private WarehouseDetailService warehouseDetailService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<BuWarehouseDetail> getById(Long id) {
        return ResultBean.querySuccess(warehouseDetailService.getById(id));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<BuWarehouseDetail> getList4Page(BuWarehouseDetail bean, Page page) {
        return warehouseDetailService.getList4Page(bean, page);
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<BuWarehouseDetail>> getList(BuWarehouseDetail bean) {
        return ResultBean.querySuccess(warehouseDetailService.getList(bean));
    }

    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseDetailList");
        return modelAndView;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(warehouseDetailService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(warehouseDetailService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(BuWarehouseDetail bean) {
        return ResultBean.saveSuccess(warehouseDetailService.save(bean));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseDetailAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseDetailEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseDetailForm");
        return modelAndView;
    }

}
