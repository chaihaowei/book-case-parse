package com.icourt.lawyercrawlparse.entity;
import java.math.*;
import java.util.Date;

import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-09-09
*/
@Table(name="matter_case.user")
@Data
public class User   {
	
	// alias
	public static final String ALIAS_base_id = "base_id";
	public static final String ALIAS_birthday_year = "birthday_year";
	public static final String ALIAS_e3_lawyer_id = "e3_lawyer_id";
	public static final String ALIAS_ins_id = "ins_id";
	public static final String ALIAS_is_alpha_user = "is_alpha_user";
	public static final String ALIAS_is_contacted = "is_contacted";
	public static final String ALIAS_is_icourt_student = "is_icourt_student";
	public static final String ALIAS_is_valid = "is_valid";
	public static final String ALIAS_address = "address";
	public static final String ALIAS_antipathy_level = "antipathy_level";
	public static final String ALIAS_applyFee = "applyFee";
	public static final String ALIAS_area = "area";
	public static final String ALIAS_associations = "associations";
	public static final String ALIAS_attention = "attention";
	public static final String ALIAS_channel = "channel";
	public static final String ALIAS_city = "city";
	public static final String ALIAS_communication_status = "communication_status";
	public static final String ALIAS_country = "country";
	public static final String ALIAS_customer_intent = "customer_intent";
	public static final String ALIAS_customer_level = "customer_level";
	public static final String ALIAS_decision_point = "decision_point";
	public static final String ALIAS_development_needs = "development_needs";
	public static final String ALIAS_education = "education";
	public static final String ALIAS_email = "email";
	public static final String ALIAS_fw_sp_pkid = "fw_sp_pkid";
	public static final String ALIAS_gender = "gender";
	public static final String ALIAS_headimgurl = "headimgurl";
	public static final String ALIAS_id_card_num = "id_card_num";
	public static final String ALIAS_important_honor = "important_honor";
	public static final String ALIAS_impression = "impression";
	public static final String ALIAS_institution = "institution";
	public static final String ALIAS_introducer = "introducer";
	public static final String ALIAS_introduction = "introduction";
	public static final String ALIAS_mobile_phone = "mobile_phone";
	public static final String ALIAS_name = "name";
	public static final String ALIAS_nationality = "nationality";
	public static final String ALIAS_occupation = "occupation";
	public static final String ALIAS_paidFee = "paidFee";
	public static final String ALIAS_political_status = "political_status";
	public static final String ALIAS_position = "position";
	public static final String ALIAS_postal_code = "postal_code";
	public static final String ALIAS_province = "province";
	public static final String ALIAS_score = "score";
	public static final String ALIAS_social_identity = "social_identity";
	public static final String ALIAS_tags = "tags";
	public static final String ALIAS_telephone = "telephone";
	public static final String ALIAS_tools_used = "tools_used";
	public static final String ALIAS_user_create = "user_create";
	public static final String ALIAS_user_modify = "user_modify";
	public static final String ALIAS_wechat = "wechat";
	public static final String ALIAS_zyjg = "zyjg";
	public static final String ALIAS_birthday = "birthday";
	public static final String ALIAS_certificate_award_time = "certificate_award_time";
	public static final String ALIAS_fetch_update_time = "fetch_update_time";
	public static final String ALIAS_fw_sp_create_time = "fw_sp_create_time";
	public static final String ALIAS_fw_sp_update_time = "fw_sp_update_time";
	public static final String ALIAS_gmt_create = "gmt_create";
	public static final String ALIAS_gmt_modify = "gmt_modify";
	
