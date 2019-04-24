package cn.coolink.controller.sys;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.sys.SysDict;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.sys.DictService;
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
@RequestMapping("dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<SysDict> getById(SysDict dict){
        return ResultBean.querySuccess(dictService.getById(dict));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<SysDict> getList4Page(SysDict dict, Page page) {
        return dictService.getList4Page(dict,page);
    }

    @RequestMapping(value ="getListByType",method = RequestMethod.GET)
    @ResponseBody
    public ResultBean<List<SysDict>> getListByType(String type) {
        return ResultBean.querySuccess(dictService.getListByTypeForCache(type));
    }

    @EscapeLogin
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView toInfo(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/dictList");
        return modelAndView;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(SysDict dict){
        return ResultBean.delSuccess(dictService.del(dict));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(List<SysDict> dict){
        return ResultBean.delSuccess(dictService.dels(dict));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(SysDict dict){
        return ResultBean.saveSuccess(dictService.save(dict));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/dictAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/dictEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/dictForm");
        return modelAndView;
    }
}
