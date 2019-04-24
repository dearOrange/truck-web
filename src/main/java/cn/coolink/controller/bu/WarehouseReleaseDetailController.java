package cn.coolink.controller.bu;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.bu.BuWarehouseReleaseDetail;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.bu.WarehouseReleaseDetailService;
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
@RequestMapping("bu/warehouseRelease/info")
public class WarehouseReleaseDetailController {

    @Autowired
    private WarehouseReleaseDetailService warehouseReleaseDetailService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<BuWarehouseReleaseDetail> getById(Long id) {
        return ResultBean.querySuccess(warehouseReleaseDetailService.getExtById(id));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<BuWarehouseReleaseDetail> getList4Page(BuWarehouseReleaseDetail bean, Page page) {
        return warehouseReleaseDetailService.getList4Page(bean, page);
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<BuWarehouseReleaseDetail>> getList(BuWarehouseReleaseDetail bean) {
        return ResultBean.querySuccess(warehouseReleaseDetailService.getList(bean));
    }


    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseReleaseDetailList");
        return modelAndView;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(warehouseReleaseDetailService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(warehouseReleaseDetailService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(BuWarehouseReleaseDetail bean) {
        return ResultBean.saveSuccess(warehouseReleaseDetailService.save(bean));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseReleaseDetailAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseReleaseDetailEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/warehouseReleaseDetailForm");
        return modelAndView;
    }

}
