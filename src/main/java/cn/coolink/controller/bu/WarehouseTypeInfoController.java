package cn.coolink.controller.bu;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.bu.BuWarehouseTypeInfo;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.bu.WarehouseTypeInfoService;
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
@RequestMapping("bu/warehouse/info")
public class WarehouseTypeInfoController {

    @Autowired
    private WarehouseTypeInfoService warehouseTypeInfoService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<BuWarehouseTypeInfo> getById(Long id) {
        return ResultBean.querySuccess(warehouseTypeInfoService.getExtById(id));
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<BuWarehouseTypeInfo>> getList(BuWarehouseTypeInfo bean) {
        return ResultBean.querySuccess(warehouseTypeInfoService.getList(bean));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<BuWarehouseTypeInfo> getList4Page(BuWarehouseTypeInfo bean, Page page) {
        return warehouseTypeInfoService.getList4Page(bean, page);
    }

    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseTypeInfoList");
        return modelAndView;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(warehouseTypeInfoService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(warehouseTypeInfoService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(BuWarehouseTypeInfo bean) {
        return ResultBean.saveSuccess(warehouseTypeInfoService.save(bean));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseTypeInfoAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseTypeInfoEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseTypeInfoForm");
        return modelAndView;
    }

}
