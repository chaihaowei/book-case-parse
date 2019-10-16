package com.icourt.lawyercrawlparse.entity;

public interface JudgementExt {

    // 案件名称
    String caseName = "caseName";

    // 案件类型:1=刑事案件   2=民事案件  3=行政案件  4=赔偿案件  5=执行案件
    String caseType = "caseType";

    // yyyy-mm-dd
    String judgeDate = "judgeDate";

    String thirdId = "thirdId";

    String thirdUrl ="thirdUrl";

    // 审判程序
    String judgeLevel = "judgeLevel";

    // 案号
    String caseNum = "caseNum";

    // 法院名称
    String courtName = "courtName";

    // 相关案例
    String relativeFilesJson = "relativeFilesJson";

    // yyyy-mm-dd 公布时间
    String pubDate = "pubDate";

    // 案由
    String textCause = "textCause";

    String source = "source";

    //是否未公开
    String noPub = "noPub";

    // 裁判文书网id
    String cpwsId = "cpwsId";

    //案由
    String causeName = "causeName";

    //公报案例类型
    String caseEnumType = "caseEnumType";

    // 文书期刊号(暂时只用于公报案例)
    String issueNum = "issueNum";

    //对于案例：1=最高法，2=最高检
    String sourceType = "source_type";
}
