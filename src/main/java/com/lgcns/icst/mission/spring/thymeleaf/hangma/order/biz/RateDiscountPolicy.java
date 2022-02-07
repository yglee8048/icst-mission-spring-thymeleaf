package com.lgcns.icst.mission.spring.thymeleaf.hangma.order.biz;

import com.lgcns.icst.mission.spring.thymeleaf.hangma.member.entity.EmpRank;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@Qualifier("rate-discount")
public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public int getDiscountPrice(EmpRank empRank, int price) {
        if (empRank.equals(EmpRank.VIP)) {
            return (int) (price * 0.1);
        }
        return 0;
    }
}
