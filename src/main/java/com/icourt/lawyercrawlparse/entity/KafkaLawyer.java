package com.icourt.lawyercrawlparse.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-09-09
*/
@Table(name="matter_case.kafka_lawyer_01")
public class KafkaLawyer   {
	
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_date_type = "date_type";
	public static final String ALIAS_json_content = "json_content";
	public static final String ALIAS_message = "message";
	public static final String ALIAS_record = "record";
	
	private Integer id ;
	private String dateType ;
	private String jsonContent ;
	private String message ;
	private String record ;
	
	public KafkaLawyer() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public String getDateType(){
		return  dateType;
	}
	public void setDateType(String dateType ){
		this.dateType = dateType;
	}
	
	public String getJsonContent(){
		return  jsonContent;
	}
	public void setJsonContent(String jsonContent ){
		this.jsonContent = jsonContent;
	}
	
	public String getMessage(){
		return  message;
	}
	public void setMessage(String message ){
		this.message = message;
	}
	
	public String getRecord(){
		return  record;
	}
	public void setRecord(String record ){
		this.record = record;
	}
	

}
