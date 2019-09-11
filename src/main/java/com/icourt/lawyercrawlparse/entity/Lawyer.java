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
@Table(name="matter_case.lawyer_pro")
public class Lawyer   {
	
	// alias
	public static final String ALIAS_base_id = "base_id";
	public static final String ALIAS_ins_id = "ins_id";
	public static final String ALIAS_is_valid = "is_valid";
	public static final String ALIAS_length_of_practice = "length_of_practice";
	public static final String ALIAS_team_leader_user_id = "team_leader_user_id";
	public static final String ALIAS_administrative_license = "administrative_license";
	public static final String ALIAS_assessment_info = "assessment_info";
	public static final String ALIAS_business_area = "business_area";
	public static final String ALIAS_certificate_category = "certificate_category";
	public static final String ALIAS_commendation_info = "commendation_info";
	public static final String ALIAS_competent_authority = "competent_authority";
	public static final String ALIAS_license_number = "license_number";
	public static final String ALIAS_lost_info = "lost_info";
	public static final String ALIAS_other_info = "other_info";
	public static final String ALIAS_practice_category = "practice_category";
	public static final String ALIAS_practice_status = "practice_status";
	public static final String ALIAS_professional_industry = "professional_industry";
	public static final String ALIAS_qualification_code = "qualification_code";
	public static final String ALIAS_team_leader_name = "team_leader_name";
	public static final String ALIAS_team_type = "team_type";
	public static final String ALIAS_technical_sensitivity = "technical_sensitivity";
	public static final String ALIAS_user_create = "user_create";
	public static final String ALIAS_user_modify = "user_modify";
	public static final String ALIAS_certificate_award_time = "certificate_award_time";
	public static final String ALIAS_fetch_update_time = "fetch_update_time";
	public static final String ALIAS_fw_sp_create_time = "fw_sp_create_time";
	public static final String ALIAS_fw_sp_update_time = "fw_sp_update_time";
	public static final String ALIAS_gmt_create = "gmt_create";
	public static final String ALIAS_gmt_modify = "gmt_modify";
	public static final String ALIAS_practice_year = "practice_year";
	
	/*
	用户ID
	*/
	@AssignID
	private Integer baseId ;
	/*
	机构ID
	*/
	private Integer insId ;
	/*
	是否有效 1 是 0 否
	*/
	private Integer isValid ;
	/*
	执业年限
	*/
	private Integer lengthOfPractice ;
	/*
	所在团队负责人ID
	*/
	private Integer teamLeaderUserId ;
	/*
	律师行政许可
	*/
	private String administrativeLicense ;
	/*
	考核信息。不可编辑。定期更新
	*/
	private String assessmentInfo ;
	/*
	业务专长。综合、诉讼业务（诉讼与仲裁）、非诉业务、政府法律顾问、企业法律顾问、刑事业务、行政业务、民商事、婚姻家事、交通事故、合同纠纷、劳动、国际业务、知识产权、公司业务、{金融、证券、保险}、{资本市场、IPO、新三板}、海商海事、{环境、资源与能源}、破产与重组、建设工程、房地产、财税，其他手填
	*/
	private String businessArea ;
	/*
	证书类别。法律执业证等
	*/
	private String certificateCategory ;
	/*
	表彰信息。不可编辑。定期更新
	*/
	private String commendationInfo ;
	/*
	主管机关
	*/
	private String competentAuthority ;
	/*
	执业证号
	*/
	private String licenseNumber ;
	/*
	失信信息。不可编辑。定期更新
	*/
	private String lostInfo ;
	/*
	其他信息。不可编辑。定期更新
	*/
	private String otherInfo ;
	/*
	执业类别。专职或兼职
	*/
	private String practiceCategory ;
	/*
	执业状态。1 正常 0 注销
	*/
	private String practiceStatus ;
	/*
	服务行业。金融业、地产业等，其他可填写
	*/
	private String professionalIndustry ;
	/*
	资格证号
	*/
	private String qualificationCode ;
	/*
	所在团队负责人的姓名
	*/
	private String teamLeaderName ;
	/*
	所在团队类型。紧密、松散
	*/
	private String teamType ;
	/*
	用户技术敏感度。1 高、2 低、3 一般
	*/
	private String technicalSensitivity ;
	/*
	创建者AlphaID
	*/
	private String userCreate ;
	/*
	修改者AlphaID
	*/
	private String userModify ;
	/*
	发证时间
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
	创建时间
	*/
	private Date gmtCreate ;
	/*
	修改时间
	*/
	private Date gmtModify ;
	/*
	执业的起始日期
	*/
	private Date practiceYear ;
	
