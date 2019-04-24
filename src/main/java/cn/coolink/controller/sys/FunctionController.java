package cn.coolink.controller.sys;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.TreeDTO;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.sys.SysFunction;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.sys.FunctionService;
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
@RequestMapping("function")
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<SysFunction> getById(Long id) {
        return ResultBean.querySuccess(functionService.getById(id));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<SysFunction> getList4Page(SysFunction function, Page page) {
        return functionService.getList4Page(function, page);
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<SysFunction>> getList(SysFunction function) {
        return ResultBean.querySuccess(functionService.getList(function,false));
    }

    @RequestMapping("treeDTO")
    @ResponseBody
    public ResultBean<List<TreeDTO>> getDTOTree(String type) {
        return  ResultBean.querySuccess(functionService.functionDTOTree(type));
    }
//    public List<TreeDTO> getDTOTree(String type) {
//        List<TreeDTO> tree = null;
//        ResultBean<List<TreeDTO>> list =  functionService.functionDTOTree(type);
//        if(null!=list && list.getCode()==200){
//            tree = list.getData();
//        }
//        return tree;
//    }

    @RequestMapping("getTreeList")
    @ResponseBody
    public ResultBean<List<SysFunction>> getTreeList(String name, String functionType, Long pid) {
        SysFunction sysFunction = new SysFunction();
        sysFunction.setName(name);
        sysFunction.setFunctionType(functionType);
        sysFunction.setParentId(pid);
        return ResultBean.querySuccess(functionService.getTreeList(sysFunction));
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(functionService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(functionService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(SysFunction function) {
        return ResultBean.saveSuccess(functionService.save(function));
    }

    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/functionList");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/functionAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/functionEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/functionForm");
        return modelAndView;
    }
}
