package com.icourt.lawyercrawlparse.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-09-10
*/
@Table(name="matter_case.user_02")
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
	@AssignID
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
	
	public User() {
	}
	
	/**
	* 用户ID
	*@return 
	*/
	public Integer getBaseId(){
		return  baseId;
	}
	/**
	* 用户ID
	*@param  baseId
	*/
	public void setBaseId(Integer baseId ){
		this.baseId = baseId;
	}
	
	/**
	* 出生年
	*@return 
	*/
	public Integer getBirthdayYear(){
		return  birthdayYear;
	}
	/**
	* 出生年
	*@param  birthdayYear
	*/
	public void setBirthdayYear(Integer birthdayYear ){
		this.birthdayYear = birthdayYear;
	}
	
	public Integer getE3LawyerId(){
		return  e3LawyerId;
	}
	public void setE3LawyerId(Integer e3LawyerId ){
		this.e3LawyerId = e3LawyerId;
	}
	
	/**
	* 系统字段。机构ID
	*@return 
	*/
	public Integer getInsId(){
		return  insId;
	}
	/**
	* 系统字段。机构ID
	*@param  insId
	*/
	public void setInsId(Integer insId ){
		this.insId = insId;
	}
	
	/**
	* 是否是Alpha用户。1 是 0 否
	*@return 
	*/
	public Integer getIsAlphaUser(){
		return  isAlphaUser;
	}
	/**
	* 是否是Alpha用户。1 是 0 否
	*@param  isAlphaUser
	*/
	public void setIsAlphaUser(Integer isAlphaUser ){
		this.isAlphaUser = isAlphaUser;
	}
	
	/**
	* 是否沟通过。1 是 0 否
	*@return 
	*/
	public Integer getIsContacted(){
		return  isContacted;
	}
	/**
	* 是否沟通过。1 是 0 否
	*@param  isContacted
	*/
	public void setIsContacted(Integer isContacted ){
		this.isContacted = isContacted;
	}
	
	/**
	* 是否是iCourt学员。1 是 0 否
	*@return 
	*/
	public Integer getIsIcourtStudent(){
		return  isIcourtStudent;
	}
	/**
	* 是否是iCourt学员。1 是 0 否
	*@param  isIcourtStudent
	*/
	public void setIsIcourtStudent(Integer isIcourtStudent ){
		this.isIcourtStudent = isIcourtStudent;
	}
	
	/**
	* 系统字段。是否有效。1 是 0 否
	*@return 
	*/
	public Integer getIsValid(){
		return  isValid;
	}
	/**
	* 系统字段。是否有效。1 是 0 否
	*@param  isValid
	*/
	public void setIsValid(Integer isValid ){
		this.isValid = isValid;
	}
	
	/**
	* 联系地址
	*@return 
	*/
	public String getAddress(){
		return  address;
	}
	/**
	* 联系地址
	*@param  address
	*/
	public void setAddress(String address ){
		this.address = address;
	}
	
	/**
	* 反感度级别
	*@return 
	*/
	public String getAntipathyLevel(){
		return  antipathyLevel;
	}
	/**
	* 反感度级别
	*@param  antipathyLevel
	*/
	public void setAntipathyLevel(String antipathyLevel ){
		this.antipathyLevel = antipathyLevel;
	}
	
	/**
	* 申请账户费用
	*@return 
	*/
	public BigDecimal getApplyfee(){
		return  applyfee;
	}
	/**
	* 申请账户费用
	*@param  applyfee
	*/
	public void setApplyfee(BigDecimal applyfee ){
		this.applyfee = applyfee;
	}
	
	/**
	* 地区
	*@return 
	*/
	public String getArea(){
		return  area;
	}
	/**
	* 地区
	*@param  area
	*/
	public void setArea(String area ){
		this.area = area;
	}
	
	/**
	* 协会信息。朝阳律协-会长；北京律协-理事
	*@return 
	*/
	public String getAssociations(){
		return  associations;
	}
	/**
	* 协会信息。朝阳律协-会长；北京律协-理事
	*@param  associations
	*/
	public void setAssociations(String associations ){
		this.associations = associations;
	}
	
	/**
	* 用户关注度 S A B
	*@return 
	*/
	public String getAttention(){
		return  attention;
	}
	/**
	* 用户关注度 S A B
	*@param  attention
	*/
	public void setAttention(String attention ){
		this.attention = attention;
	}
	
	/**
	* 来源渠道。介绍、咨询、法秀、活动，其他可填写。如果是介绍，需要填写介绍人
	*@return 
	*/
	public String getChannel(){
		return  channel;
	}
	/**
	* 来源渠道。介绍、咨询、法秀、活动，其他可填写。如果是介绍，需要填写介绍人
	*@param  channel
	*/
	public void setChannel(String channel ){
		this.channel = channel;
	}
	
	/**
	* 城市
	*@return 
	*/
	public String getCity(){
		return  city;
	}
	/**
	* 城市
	*@param  city
	*/
	public void setCity(String city ){
		this.city = city;
	}
	
	/**
	* 沟通状态。已添加-有意向-强意向-待付款-已付款-已申请-已开户-已匹配  搁置中
	*@return 
	*/
	public String getCommunicationStatus(){
		return  communicationStatus;
	}
	/**
	* 沟通状态。已添加-有意向-强意向-待付款-已付款-已申请-已开户-已匹配  搁置中
	*@param  communicationStatus
	*/
	public void setCommunicationStatus(String communicationStatus ){
		this.communicationStatus = communicationStatus;
	}
	
	/**
	* 国家
	*@return 
	*/
	public String getCountry(){
		return  country;
	}
	/**
	* 国家
	*@param  country
	*/
	public void setCountry(String country ){
		this.country = country;
	}
	
	/**
	* 客户意向。1  平台板 2 团队版
	*@return 
	*/
	public String getCustomerIntent(){
		return  customerIntent;
	}
	/**
	* 客户意向。1  平台板 2 团队版
	*@param  customerIntent
	*/
	public void setCustomerIntent(String customerIntent ){
		this.customerIntent = customerIntent;
	}
	
	/**
	* 级别. 0 无 1 A 2 A+ 3 A++
	*@return 
	*/
	public String getCustomerLevel(){
		return  customerLevel;
	}
	/**
	* 级别. 0 无 1 A 2 A+ 3 A++
	*@param  customerLevel
	*/
	public void setCustomerLevel(String customerLevel ){
		this.customerLevel = customerLevel;
	}
	
	/**
	* 用户决策点。管理、大数据、专业、效率。其他可填写。
	*@return 
	*/
	public String getDecisionPoint(){
		return  decisionPoint;
	}
	/**
	* 用户决策点。管理、大数据、专业、效率。其他可填写。
	*@param  decisionPoint
	*/
	public void setDecisionPoint(String decisionPoint ){
		this.decisionPoint = decisionPoint;
	}
	
	/**
	* 发展需求。商业律师、专业律师、管理。其他可填写。
	*@return 
	*/
	public String getDevelopmentNeeds(){
		return  developmentNeeds;
	}
	/**
	* 发展需求。商业律师、专业律师、管理。其他可填写。
	*@param  developmentNeeds
	*/
	public void setDevelopmentNeeds(String developmentNeeds ){
		this.developmentNeeds = developmentNeeds;
	}
	
	/**
	* 学历。1 大专、2 本科、3 硕士、4 博士、5 LLM、6 JD、7 其他
	*@return 
	*/
	public String getEducation(){
		return  education;
	}
	/**
	* 学历。1 大专、2 本科、3 硕士、4 博士、5 LLM、6 JD、7 其他
	*@param  education
	*/
	public void setEducation(String education ){
		this.education = education;
	}
	
	/**
	* 邮箱
	*@return 
	*/
	public String getEmail(){
		return  email;
	}
	/**
	* 邮箱
	*@param  email
	*/
	public void setEmail(String email ){
		this.email = email;
	}
	
	/**
	* 法网唯一的ID
	*@return 
	*/
	public String getFwSpPkid(){
		return  fwSpPkid;
	}
	/**
	* 法网唯一的ID
	*@param  fwSpPkid
	*/
	public void setFwSpPkid(String fwSpPkid ){
		this.fwSpPkid = fwSpPkid;
	}
	
	/**
	* 性别。1 男 2 女 0 未知
	*@return 
	*/
	public String getGender(){
		return  gender;
	}
	/**
	* 性别。1 男 2 女 0 未知
	*@param  gender
	*/
	public void setGender(String gender ){
		this.gender = gender;
	}
	
	/**
	* 头像URL地址
	*@return 
	*/
	public String getHeadimgurl(){
		return  headimgurl;
	}
	/**
	* 头像URL地址
	*@param  headimgurl
	*/
	public void setHeadimgurl(String headimgurl ){
		this.headimgurl = headimgurl;
	}
	
	/**
	* 身份证号
	*@return 
	*/
	public String getIdCardNum(){
		return  idCardNum;
	}
	/**
	* 身份证号
	*@param  idCardNum
	*/
	public void setIdCardNum(String idCardNum ){
		this.idCardNum = idCardNum;
	}
	
	/**
	* 重要荣誉。年份，荣誉；年份，荣誉
	*@return 
	*/
	public String getImportantHonor(){
		return  importantHonor;
	}
	/**
	* 重要荣誉。年份，荣誉；年份，荣誉
	*@param  importantHonor
	*/
	public void setImportantHonor(String importantHonor ){
		this.importantHonor = importantHonor;
	}
	
	/**
	* 印象
	*@return 
	*/
	public String getImpression(){
		return  impression;
	}
	/**
	* 印象
	*@param  impression
	*/
	public void setImpression(String impression ){
		this.impression = impression;
	}
	
	/**
	* 机构名称
	*@return 
	*/
	public String getInstitution(){
		return  institution;
	}
	/**
	* 机构名称
	*@param  institution
	*/
	public void setInstitution(String institution ){
		this.institution = institution;
	}
	
	/**
	* 介绍人。当来源为介绍的时候填写
	*@return 
	*/
	public String getIntroducer(){
		return  introducer;
	}
	/**
	* 介绍人。当来源为介绍的时候填写
	*@param  introducer
	*/
	public void setIntroducer(String introducer ){
		this.introducer = introducer;
	}
	
	/**
	* 个人简介
	*@return 
	*/
	public String getIntroduction(){
		return  introduction;
	}
	/**
	* 个人简介
	*@param  introduction
	*/
	public void setIntroduction(String introduction ){
		this.introduction = introduction;
	}
	
	/**
	* 手机号
	*@return 
	*/
	public String getMobilePhone(){
		return  mobilePhone;
	}
	/**
	* 手机号
	*@param  mobilePhone
	*/
	public void setMobilePhone(String mobilePhone ){
		this.mobilePhone = mobilePhone;
	}
	
	/**
	* 姓名
	*@return 
	*/
	public String getName(){
		return  name;
	}
	/**
	* 姓名
	*@param  name
	*/
	public void setName(String name ){
		this.name = name;
	}
	
	/**
	* 民族
	*@return 
	*/
	public String getNationality(){
		return  nationality;
	}
	/**
	* 民族
	*@param  nationality
	*/
	public void setNationality(String nationality ){
		this.nationality = nationality;
	}
	
	/**
	* 职业。律师、法务、法学生、行政、财务、法官、检察官 、公证员、警察、公务员。其他手填
	*@return 
	*/
	public String getOccupation(){
		return  occupation;
	}
	/**
	* 职业。律师、法务、法学生、行政、财务、法官、检察官 、公证员、警察、公务员。其他手填
	*@param  occupation
	*/
	public void setOccupation(String occupation ){
		this.occupation = occupation;
	}
	
	/**
	* 已支付费用
	*@return 
	*/
	public BigDecimal getPaidfee(){
		return  paidfee;
	}
	/**
	* 已支付费用
	*@param  paidfee
	*/
	public void setPaidfee(BigDecimal paidfee ){
		this.paidfee = paidfee;
	}
	
	/**
	* 政治面貌
	*@return 
	*/
	public String getPoliticalStatus(){
		return  politicalStatus;
	}
	/**
	* 政治面貌
	*@param  politicalStatus
	*/
	public void setPoliticalStatus(String politicalStatus ){
		this.politicalStatus = politicalStatus;
	}
	
	/**
	* 职务。合伙人、主任、律师、实习生、行政、财务、法务负责人、法务。其他手填
	*@return 
	*/
	public String getPosition(){
		return  position;
	}
	/**
	* 职务。合伙人、主任、律师、实习生、行政、财务、法务负责人、法务。其他手填
	*@param  position
	*/
	public void setPosition(String position ){
		this.position = position;
	}
	
	/**
	* 邮政编码
	*@return 
	*/
	public String getPostalCode(){
		return  postalCode;
	}
	/**
	* 邮政编码
	*@param  postalCode
	*/
	public void setPostalCode(String postalCode ){
		this.postalCode = postalCode;
	}
	
	/**
	* 省份
	*@return 
	*/
	public String getProvince(){
		return  province;
	}
	/**
	* 省份
	*@param  province
	*/
	public void setProvince(String province ){
		this.province = province;
	}
	
	/**
	* 智能分数
	*@return 
	*/
	public BigDecimal getScore(){
		return  score;
	}
	/**
	* 智能分数
	*@param  score
	*/
	public void setScore(BigDecimal score ){
		this.score = score;
	}
	
	/**
	* 社会身份。现任，北京丰台区政协，政协委员; 曾任，中国企业家协会，维权专家
	*@return 
	*/
	public String getSocialIdentity(){
		return  socialIdentity;
	}
	/**
	* 社会身份。现任，北京丰台区政协，政协委员; 曾任，中国企业家协会，维权专家
	*@param  socialIdentity
	*/
	public void setSocialIdentity(String socialIdentity ){
		this.socialIdentity = socialIdentity;
	}
	
	/**
	* 标签。逗号分隔
	*@return 
	*/
	public String getTags(){
		return  tags;
	}
	/**
	* 标签。逗号分隔
	*@param  tags
	*/
	public void setTags(String tags ){
		this.tags = tags;
	}
	
	/**
	* 座机号。多个
	*@return 
	*/
	public String getTelephone(){
		return  telephone;
	}
	/**
	* 座机号。多个
	*@param  telephone
	*/
	public void setTelephone(String telephone ){
		this.telephone = telephone;
	}
	
	/**
	* 用户前期工具情况。印象笔记、坚果云、teambition、元典、必智、法禅、金助理、天行通。其他可填写
	*@return 
	*/
	public String getToolsUsed(){
		return  toolsUsed;
	}
	/**
	* 用户前期工具情况。印象笔记、坚果云、teambition、元典、必智、法禅、金助理、天行通。其他可填写
	*@param  toolsUsed
	*/
	public void setToolsUsed(String toolsUsed ){
		this.toolsUsed = toolsUsed;
	}
	
	/**
	* 系统字段。创建者AlphaID
	*@return 
	*/
	public String getUserCreate(){
		return  userCreate;
	}
	/**
	* 系统字段。创建者AlphaID
	*@param  userCreate
	*/
	public void setUserCreate(String userCreate ){
		this.userCreate = userCreate;
	}
	
	/**
	* 系统字段。修改者AlphaID
	*@return 
	*/
	public String getUserModify(){
		return  userModify;
	}
	/**
	* 系统字段。修改者AlphaID
	*@param  userModify
	*/
	public void setUserModify(String userModify ){
		this.userModify = userModify;
	}
	
	/**
	* 微信号
	*@return 
	*/
	public String getWechat(){
		return  wechat;
	}
	/**
	* 微信号
	*@param  wechat
	*/
	public void setWechat(String wechat ){
		this.wechat = wechat;
	}
	
	/**
	* 类似某个维度的Id, 暂时存储
	*@return 
	*/
	public String getZyjg(){
		return  zyjg;
	}
	/**
	* 类似某个维度的Id, 暂时存储
	*@param  zyjg
	*/
	public void setZyjg(String zyjg ){
		this.zyjg = zyjg;
	}
	
	/**
	* 生日
	*@return 
	*/
	public Date getBirthday(){
		return  birthday;
	}
	/**
	* 生日
	*@param  birthday
	*/
	public void setBirthday(Date birthday ){
		this.birthday = birthday;
	}
	
	/**
	* 发证日期
	*@return 
	*/
	public Date getCertificateAwardTime(){
		return  certificateAwardTime;
	}
	/**
	* 发证日期
	*@param  certificateAwardTime
	*/
	public void setCertificateAwardTime(Date certificateAwardTime ){
		this.certificateAwardTime = certificateAwardTime;
	}
	
	/**
	* 爬虫更新时间
	*@return 
	*/
	public Date getFetchUpdateTime(){
		return  fetchUpdateTime;
	}
	/**
	* 爬虫更新时间
	*@param  fetchUpdateTime
	*/
	public void setFetchUpdateTime(Date fetchUpdateTime ){
		this.fetchUpdateTime = fetchUpdateTime;
	}
	
	/**
	* 法网创建时间
	*@return 
	*/
	public Date getFwSpCreateTime(){
		return  fwSpCreateTime;
	}
	/**
	* 法网创建时间
	*@param  fwSpCreateTime
	*/
	public void setFwSpCreateTime(Date fwSpCreateTime ){
		this.fwSpCreateTime = fwSpCreateTime;
	}
	
	/**
	* 法网更新时间
	*@return 
	*/
	public Date getFwSpUpdateTime(){
		return  fwSpUpdateTime;
	}
	/**
	* 法网更新时间
	*@param  fwSpUpdateTime
	*/
	public void setFwSpUpdateTime(Date fwSpUpdateTime ){
		this.fwSpUpdateTime = fwSpUpdateTime;
	}
	
	/**
	* 系统字段。创建时间
	*@return 
	*/
	public Date getGmtCreate(){
		return  gmtCreate;
	}
	/**
	* 系统字段。创建时间
	*@param  gmtCreate
	*/
	public void setGmtCreate(Date gmtCreate ){
		this.gmtCreate = gmtCreate;
	}
	
	/**
	* 系统字段。修改时间
	*@return 
	*/
	public Date getGmtModify(){
		return  gmtModify;
	}
	/**
	* 系统字段。修改时间
	*@param  gmtModify
	*/
	public void setGmtModify(Date gmtModify ){
		this.gmtModify = gmtModify;
	}
	

}
