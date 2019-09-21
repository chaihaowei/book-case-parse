package com.icourt.lawyercrawlparse.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
*  faxindata 法信
* @author 柴浩伟 2019-09-19
 *
 * https://wiki.alphalawyer.cn/pages/viewpage.action?pageId=62783523
*/
@Data
@ToString
public class Faxindata implements Serializable {

    /**
    * id
    */
    private Integer id;

    /**
    * book_title
    */
    private String bookTitle;

    /**
    * book_id
    */
    private String bookId;

    /**
    * article_id
    */
    private String articleId;

    /**
    * article_title
    */
    private String articleTitle;

    /**
    * article_content
    */
    private String articleContent;

    @TableField(exist = false)
    private String caseCode;


    public Faxindata() {
    }

}