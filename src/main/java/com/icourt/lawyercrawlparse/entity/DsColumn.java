package com.icourt.lawyercrawlparse.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Caomr
 */
@Data
public class DsColumn implements Serializable {
    private String id;

    private String text;
    private String ext;

    private String source;
    private String modifier;
    private String modifytype;
    /**
     * 对于案例：0=普通案例,1=公报案例，2=指导案例
     */
    private String publishType;

    private String remark;
    private String syncstate;
    /**
     * 上传时间 一般不修改
     */
    private String uploadtimestamp;

    /**
     * 爬虫爬取发送时间戳
     */
    private String crawltimestamp;

    /**
     * 数据质量分，默认新数据1分，解析程序根据关键字段设置分数范围为30-59以及60-79分， 人工编辑0-100分，默认自动计算分数80-100分
     * 0-20以及30-59分为不及格分数的两个等级
     */
    private String score;
    private String trycount;


    /**
     * 为"1"表示记录已被标记为删除
     */
    private String status;


    @Override
    public String toString() {
        return "DsColumn{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", ext='" + ext + '\'' +
                ", source='" + source + '\'' +
                ", modifier='" + modifier + '\'' +
                ", modifytype='" + modifytype + '\'' +
                ", remark='" + remark + '\'' +
                ", syncstate='" + syncstate + '\'' +
                ", uploadtimestamp='" + uploadtimestamp + '\'' +
                ", score='" + score + '\'' +
                ", trycount='" + trycount + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * 案例分析，法规分析， 案例爬取，法规爬取，检察文书爬取，行政处罚爬取
     */
    public enum ModifierEnum {
        analysis, lawregu, al_crawl, fg_crawl, jcws_crawl, adm_p_crawl, crawl;
    }

    /**
     * 人工新增，人工修改
     */
    public enum ModifyTypeEnum {
        ds_input, ds_update;
    }

    /**
     * 案例文书文件，法规律商数据，法规结构化人工录入,案例裁判文书爬取，案例数据源录入，法规数据源录入，法规北大法宝爬取， 威科爬取，
     * 知识产品文书网爬取，上海文书网爬取，浙江文书网爬取，法信爬取，人大法规爬取, 北京地方文书爬取,对外商事海事，威科法规爬取
     */
    public enum SourceEnum {
        al_wenshufile("案例文书文件"),
        al_crawl_caipan("案例裁判文书爬取"),
        al_ds_input("案例数据源录入"),
        al_crawl_wkinfo("威科案例爬取"),
        al_crawl_iprcase("知识产品文书爬取"),
        al_crawl_hshfy("上海文书网爬取"),
        al_crawl_zjsfgkw("浙江文书网爬取"),
        al_crawl_bjcourt("北京地方文书爬取"),
        al_crawl_guangxi("广西文书网爬取"),
        al_crawl_ccmt("对外商事海事"),
        al_crawl_huayu_local("华宇地方案例"),
        al_crawl_other_without_captcha("无验证码非华宇地方案例"),
        fg_lvshangsite("法规律商数据"),
        fg_struct_input("法规结构化人工录入"),
        fg_ds_input("法规数据源录入"),
        fg_crawl_pkulaw("法规北大法宝爬取"),
        fg_crawl_legal("法规理脉爬取"),
        fg_crawl_faxin("法信爬取"),
        fg_crawl_npc("人大法规爬取"),
        fg_crawl_wkinfo("威科法规爬取"),
        adm_crawl_wkinfo("威科行政处罚爬取"),
        adm_crawl_zjinfo("浙江行政处罚爬取"),
        adm_crawl_hbinfo("河北行政处罚爬取"),
        adm_crawl_other_info("其他第三方行政处罚爬取"),

        jcws_crawl_ajxxgk("检察文书官网爬取"),

        fsqa_crawl_wkinfo("威科法税问答爬取"),
        fsdt_crawl_wkinfo("威科法税动态爬取"),

        al_crawl_gongbao("公报案例爬取");

        private final String value;

        private SourceEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum FamilyEnum {
        content("文本字段"),
        mark("标记字段"),
        calc("计算字段");
        private final String value;

        private FamilyEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum FamilyQContentEnum {
        text("文本源"),
        ext("扩展字段json"),
        timeLimitedBasis("时效影响扩展字段");
        private final String value;

        private FamilyQContentEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum FamilyQMarkEnum {
        source("数据来源"),
        modifier("修改人"),
        modifytype("修改类型"),
        remark("备注"),
        syncstate("同步状态"),
        uploadtimestamp("上传时间戳"),
        crawltimestamp("爬虫爬取发送时间戳"),
        publishType("案例类型，公报案例1，指导案例2"),
        attachs("原文附件地址json"),
        status("标记状态");
        private final String value;

        private FamilyQMarkEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum FamilyQCalcEnum {
        score("评分"),
        trycount("重试次数");
        private final String value;

        private FamilyQCalcEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


}
