package cn.coolink.controller.bu;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.bu.BuReleaseLine;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.bu.ReleaseLineService;
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
@RequestMapping("bu/releaseLine")
public class ReleaseLineController {

    @Autowired
    private ReleaseLineService releaseLineService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<BuReleaseLine> getById(Long id) {
        return ResultBean.querySuccess(releaseLineService.getExtById(id));
    }

    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<BuReleaseLine> getList4Page(BuReleaseLine bean, Page page) {
        return releaseLineService.getList4Page(bean, page);
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<BuReleaseLine>> getList(BuReleaseLine bean) {
        return ResultBean.querySuccess(releaseLineService.getList(bean));
    }

    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/releaseLineList");
        return modelAndView;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(releaseLineService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(releaseLineService.dels(ids));
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(BuReleaseLine bean) {
        return ResultBean.saveSuccess(releaseLineService.save(bean));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/releaseLineAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/releaseLineEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bu/releaseLineForm");
        return modelAndView;
    }

}
