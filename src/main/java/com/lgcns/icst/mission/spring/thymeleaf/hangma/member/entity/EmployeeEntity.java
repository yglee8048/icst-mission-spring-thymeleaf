package com.lgcns.icst.mission.spring.thymeleaf.hangma.member.entity;

public class EmployeeEntity {

    private Integer empNo;
    private String empNm;
    private EmpRank empRank;

    public EmployeeEntity(Integer empNo, String empNm, String empRank) {
        this.empNo = empNo;
        this.empNm = empNm;
        this.empRank = EmpRank.valueOf(empRank);
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public String getEmpNm() {
        return empNm;
    }

    public EmpRank getEmpRank() {
        return empRank;
    }
}
