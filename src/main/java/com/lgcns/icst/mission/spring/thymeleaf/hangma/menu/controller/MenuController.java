package com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.controller;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.biz.MenuBiz;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuBiz menuBiz;

    @Autowired
    public MenuController(MenuBiz menuBiz) {
        this.menuBiz = menuBiz;
    }

    @GetMapping("/list")
    public ModelAndView listPage() {
        try {
            List<MenuEntity> menu = menuBiz.findAllMenu();

            ModelAndView modelAndView = new ModelAndView("menu/list");
            modelAndView.addObject("menu", menu);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("common/error");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }
    }

    @GetMapping("/{menuId}/update")
    public ModelAndView updateForm(@PathVariable Long menuId) {
        try {
            MenuEntity menuEntity = menuBiz.findMenuById(menuId);

            ModelAndView modelAndView = new ModelAndView("menu/update");
            modelAndView.addObject("menu", menuEntity);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("common/error");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/{menuId}/update")
    public ModelAndView updateAction(@PathVariable Long menuId,
                                     @ModelAttribute MenuEntity menuEntity) {
        try {
            menuEntity.setMenuId(menuId);
            menuBiz.updateMenu(menuEntity);

            return new ModelAndView("redirect:/menu/manage");
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("common/error");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }
    }

    @GetMapping("/{menuId}/delete")
    public ModelAndView deleteAction(@PathVariable Long menuId) {
        try {
            menuBiz.deleteMenuById(menuId);

            return new ModelAndView("redirect:/menu/manage");
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("common/error");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }
    }

    @GetMapping("/insert")
    public ModelAndView insertForm() {
        return new ModelAndView("menu/insert");
    }

    @PostMapping("/insert")
    public ModelAndView insertAction(@ModelAttribute MenuEntity menuEntity) {
        try {
            menuBiz.addMenu(menuEntity.getCategory(), menuEntity.getMenuNm(), menuEntity.getPrice());

            return new ModelAndView("redirect:/menu/manage");
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("common/error");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }
    }

    @GetMapping("/manage")
    public ModelAndView managePage() {
        try {
            List<MenuEntity> menu = menuBiz.findAllMenu();

            ModelAndView modelAndView = new ModelAndView("menu/manage");
            modelAndView.addObject("menu", menu);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("common/error");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }
    }
}
