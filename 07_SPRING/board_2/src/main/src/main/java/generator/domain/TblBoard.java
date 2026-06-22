package generator.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName tbl_board
*/
public class TblBoard implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer no;
    /**
    * 
    */
    @NotBlank(message="[]不能为空")
    @Size(max= 200,message="编码长度不能超过200")
    @ApiModelProperty("")
    @Length(max= 200,message="编码长度不能超过200")
    private String title;
    /**
    * 
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("")
    @Length(max= -1,message="编码长度不能超过-1")
    private String content;
    /**
    * 
    */
    @NotBlank(message="[]不能为空")
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("")
    @Length(max= 50,message="编码长度不能超过50")
    private String writer;
    /**
    * 
    */
    @ApiModelProperty("")
    private Date regDate;
    /**
    * 
    */
    @ApiModelProperty("")
    private Date updateDate;

    /**
    * 
    */
    private void setNo(Integer no){
    this.no = no;
    }

    /**
    * 
    */
    private void setTitle(String title){
    this.title = title;
    }

    /**
    * 
    */
    private void setContent(String content){
    this.content = content;
    }

    /**
    * 
    */
    private void setWriter(String writer){
    this.writer = writer;
    }

    /**
    * 
    */
    private void setRegDate(Date regDate){
    this.regDate = regDate;
    }

    /**
    * 
    */
    private void setUpdateDate(Date updateDate){
    this.updateDate = updateDate;
    }


    /**
    * 
    */
    private Integer getNo(){
    return this.no;
    }

    /**
    * 
    */
    private String getTitle(){
    return this.title;
    }

    /**
    * 
    */
    private String getContent(){
    return this.content;
    }

    /**
    * 
    */
    private String getWriter(){
    return this.writer;
    }

    /**
    * 
    */
    private Date getRegDate(){
    return this.regDate;
    }

    /**
    * 
    */
    private Date getUpdateDate(){
    return this.updateDate;
    }

}
