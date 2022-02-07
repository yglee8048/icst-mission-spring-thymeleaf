package com.lgcns.icst.mission.spring.thymeleaf.hangma.order.biz;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.member.entity.EmpRank;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixed-discount")
public class FixedDiscountPolicy implements DiscountPolicy {

    @Override
    public int getDiscountPrice(EmpRank empRank, int price) {
        if (empRank.equals(EmpRank.VIP) && price > 1000) {
            return 1000;
        }
        return 0;
    }
}
