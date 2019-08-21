package com.zzmj.pojo.vo;

import java.io.Serializable;

/**
 * @description 类描述
 * @author umr
 * @date 2019/7/22
 */
public class AppUpdate implements Serializable {
    /**
     * 是否更新
     * */
    private Boolean status;
    /**
     * 状态码
     * */
    private Integer statusCode;
    /**
     * 更新说明,当状态码不等于200时,作异常说明
     * */
    private String note;
    /**
     * 新版本app下载地址
     * */
    private String url;

    public AppUpdate() {
    }

    public AppUpdate(Boolean status, Integer statusCode, String note, String url) {
        this.status = status;
        this.statusCode = statusCode;
        this.note = note;
        this.url = url;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
