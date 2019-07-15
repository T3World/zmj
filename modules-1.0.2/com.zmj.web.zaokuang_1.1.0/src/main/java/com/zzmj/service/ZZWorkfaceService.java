package com.zzmj.service;

import java.util.List;
import java.util.Map;

import com.zzmj.pojo.entity.WorkfaceAndConfig;
import com.zzmj.pojo.entity.ZZWorkfaceEntity;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;
import com.zzmj.util.exception.IllegalParamException;

public interface ZZWorkfaceService {

    /**
     * @Title: countAllWorkface
     * @Description: 条件查询返回结果的总数
     * @param: keyword 查询关键字 orgId 所属的组织机构ID
     * @return: int 所有符合条件的工作面的总数
     */
    int countWorkface(String orgId, String keyword);

    /**
     * @Title: getAllWorkface
     * @Description: 条件查询工作面; String类型 nil 表示 空值; int类型 0 表示 空值;
     * @param orgId 工作面所属的组织机构Id, keyword 查询关键字, pageNumber 分页页码, pageSize
     * 分页单页条数
     * @return List<ZZWorkfaceEntity> 符合条件的工作面集合
     * @throws EmptyResultException
     */
    SysResult listWorkface(String orgId, String keyword, Integer pageNo, Integer pageSize)
            throws EmptyResultException;

    /**
     * 新增工作面
     * 
     * @param zzWorkfaceEntity 工作面实体类 zzWorkfaceconfigEntity 工作面配置
     * @return
     * @throws IllegalParamException
     * @throws DoSqlFailedException
     */
    SysResult addWorkface(WorkfaceAndConfig wac) throws IllegalParamException, DoSqlFailedException;

    /**
     * 根据workfaceId修改工作面信息，如要修改配置信息，则需要级联调用修改工作面配置信息表。
     * 
     * @param workfaceId 工作面id
     * @return
     * @throws Exception
     */
    SysResult updateWorkface(WorkfaceAndConfig wac) throws Exception;

    /**
     * 根据workfaceId删除该工作面，如要删除工作面，则也要级联删除，该工作面所拥有的配置信息。
     * 
     * @param workfaceId
     * @return
     * @throws IllegalParamException
     * @throws DoSqlFailedException
     */
    SysResult delWorkface(String workfaceId) throws IllegalParamException, DoSqlFailedException;

    SysResult stopWorkface(String workfaceId, Integer workfaceState);

    List<Map<String, Object>> listWorkfaceNoPage(String orgId);
}
