package com.lgcns.icst.mission.spring.thymeleaf.hangma.order.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class OrderEntity {

    private Long orderId;
    private Integer empNo;
    private Integer price;
    private Integer discountPrice;
    private Date orderDt;

    public OrderEntity(Long orderId, Integer empNo, Integer price, Integer discountPrice, Date orderDt) {
        this.orderId = orderId;
        this.empNo = empNo;
        this.price = price;
        this.discountPrice = discountPrice;
        this.orderDt = orderDt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public String getOrderDt() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(orderDt);
    }
}
