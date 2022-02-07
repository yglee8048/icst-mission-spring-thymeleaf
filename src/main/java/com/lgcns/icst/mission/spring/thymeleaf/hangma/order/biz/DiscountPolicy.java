package com.lgcns.icst.mission.spring.thymeleaf.hangma.order.biz;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.member.entity.EmpRank;

public interface DiscountPolicy {

    int getDiscountPrice(EmpRank empRank, int price);
}
