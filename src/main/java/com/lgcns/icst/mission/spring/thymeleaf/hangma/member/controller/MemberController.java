package com.lgcns.icst.mission.spring.thymeleaf.hangma.member.controller;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.common.constant.SessionKey;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.member.biz.MemberBiz;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.member.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberBiz memberBiz;

    @Autowired
    public MemberController(MemberBiz memberBiz) {
        this.memberBiz = memberBiz;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "member/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAction(@RequestParam Integer empNo, HttpSession session, Model model,
                              @RequestParam(required = false, defaultValue = "/menu/list") String redirectURI) {
        try {
            EmployeeEntity employeeEntity = memberBiz.findByEmpNo(empNo);
            session.setAttribute(SessionKey.EMP_NO, employeeEntity.getEmpNo());

            return "redirect:" + redirectURI;
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "common/error";
        }
    }

    @GetMapping("/logout")
    public String logoutAction(HttpSession session) {
        session.invalidate();
        return "redirect:/member/login";
    }

    @GetMapping("/update")
    public String updateForm(@SessionAttribute(value = SessionKey.EMP_NO, required = false) Integer empNo,
                             Model model) {
        try {
            if (empNo == null) {
                throw new Exception("로그인 되지 않은 사용자 입니다.");
            }
            EmployeeEntity employeeEntity = memberBiz.findByEmpNo(empNo);

            model.addAttribute("member", employeeEntity);
            return "member/update";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "common/error";
        }
    }

    @PostMapping("/update")
    public String updateAction(@SessionAttribute(value = SessionKey.EMP_NO, required = false) Integer empNo,
                               @RequestParam String empNm, Model model) {
        try {
            if (empNo == null) {
                throw new Exception("로그인 되지 않은 사용자 입니다.");
            }
            memberBiz.updateEmployee(empNo, empNm);

            return "redirect:/menu/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "common/error";
        }
    }

    @GetMapping("/sign-up")
    public String signUpForm() {
        return "member/signUp";
    }

    @PostMapping("/sign-up")
    public String signUpAction(@RequestParam Integer empNo, @RequestParam String empNm, Model model) {
        try {
            memberBiz.signUp(empNo, empNm);

            return "redirect:/member/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "common/error";
        }
    }

    @GetMapping("/withdraw")
    public String withDrawAction(HttpSession session, Model model) {
        try {
            Integer empNo = (Integer) session.getAttribute(SessionKey.EMP_NO);
            if (empNo == null) {
                throw new Exception("로그인 되지 않은 사용자 입니다.");
            }

            memberBiz.withdraw(empNo);
            session.invalidate();

            return "redirect:/member/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "common/error";
        }
    }
}