	/*
	用户ID
	*/
	private Integer baseId ;
	/*
	出生年
	*/
	private Integer birthdayYear ;
	private Integer e3LawyerId ;
	/*
	系统字段。机构ID
	*/
	private Integer insId ;
	/*
	是否是Alpha用户。1 是 0 否
	*/
	private Integer isAlphaUser ;
	/*
	是否沟通过。1 是 0 否
	*/
	private Integer isContacted ;
	/*
	是否是iCourt学员。1 是 0 否
	*/
	private Integer isIcourtStudent ;
	/*
	系统字段。是否有效。1 是 0 否
	*/
	private Integer isValid ;
	/*
	联系地址
	*/
	private String address ;
	/*
	反感度级别
	*/
	private String antipathyLevel ;
	/*
	申请账户费用
	*/
	private BigDecimal applyfee ;
	/*
	地区
	*/
	private String area ;
	/*
	协会信息。朝阳律协-会长；北京律协-理事
	*/
	private String associations ;
	/*
	用户关注度 S A B
	*/
	private String attention ;
	/*
	来源渠道。介绍、咨询、法秀、活动，其他可填写。如果是介绍，需要填写介绍人
	*/
	private String channel ;
	/*
	城市
	*/
	private String city ;
	/*
	沟通状态。已添加-有意向-强意向-待付款-已付款-已申请-已开户-已匹配  搁置中
	*/
	private String communicationStatus ;
	/*
	国家
	*/
	private String country ;
	/*
	客户意向。1  平台板 2 团队版
	*/
	private String customerIntent ;
	/*
	级别. 0 无 1 A 2 A+ 3 A++
	*/
	private String customerLevel ;
	/*
	用户决策点。管理、大数据、专业、效率。其他可填写。
	*/
	private String decisionPoint ;
	/*
	发展需求。商业律师、专业律师、管理。其他可填写。
	*/
	private String developmentNeeds ;
	/*
	学历。1 大专、2 本科、3 硕士、4 博士、5 LLM、6 JD、7 其他
	*/
	private String education ;
	/*
	邮箱
	*/
	private String email ;
	/*
	法网唯一的ID
	*/
	private String fwSpPkid ;
	/*
	性别。1 男 2 女 0 未知
	*/
	private String gender ;
	/*
	头像URL地址
	*/
	private String headimgurl ;
	/*
	身份证号
	*/
	private String idCardNum ;
	/*
	重要荣誉。年份，荣誉；年份，荣誉
	*/
	private String importantHonor ;
	/*
	印象
	*/
	private String impression ;
	/*
	机构名称
	*/
	private String institution ;
	/*
	介绍人。当来源为介绍的时候填写
	*/
	private String introducer ;
	/*
	个人简介
	*/
	private String introduction ;
	/*
	手机号
	*/
	private String mobilePhone ;
	/*
	姓名
	*/
	private String name ;
	/*
	民族
	*/
	private String nationality ;
	/*
	职业。律师、法务、法学生、行政、财务、法官、检察官 、公证员、警察、公务员。其他手填
	*/
	private String occupation ;
	/*
	已支付费用
	*/
	private BigDecimal paidfee ;
	/*
	政治面貌
	*/
	private String politicalStatus ;
	/*
	职务。合伙人、主任、律师、实习生、行政、财务、法务负责人、法务。其他手填
	*/
	private String position ;
	/*
	邮政编码
	*/
	private String postalCode ;
	/*
	省份
	*/
	private String province ;
	/*
	智能分数
	*/
	private BigDecimal score ;
	/*
	社会身份。现任，北京丰台区政协，政协委员; 曾任，中国企业家协会，维权专家
	*/
	private String socialIdentity ;
	/*
	标签。逗号分隔
	*/
	private String tags ;
	/*
	座机号。多个
	*/
	private String telephone ;
	/*
	用户前期工具情况。印象笔记、坚果云、teambition、元典、必智、法禅、金助理、天行通。其他可填写
	*/
	private String toolsUsed ;
	/*
	系统字段。创建者AlphaID
	*/
	private String userCreate ;
	/*
	系统字段。修改者AlphaID
	*/
	private String userModify ;
	/*
	微信号
	*/
	private String wechat ;
	/*
	类似某个维度的Id, 暂时存储
	*/
	private String zyjg ;
	/*
	生日
	*/
	private Date birthday ;
	/*
	发证日期
	*/
	private Date certificateAwardTime ;
	/*
	爬虫更新时间
	*/
	private Date fetchUpdateTime ;
	/*
	法网创建时间
	*/
	private Date fwSpCreateTime ;
	/*
	法网更新时间
	*/
	private Date fwSpUpdateTime ;
	/*
	系统字段。创建时间
	*/
	private Date gmtCreate ;
	/*
	系统字段。修改时间
	*/
	private Date gmtModify ;
}
