package com.skyland.zimuzutv.zimuzutv.MVP.Entity;

/**
 * Created by skyland on 2016/12/29.
 */

public class SubtitleListDto {
    private String id;
    private String cnname;
    private String enname;
    private String resourceid;
    private SubtitleResInfoDto resource_info;
    private String segment;
    private String source;
    private String category;
    private String lang;
    private String format;
    private String remark;
    private String views;
    private String dateline;

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public SubtitleResInfoDto getResource_info() {
        return resource_info;
    }

    public void setResource_info(SubtitleResInfoDto resource_info) {
        this.resource_info = resource_info;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

}