	public Lawyer() {
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
	* 机构ID
	*@return 
	*/
	public Integer getInsId(){
		return  insId;
	}
	/**
	* 机构ID
	*@param  insId
	*/
	public void setInsId(Integer insId ){
		this.insId = insId;
	}
	
	/**
	* 是否有效 1 是 0 否
	*@return 
	*/
	public Integer getIsValid(){
		return  isValid;
	}
	/**
	* 是否有效 1 是 0 否
	*@param  isValid
	*/
	public void setIsValid(Integer isValid ){
		this.isValid = isValid;
	}
	
	/**
	* 执业年限
	*@return 
	*/
	public Integer getLengthOfPractice(){
		return  lengthOfPractice;
	}
	/**
	* 执业年限
	*@param  lengthOfPractice
	*/
	public void setLengthOfPractice(Integer lengthOfPractice ){
		this.lengthOfPractice = lengthOfPractice;
	}
	
	/**
	* 所在团队负责人ID
	*@return 
	*/
	public Integer getTeamLeaderUserId(){
		return  teamLeaderUserId;
	}
	/**
	* 所在团队负责人ID
	*@param  teamLeaderUserId
	*/
	public void setTeamLeaderUserId(Integer teamLeaderUserId ){
		this.teamLeaderUserId = teamLeaderUserId;
	}
	
	/**
	* 律师行政许可
	*@return 
	*/
	public String getAdministrativeLicense(){
		return  administrativeLicense;
	}
	/**
	* 律师行政许可
	*@param  administrativeLicense
	*/
	public void setAdministrativeLicense(String administrativeLicense ){
		this.administrativeLicense = administrativeLicense;
	}
	
	/**
	* 考核信息。不可编辑。定期更新
	*@return 
	*/
	public String getAssessmentInfo(){
		return  assessmentInfo;
	}
	/**
	* 考核信息。不可编辑。定期更新
	*@param  assessmentInfo
	*/
	public void setAssessmentInfo(String assessmentInfo ){
		this.assessmentInfo = assessmentInfo;
	}
	
	/**
	* 业务专长。综合、诉讼业务（诉讼与仲裁）、非诉业务、政府法律顾问、企业法律顾问、刑事业务、行政业务、民商事、婚姻家事、交通事故、合同纠纷、劳动、国际业务、知识产权、公司业务、{金融、证券、保险}、{资本市场、IPO、新三板}、海商海事、{环境、资源与能源}、破产与重组、建设工程、房地产、财税，其他手填
	*@return 
	*/
	public String getBusinessArea(){
		return  businessArea;
	}
	/**
	* 业务专长。综合、诉讼业务（诉讼与仲裁）、非诉业务、政府法律顾问、企业法律顾问、刑事业务、行政业务、民商事、婚姻家事、交通事故、合同纠纷、劳动、国际业务、知识产权、公司业务、{金融、证券、保险}、{资本市场、IPO、新三板}、海商海事、{环境、资源与能源}、破产与重组、建设工程、房地产、财税，其他手填
	*@param  businessArea
	*/
	public void setBusinessArea(String businessArea ){
		this.businessArea = businessArea;
	}
	
	/**
	* 证书类别。法律执业证等
	*@return 
	*/
	public String getCertificateCategory(){
		return  certificateCategory;
	}
	/**
	* 证书类别。法律执业证等
	*@param  certificateCategory
	*/
	public void setCertificateCategory(String certificateCategory ){
		this.certificateCategory = certificateCategory;
	}
	
	/**
	* 表彰信息。不可编辑。定期更新
	*@return 
	*/
	public String getCommendationInfo(){
		return  commendationInfo;
	}
	/**
	* 表彰信息。不可编辑。定期更新
	*@param  commendationInfo
	*/
	public void setCommendationInfo(String commendationInfo ){
		this.commendationInfo = commendationInfo;
	}
	
	/**
	* 主管机关
	*@return 
	*/
	public String getCompetentAuthority(){
		return  competentAuthority;
	}
	/**
	* 主管机关
	*@param  competentAuthority
	*/
	public void setCompetentAuthority(String competentAuthority ){
		this.competentAuthority = competentAuthority;
	}
	
	/**
	* 执业证号
	*@return 
	*/
	public String getLicenseNumber(){
		return  licenseNumber;
	}
	/**
	* 执业证号
	*@param  licenseNumber
	*/
	public void setLicenseNumber(String licenseNumber ){
		this.licenseNumber = licenseNumber;
	}
	
	/**
	* 失信信息。不可编辑。定期更新
	*@return 
	*/
	public String getLostInfo(){
		return  lostInfo;
	}
	/**
	* 失信信息。不可编辑。定期更新
	*@param  lostInfo
	*/
	public void setLostInfo(String lostInfo ){
		this.lostInfo = lostInfo;
	}
	
	/**
	* 其他信息。不可编辑。定期更新
	*@return 
	*/
	public String getOtherInfo(){
		return  otherInfo;
	}
	/**
	* 其他信息。不可编辑。定期更新
	*@param  otherInfo
	*/
	public void setOtherInfo(String otherInfo ){
		this.otherInfo = otherInfo;
	}
	
	/**
	* 执业类别。专职或兼职
	*@return 
	*/
	public String getPracticeCategory(){
		return  practiceCategory;
	}
	/**
	* 执业类别。专职或兼职
	*@param  practiceCategory
	*/
	public void setPracticeCategory(String practiceCategory ){
		this.practiceCategory = practiceCategory;
	}
	
	/**
	* 执业状态。1 正常 0 注销
	*@return 
	*/
	public String getPracticeStatus(){
		return  practiceStatus;
	}
	/**
	* 执业状态。1 正常 0 注销
	*@param  practiceStatus
	*/
	public void setPracticeStatus(String practiceStatus ){
		this.practiceStatus = practiceStatus;
	}
	
	/**
	* 服务行业。金融业、地产业等，其他可填写
	*@return 
	*/
	public String getProfessionalIndustry(){
		return  professionalIndustry;
	}
	/**
	* 服务行业。金融业、地产业等，其他可填写
	*@param  professionalIndustry
	*/
	public void setProfessionalIndustry(String professionalIndustry ){
		this.professionalIndustry = professionalIndustry;
	}
	
	/**
	* 资格证号
	*@return 
	*/
	public String getQualificationCode(){
		return  qualificationCode;
	}
	/**
	* 资格证号
	*@param  qualificationCode
	*/
	public void setQualificationCode(String qualificationCode ){
		this.qualificationCode = qualificationCode;
	}
	
	/**
	* 所在团队负责人的姓名
	*@return 
	*/
	public String getTeamLeaderName(){
		return  teamLeaderName;
	}
	/**
	* 所在团队负责人的姓名
	*@param  teamLeaderName
	*/
	public void setTeamLeaderName(String teamLeaderName ){
		this.teamLeaderName = teamLeaderName;
	}
	
	/**
	* 所在团队类型。紧密、松散
	*@return 
	*/
	public String getTeamType(){
		return  teamType;
	}
	/**
	* 所在团队类型。紧密、松散
	*@param  teamType
	*/
	public void setTeamType(String teamType ){
		this.teamType = teamType;
	}
	
	/**
	* 用户技术敏感度。1 高、2 低、3 一般
	*@return 
	*/
	public String getTechnicalSensitivity(){
		return  technicalSensitivity;
	}
	/**
	* 用户技术敏感度。1 高、2 低、3 一般
	*@param  technicalSensitivity
	*/
	public void setTechnicalSensitivity(String technicalSensitivity ){
		this.technicalSensitivity = technicalSensitivity;
	}
	
	/**
	* 创建者AlphaID
	*@return 
	*/
	public String getUserCreate(){
		return  userCreate;
	}
	/**
	* 创建者AlphaID
	*@param  userCreate
	*/
	public void setUserCreate(String userCreate ){
		this.userCreate = userCreate;
	}
	
	/**
	* 修改者AlphaID
	*@return 
	*/
	public String getUserModify(){
		return  userModify;
	}
	/**
	* 修改者AlphaID
	*@param  userModify
	*/
	public void setUserModify(String userModify ){
		this.userModify = userModify;
	}
	
	/**
	* 发证时间
	*@return 
	*/
	public Date getCertificateAwardTime(){
		return  certificateAwardTime;
	}
	/**
	* 发证时间
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
	* 创建时间
	*@return 
	*/
	public Date getGmtCreate(){
		return  gmtCreate;
	}
	/**
	* 创建时间
	*@param  gmtCreate
	*/
	public void setGmtCreate(Date gmtCreate ){
		this.gmtCreate = gmtCreate;
	}
	
	/**
	* 修改时间
	*@return 
	*/
	public Date getGmtModify(){
		return  gmtModify;
	}
	/**
	* 修改时间
	*@param  gmtModify
	*/
	public void setGmtModify(Date gmtModify ){
		this.gmtModify = gmtModify;
	}
	
	/**
	* 执业的起始日期
	*@return 
	*/
	public Date getPracticeYear(){
		return  practiceYear;
	}
	/**
	* 执业的起始日期
	*@param  practiceYear
	*/
	public void setPracticeYear(Date practiceYear ){
		this.practiceYear = practiceYear;
	}
	

}
