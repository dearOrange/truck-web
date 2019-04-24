package cn.coolink.controller.sys;

import cn.coolink.common.annotations.EscapeLogin;
import cn.coolink.dto.MutiSelectDTO;
import cn.coolink.dto.result.PageResultBean;
import cn.coolink.dto.result.ResultBean;
import cn.coolink.entity.sys.SysMember;
import cn.coolink.framework.dao.Page;
import cn.coolink.service.sys.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: truck-platform
 * @Package: cn.coolink.controller.sys
 * @Description:
 * @author: zfk
 * @date 2018/8/8 10:34
 */
@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("getById")
    @ResponseBody
    public ResultBean<SysMember> getById(Long id) {
        return ResultBean.querySuccess(memberService.getById(id));
    }

    @RequestMapping("getList")
    @ResponseBody
    public ResultBean<List<SysMember>> getList(SysMember member) {
        return ResultBean.querySuccess(memberService.getList(member));
    }

    @RequestMapping("getMutiSelect")
    @ResponseBody
    public ResultBean<List<MutiSelectDTO>> getMutiSelect(Long orgId) {
        List<MutiSelectDTO> list = new ArrayList<>();
        List<SysMember> org = null;
        MutiSelectDTO dto = null;
        if (null != orgId) {
            org = memberService.getListByOrg(orgId);
        }
        List<SysMember> listBean = memberService.getList(new SysMember());

        for (SysMember m : listBean) {
            dto = new MutiSelectDTO();
            parseMemberToSelectDTO(m, dto, org);
            list.add(dto);
        }
        return ResultBean.querySuccess(list);
    }

    private void parseMemberToSelectDTO(SysMember m, MutiSelectDTO dto, List<SysMember> org) {
        dto.setValue(m.getId());
        dto.setName(m.getName() + " [" + m.getMobile() + "]");
        if (null != org && org.size() > 0) {
            for (SysMember sm : org) {
                if (m.getId().doubleValue() == sm.getId().doubleValue()) {
                    dto.setSelected("selected");
                    break;
                }
            }
        }
    }


    @RequestMapping("del")
    @ResponseBody
    public ResultBean<Integer> del(Long id) {
        return ResultBean.delSuccess(memberService.del(id));
    }

    @RequestMapping("dels")
    @ResponseBody
    public ResultBean<Integer> dels(String ids) {
        return ResultBean.delSuccess(memberService.dels(ids));
    }


    @RequestMapping("getList4Page")
    @ResponseBody
    public PageResultBean<SysMember> getList4Page(SysMember member, Page page) {
        return memberService.getList4Page(member, page);
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultBean<Integer> save(SysMember member) {
        return ResultBean.saveSuccess(memberService.save(member));
    }

    @EscapeLogin
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/memberAdd");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("edit")
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/memberEdit");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping("toForm")
    public ModelAndView toForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/memberForm");
        return modelAndView;
    }

    @EscapeLogin
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView toInfo(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sys/memberList");
        return modelAndView;
    }
}
