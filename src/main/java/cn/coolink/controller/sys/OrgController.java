package cn.coolink.controller.sys;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.sys.SysMember;
import cn.coolink.entity.sys.SysOrg;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.sys.OrgService;
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
@RequestMapping("org")
public class OrgController {

    @Autowired
    private OrgService orgService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<SysOrg> getById(Long id){
        return ResultBean.querySuccess(orgService.getById(id));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<SysOrg> getList4Page(SysOrg org, Page page) {
        return orgService.getList4Page(org,page);
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<SysOrg>> getList(SysOrg org) {
        return ResultBean.querySuccess(orgService.getList(org));
    }

    @EscapeLogin
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView toInfo(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/orgList");
        return modelAndView;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id){
        return  ResultBean.delSuccess(orgService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids){
        return  ResultBean.delSuccess(orgService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(SysOrg org){
        return ResultBean.saveSuccess(orgService.save(org));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/orgAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/orgEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/orgForm");
        return modelAndView;
    }

}
