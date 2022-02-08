package com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.api;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.biz.MenuBiz;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.entity.Category;
import com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuApi {

    private final MenuBiz menuBiz;

    @Autowired
    public MenuApi(MenuBiz menuBiz) {
        this.menuBiz = menuBiz;
    }

    @GetMapping
    public List<MenuEntity> findAllMenus(@RequestParam(required = false) Category category) throws SQLException {
        List<MenuEntity> menu = menuBiz.findAllMenu();
        if (category != null) {
            List<MenuEntity> filtered = new ArrayList<>();
            for (MenuEntity menuEntity : menu) {
                if (menuEntity.getCategory().equals(category)) {
                    filtered.add(menuEntity);
                }
            }
            return filtered;
        }
        return menu;
    }

    @GetMapping("/{menuId}")
    public MenuEntity findMenuById(@PathVariable Long menuId) throws SQLException {
        return menuBiz.findMenuById(menuId);
    }
}
