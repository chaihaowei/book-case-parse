sample
===
* 注释

	select #use("cols")# from kafka_lawyer  where  #use("condition")#

cols
===
	id,record,message,json_content,date_type

updateSample
===
	
	id=#id#,record=#record#,message=#message#,json_content=#jsonContent#,date_type=#dateType#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(record)){
	 and record=#record#
	@}
	@if(!isEmpty(message)){
	 and message=#message#
	@}
	@if(!isEmpty(jsonContent)){
	 and json_content=#jsonContent#
	@}
	@if(!isEmpty(dateType)){
	 and date_type=#dateType#
	@}
	
	