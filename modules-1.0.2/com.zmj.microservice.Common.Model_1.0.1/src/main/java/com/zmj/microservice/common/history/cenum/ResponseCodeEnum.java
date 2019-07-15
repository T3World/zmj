package com.zmj.microservice.common.history.cenum;

/**
 * @author umr,hushixian
 */

public enum ResponseCodeEnum {
    /**
     * 用于表示返回结果的状态信息
     */
    // 200系表示能与服务器正常通信,请求与响应格式正确,可以得到流程内的处理结果
    // 表示ok,
    OK(200),
    // 表示查无结果,
    EMPTY_RESULT(201),
    //300系表示可预知的远程调用失败,消费降级
    RPC_FAILED(300),
    // 400系表示非法请求
    //非法参数
    ILLEGAL_PARAM(400),
    //拒绝访问
    ACCESS_DENIED(401),
    // 500系表示服务器异常,出现了运行时异常,出现了意料之外的错误
    // 未知异常
    UNKNOW_EXCEPTION(500),
    // 表示无法与数据库建立连接
    CONNECT_DB_FAILED(501);

    private Integer value;

    ResponseCodeEnum(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
