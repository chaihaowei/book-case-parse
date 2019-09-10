sample
===
* 注释

	select #use("cols")# from user  where  #use("condition")#

cols
===
	e3_lawyer_id,base_id,name,headimgurl,birthday,birthday_year,gender,occupation,institution,position,customer_level,mobile_phone,telephone,email,wechat,country,province,city,area,education,nationality,political_status,social_identity,channel,introducer,tools_used,decision_point,development_needs,important_honor,communication_status,customer_intent,associations,attention,applyFee,paidFee,tags,is_alpha_user,is_icourt_student,is_contacted,ins_id,is_valid,user_create,user_modify,gmt_create,gmt_modify,fw_sp_pkid,id_card_num,postal_code,introduction,certificate_award_time,zyjg,fw_sp_create_time,fw_sp_update_time,address,fetch_update_time,score,antipathy_level,impression

updateSample
===
	
	e3_lawyer_id=#e3LawyerId#,base_id=#baseId#,name=#name#,headimgurl=#headimgurl#,birthday=#birthday#,birthday_year=#birthdayYear#,gender=#gender#,occupation=#occupation#,institution=#institution#,position=#position#,customer_level=#customerLevel#,mobile_phone=#mobilePhone#,telephone=#telephone#,email=#email#,wechat=#wechat#,country=#country#,province=#province#,city=#city#,area=#area#,education=#education#,nationality=#nationality#,political_status=#politicalStatus#,social_identity=#socialIdentity#,channel=#channel#,introducer=#introducer#,tools_used=#toolsUsed#,decision_point=#decisionPoint#,development_needs=#developmentNeeds#,important_honor=#importantHonor#,communication_status=#communicationStatus#,customer_intent=#customerIntent#,associations=#associations#,attention=#attention#,applyFee=#applyfee#,paidFee=#paidfee#,tags=#tags#,is_alpha_user=#isAlphaUser#,is_icourt_student=#isIcourtStudent#,is_contacted=#isContacted#,ins_id=#insId#,is_valid=#isValid#,user_create=#userCreate#,user_modify=#userModify#,gmt_create=#gmtCreate#,gmt_modify=#gmtModify#,fw_sp_pkid=#fwSpPkid#,id_card_num=#idCardNum#,postal_code=#postalCode#,introduction=#introduction#,certificate_award_time=#certificateAwardTime#,zyjg=#zyjg#,fw_sp_create_time=#fwSpCreateTime#,fw_sp_update_time=#fwSpUpdateTime#,address=#address#,fetch_update_time=#fetchUpdateTime#,score=#score#,antipathy_level=#antipathyLevel#,impression=#impression#

condition
===

	1 = 1  
	@if(!isEmpty(e3LawyerId)){
	 and e3_lawyer_id=#e3LawyerId#
	@}
	@if(!isEmpty(baseId)){
	 and base_id=#baseId#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(headimgurl)){
	 and headimgurl=#headimgurl#
	@}
	@if(!isEmpty(birthday)){
	 and birthday=#birthday#
	@}
	@if(!isEmpty(birthdayYear)){
	 and birthday_year=#birthdayYear#
	@}
	@if(!isEmpty(gender)){
	 and gender=#gender#
	@}
	@if(!isEmpty(occupation)){
	 and occupation=#occupation#
	@}
	@if(!isEmpty(institution)){
	 and institution=#institution#
	@}
	@if(!isEmpty(position)){
	 and position=#position#
	@}
	@if(!isEmpty(customerLevel)){
	 and customer_level=#customerLevel#
	@}
	@if(!isEmpty(mobilePhone)){
	 and mobile_phone=#mobilePhone#
	@}
	@if(!isEmpty(telephone)){
	 and telephone=#telephone#
	@}
	@if(!isEmpty(email)){
	 and email=#email#
	@}
	@if(!isEmpty(wechat)){
	 and wechat=#wechat#
	@}
	@if(!isEmpty(country)){
	 and country=#country#
	@}
	@if(!isEmpty(province)){
	 and province=#province#
	@}
	@if(!isEmpty(city)){
	 and city=#city#
	@}
	@if(!isEmpty(area)){
	 and area=#area#
	@}
	@if(!isEmpty(education)){
	 and education=#education#
	@}
	@if(!isEmpty(nationality)){
	 and nationality=#nationality#
	@}
	@if(!isEmpty(politicalStatus)){
	 and political_status=#politicalStatus#
	@}
	@if(!isEmpty(socialIdentity)){
	 and social_identity=#socialIdentity#
	@}
	@if(!isEmpty(channel)){
	 and channel=#channel#
	@}
	@if(!isEmpty(introducer)){
	 and introducer=#introducer#
	@}
	@if(!isEmpty(toolsUsed)){
	 and tools_used=#toolsUsed#
	@}
	@if(!isEmpty(decisionPoint)){
	 and decision_point=#decisionPoint#
	@}
	@if(!isEmpty(developmentNeeds)){
	 and development_needs=#developmentNeeds#
	@}
	@if(!isEmpty(importantHonor)){
	 and important_honor=#importantHonor#
	@}
	@if(!isEmpty(communicationStatus)){
	 and communication_status=#communicationStatus#
	@}
	@if(!isEmpty(customerIntent)){
	 and customer_intent=#customerIntent#
	@}
	@if(!isEmpty(associations)){
	 and associations=#associations#
	@}
	@if(!isEmpty(attention)){
	 and attention=#attention#
	@}
	@if(!isEmpty(applyfee)){
	 and applyFee=#applyfee#
	@}
	@if(!isEmpty(paidfee)){
	 and paidFee=#paidfee#
	@}
	@if(!isEmpty(tags)){
	 and tags=#tags#
	@}
	@if(!isEmpty(isAlphaUser)){
	 and is_alpha_user=#isAlphaUser#
	@}
	@if(!isEmpty(isIcourtStudent)){
	 and is_icourt_student=#isIcourtStudent#
	@}
	@if(!isEmpty(isContacted)){
	 and is_contacted=#isContacted#
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
	@if(!isEmpty(fwSpPkid)){
	 and fw_sp_pkid=#fwSpPkid#
	@}
	@if(!isEmpty(idCardNum)){
	 and id_card_num=#idCardNum#
	@}
	@if(!isEmpty(postalCode)){
	 and postal_code=#postalCode#
	@}
	@if(!isEmpty(introduction)){
	 and introduction=#introduction#
	@}
	@if(!isEmpty(certificateAwardTime)){
	 and certificate_award_time=#certificateAwardTime#
	@}
	@if(!isEmpty(zyjg)){
	 and zyjg=#zyjg#
	@}
	@if(!isEmpty(fwSpCreateTime)){
	 and fw_sp_create_time=#fwSpCreateTime#
	@}
	@if(!isEmpty(fwSpUpdateTime)){
	 and fw_sp_update_time=#fwSpUpdateTime#
	@}
	@if(!isEmpty(address)){
	 and address=#address#
	@}
	@if(!isEmpty(fetchUpdateTime)){
	 and fetch_update_time=#fetchUpdateTime#
	@}
	@if(!isEmpty(score)){
	 and score=#score#
	@}
	@if(!isEmpty(antipathyLevel)){
	 and antipathy_level=#antipathyLevel#
	@}
	@if(!isEmpty(impression)){
	 and impression=#impression#
	@}
	
	