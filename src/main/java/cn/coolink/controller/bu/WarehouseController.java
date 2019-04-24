package cn.coolink.controller.bu;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.bu.BuWarehouse;
import cn.coolink.entity.sys.SysOrg;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.bu.WarehouseService;
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
@RequestMapping("bu/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<BuWarehouse> getById(Long id) {
        return ResultBean.querySuccess(warehouseService.getExtById(id));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<BuWarehouse> getList4Page(BuWarehouse warehouse, Page page) {
        return warehouseService.getList4Page(warehouse, page);
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<BuWarehouse>> getList(BuWarehouse bean) {
        return ResultBean.querySuccess(warehouseService.getList(bean));
    }

    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseList");
        return modelAndView;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(warehouseService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(warehouseService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(BuWarehouse warehouse) {
        return ResultBean.saveSuccess(warehouseService.save(warehouse));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseForm");
        return modelAndView;
    }

}
