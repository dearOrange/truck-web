package cn.coolink.controller.sys;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.sys.SysRole;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.sys.RoleService;
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
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<SysRole> getById(Long id) {
        return ResultBean.querySuccess(roleService.getById(id));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<SysRole> getList4Page(SysRole role, Page page) {
        return roleService.getList4Page(role, page);
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<SysRole>> getList(SysRole role) {
        return ResultBean.querySuccess(roleService.getList(role));
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return  ResultBean.delSuccess(roleService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return  ResultBean.delSuccess(roleService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(SysRole role) {
        return ResultBean.saveSuccess(roleService.save(role));
    }

    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/roleList");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/roleAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/roleEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/roleForm");
        return modelAndView;
    }
}
