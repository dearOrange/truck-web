package cn.coolink.controller.sys;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.TreeDTO;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.sys.SysArea;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.sys.AreaService;
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
 * @Package: cn.coolink.controller.sys
 * @Description:
 * @author: zfk
 * @date 2018/8/8 10:34
 */
@Controller
@RequestMapping("area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<SysArea> getById(Long id) {
        return ResultBean.querySuccess(areaService.getById(id));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<SysArea> getList4Page(SysArea area, Page page) {
        return areaService.getList4Page(area, page);
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<SysArea>> getList(SysArea area) {
        return ResultBean.querySuccess(areaService.getList(area));
    }


    @RequestMapping("getTreeList")
    @ResponseBody
    public ResultBean<List<SysArea>> getTreeList(String name, Integer level, Long pid) {
        SysArea area = new SysArea();
        area.setName(name);
        area.setLevel(level);
        area.setParentId(pid);
        return ResultBean.querySuccess(areaService.getTreeList(area));
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(areaService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(areaService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(SysArea area) {
        return ResultBean.saveSuccess(areaService.save(area));
    }

    @RequestMapping("areaTree")
    @ResponseBody
    public ResultBean<List<TreeDTO>> areaTree() {
        return ResultBean.querySuccess(areaService.areaTree());
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/areaAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/areaEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/areaForm");
        return modelAndView;
    }


    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/areaList");
        return modelAndView;
    }
}
