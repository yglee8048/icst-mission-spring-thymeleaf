package com.lgcns.icst.mission.spring.thymeleaf.hangma.menu.entity;

public class MenuEntity {

    private Long menuId;
    private Category category;
    private String menuNm;
    private Integer price;
    private String imgFileNm;

    public MenuEntity(Long menuId, String category, String menuNm, Integer price, String imgFileNm) {
        this.menuId = menuId;
        this.category = Category.valueOf(category);
        this.menuNm = menuNm;
        this.price = price;
        this.imgFileNm = imgFileNm;
    }

    public Long getMenuId() {
        return menuId;
    }

    public Category getCategory() {
        return category;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImgFileNm() {
        return imgFileNm;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}