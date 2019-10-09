package com.icourt.lawyercrawlparse.constants;

import lombok.Data;
import lombok.Getter;

public enum CaseTypeEnum {

//    1=刑事案件   2=民事案件  3=行政案件  4=赔偿案件  5=执行案件;
    T1_(1,"刑事"),
    T2_(2,"民事"),
    T3_(3,"行政"),
    T4_(4,"赔偿"),
    T5_(5,"执行"),;

    @Getter
    private int type;

    @Getter
    private String desc;

    CaseTypeEnum(int type,String desc){
        this.type =type;
        this.desc=desc;
    }


}
