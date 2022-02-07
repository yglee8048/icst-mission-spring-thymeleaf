package com.lgcns.icst.mission.spring.thymeleaf.hangma.order.controller;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.common.constant.SessionKey;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.member.biz.MemberBiz;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.member.entity.EmployeeEntity;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.order.biz.OrderBiz;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.order.entity.OrderEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderBiz orderBiz;
    private final MemberBiz memberBiz;

    public OrderController(OrderBiz orderBiz, MemberBiz memberBiz) {
        this.orderBiz = orderBiz;
        this.memberBiz = memberBiz;
    }

    @GetMapping("/list")
    public String orderListPage(@SessionAttribute(value = SessionKey.EMP_NO, required = false) Integer empNo,
                                Model model) {
        try {
            if (empNo == null) {
                throw new Exception("로그인 되지 않은 사용자 입니다.");
            }

            EmployeeEntity employeeEntity = memberBiz.findByEmpNo(empNo);
            model.addAttribute("empNm", employeeEntity.getEmpNm());

            List<OrderEntity> orders = orderBiz.findAllOrdersByEmpNoOrderByIdDesc(empNo);
            model.addAttribute("orders", orders);

            return "order/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "common/error";
        }
    }

    @PostMapping("/insert")
    public String orderInsertAction(@SessionAttribute(value = SessionKey.EMP_NO, required = false) Integer empNo,
                                    @RequestParam Integer price,
                                    Model model) {
        try {
            if (empNo == null) {
                throw new Exception("로그인 되지 않은 사용자 입니다.");
            }

            orderBiz.insertOrder(empNo, price);
            return "redirect:/order/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "common/error";
        }
    }
}
