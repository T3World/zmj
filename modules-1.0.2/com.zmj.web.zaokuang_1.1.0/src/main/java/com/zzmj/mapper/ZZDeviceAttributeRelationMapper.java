package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZDeviceAttributeRelation;
import com.zzmj.pojo.vo.AttributeInnerAttributeDevice;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZZDeviceAttributeRelationMapper {

	/**
	 * 	 根据(attribute_id)判断与之关联的设备属性关系表是否有isDel为0的数据
	 * @param attributeId
	 * @return
	 */
	int getDeviceAttributeRelationByAttributeId(String attributeId);
	
	/**
	 * 添加设备属性关系表
	 * @param zzDeviceAttributeRelation
	 */
	int insertZZDeviceAttributeRelation(ZZDeviceAttributeRelation zzDeviceAttributeRelation);
	
	
	//List<ZZDeviceAttributeRelationMapper> selectByDeviceId(@Param("deviceId") String deviceId);
	//List<ZZDeviceAttributeRelationMapper> selectByAttributeId(@Param("attributeId") String attributeId);

	

    List<AttributeInnerAttributeDevice>  listDeviceAttributeAllByDeviceId(@Param("deviceId") String deviceId);

    
    /**
     * 更改设备属性表
     * @param deviceId
     */
    //根据deviceId删除设备属性关系表
    void deleteByDeviceId(String deviceId);

    List<AttributeInnerAttributeDevice> listDeviceAttributeSimpleByDeviceId(@Param("deviceId") String deviceId);
}
