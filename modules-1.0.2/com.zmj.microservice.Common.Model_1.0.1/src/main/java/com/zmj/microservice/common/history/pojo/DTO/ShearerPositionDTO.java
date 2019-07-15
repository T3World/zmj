package com.zmj.microservice.common.history.pojo.DTO;

import javax.validation.constraints.NotNull;

/**
 * 用于查询在指定时间段内采煤机编码器的位置数据；
 * @author umr
 * @date 2019/3/7
 */
public class ShearerPositionDTO extends BaseUNDTO{


    /**
     * 已知第一个编码器位置
     * */
    @NotNull
    private Double pos1;
    /**
     * 已知第一个编码器位置对应的支架号
     * */
    @NotNull
    private Integer scuNo1;
    /**
     * 已知第二个编码器位置
     * */
    @NotNull
    private Double pos2;
    /**
     * 已知第二个编码器位置对应的支架号
     * */
    @NotNull
    private Integer scuNo2;

    public Double getPos1() {
        return pos1;
    }

    public void setPos1(Double pos1) {
        this.pos1 = pos1;
    }

    public Integer getScuNo1() {
        return scuNo1;
    }

    public void setScuNo1(Integer scuNo1) {
        this.scuNo1 = scuNo1;
    }

    public Double getPos2() {
        return pos2;
    }

    public void setPos2(Double pos2) {
        this.pos2 = pos2;
    }

    public Integer getScuNo2() {
        return scuNo2;
    }

    public void setScuNo2(Integer scuNo2) {
        this.scuNo2 = scuNo2;
    }

    @Override
    public String toString() {
        return super.toString()+"ShearerPositionDTO{" +
                "pos1=" + pos1 +
                ", scuNo1=" + scuNo1 +
                ", pos2=" + pos2 +
                ", scuNo2=" + scuNo2 +
                '}';
    }
}
