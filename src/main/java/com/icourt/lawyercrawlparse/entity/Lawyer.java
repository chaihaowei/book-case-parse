package com.icourt.lawyercrawlparse.entity;
import java.util.Date;

import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-09-09
*/
@Table(name="matter_case.lawyer")
@Data
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


}
