package com.zzmj.pojo.entity;

/**
 * @description update.json
 * @author umr
 * @date 2019/7/22
 */
public class Update {
    /**
     * 更新说明
     * */
    private String note;
    /**
     * 最新版本号
     */
    private String version;
    /**
     * 最新文件名
     * */
    private String fileName;

    public Update() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Update{" +
                "note='" + note + '\'' +
                ", version='" + version + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
