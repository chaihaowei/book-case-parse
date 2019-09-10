sample
===
* 注释

	select #use("cols")# from lawyer  where  #use("condition")#

cols
===
	base_id,team_type,team_leader_name,team_leader_user_id,certificate_category,practice_category,practice_status,qualification_code,certificate_award_time,license_number,practice_year,length_of_practice,competent_authority,administrative_license,assessment_info,commendation_info,lost_info,other_info,professional_industry,business_area,technical_sensitivity,ins_id,is_valid,user_create,user_modify,gmt_create,gmt_modify,fw_sp_create_time,fw_sp_update_time,fetch_update_time

updateSample
===
	
	base_id=#baseId#,team_type=#teamType#,team_leader_name=#teamLeaderName#,team_leader_user_id=#teamLeaderUserId#,certificate_category=#certificateCategory#,practice_category=#practiceCategory#,practice_status=#practiceStatus#,qualification_code=#qualificationCode#,certificate_award_time=#certificateAwardTime#,license_number=#licenseNumber#,practice_year=#practiceYear#,length_of_practice=#lengthOfPractice#,competent_authority=#competentAuthority#,administrative_license=#administrativeLicense#,assessment_info=#assessmentInfo#,commendation_info=#commendationInfo#,lost_info=#lostInfo#,other_info=#otherInfo#,professional_industry=#professionalIndustry#,business_area=#businessArea#,technical_sensitivity=#technicalSensitivity#,ins_id=#insId#,is_valid=#isValid#,user_create=#userCreate#,user_modify=#userModify#,gmt_create=#gmtCreate#,gmt_modify=#gmtModify#,fw_sp_create_time=#fwSpCreateTime#,fw_sp_update_time=#fwSpUpdateTime#,fetch_update_time=#fetchUpdateTime#

condition
===

	1 = 1  
	@if(!isEmpty(baseId)){
	 and base_id=#baseId#
	@}
	@if(!isEmpty(teamType)){
	 and team_type=#teamType#
	@}
	@if(!isEmpty(teamLeaderName)){
	 and team_leader_name=#teamLeaderName#
	@}
	@if(!isEmpty(teamLeaderUserId)){
	 and team_leader_user_id=#teamLeaderUserId#
	@}
	@if(!isEmpty(certificateCategory)){
	 and certificate_category=#certificateCategory#
	@}
	@if(!isEmpty(practiceCategory)){
	 and practice_category=#practiceCategory#
	@}
	@if(!isEmpty(practiceStatus)){
	 and practice_status=#practiceStatus#
	@}
	@if(!isEmpty(qualificationCode)){
	 and qualification_code=#qualificationCode#
	@}
	@if(!isEmpty(certificateAwardTime)){
	 and certificate_award_time=#certificateAwardTime#
	@}
	@if(!isEmpty(licenseNumber)){
	 and license_number=#licenseNumber#
	@}
	@if(!isEmpty(practiceYear)){
	 and practice_year=#practiceYear#
	@}
	@if(!isEmpty(lengthOfPractice)){
	 and length_of_practice=#lengthOfPractice#
	@}
	@if(!isEmpty(competentAuthority)){
	 and competent_authority=#competentAuthority#
	@}
	@if(!isEmpty(administrativeLicense)){
	 and administrative_license=#administrativeLicense#
	@}
	@if(!isEmpty(assessmentInfo)){
	 and assessment_info=#assessmentInfo#
	@}
	@if(!isEmpty(commendationInfo)){
	 and commendation_info=#commendationInfo#
	@}
	@if(!isEmpty(lostInfo)){
	 and lost_info=#lostInfo#
	@}
	@if(!isEmpty(otherInfo)){
	 and other_info=#otherInfo#
	@}
	@if(!isEmpty(professionalIndustry)){
	 and professional_industry=#professionalIndustry#
	@}
	@if(!isEmpty(businessArea)){
	 and business_area=#businessArea#
	@}
	@if(!isEmpty(technicalSensitivity)){
	 and technical_sensitivity=#technicalSensitivity#
	@}
	@if(!isEmpty(insId)){
	 and ins_id=#insId#
	@}
	@if(!isEmpty(isValid)){
	 and is_valid=#isValid#
	@}
	@if(!isEmpty(userCreate)){
	 and user_create=#userCreate#
	@}
	@if(!isEmpty(userModify)){
	 and user_modify=#userModify#
	@}
	@if(!isEmpty(gmtCreate)){
	 and gmt_create=#gmtCreate#
	@}
	@if(!isEmpty(gmtModify)){
	 and gmt_modify=#gmtModify#
	@}
	@if(!isEmpty(fwSpCreateTime)){
	 and fw_sp_create_time=#fwSpCreateTime#
	@}
	@if(!isEmpty(fwSpUpdateTime)){
	 and fw_sp_update_time=#fwSpUpdateTime#
	@}
	@if(!isEmpty(fetchUpdateTime)){
	 and fetch_update_time=#fetchUpdateTime#
	@}
	
	