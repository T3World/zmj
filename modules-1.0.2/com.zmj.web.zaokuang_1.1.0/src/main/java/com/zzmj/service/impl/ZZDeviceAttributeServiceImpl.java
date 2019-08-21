package com.zzmj.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZDeviceAttributeMapper;
import com.zzmj.mapper.ZZDeviceAttributeRelationMapper;
import com.zzmj.pojo.entity.ZZDeviceAttribute;
import com.zzmj.pojo.entity.ZZDeviceAttributeExample;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZDeviceAttributeService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.FormatDateUtil;
import com.zzmj.util.exception.DoSqlFailedException;

@Service
public class ZZDeviceAttributeServiceImpl implements ZZDeviceAttributeService {

	private static final Logger logger = LoggerFactory.getLogger(ZZUserServiceImpl.class);

	@Autowired
	private ZZDeviceAttributeMapper zzDeviceAttributeMapper; 
	@Autowired
	private ZZDeviceAttributeRelationMapper zzDeviceAttributeRelationMapper;

	@Override
	public SysResult addZZDeviceAttribute(ZZDeviceAttribute zzDeviceAttribute) {
		try {
			zzDeviceAttribute.setId(CodeUtil.createUuid36()); // 自动 生成UUID
			zzDeviceAttribute.setAttributeId(CodeUtil.createUuid36()); // 自动生成 UUID
			zzDeviceAttribute.setIsDel("0");
			zzDeviceAttribute.setUpdateTime(FormatDateUtil.dataFormat(new Date())); // 更新日期
			int flag = zzDeviceAttributeMapper.insert(zzDeviceAttribute);
			if (flag > 0) {
				return new SysResult(ErrorUtil.CODE2000, "添加成功", zzDeviceAttribute);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "设备属性添加失败", null);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("设备属性添加失败，出现异常", e);
			throw new DoSqlFailedException("设备属性添加失败");
		}

	}

	/**
	 * 删除设备属性  
	 * 先根据(attribute_id)判断与之关联的设备属性关系表是否有isDel为0的数据
	 */
	@Override
	public SysResult delZZDeviceAttribute(ZZDeviceAttribute zzDeviceAttribute) {
		try {
			String attributeId = zzDeviceAttribute.getAttributeId(); // 得到设备信息的Id
			
			//根据(attribute_id)判断与之关联的设备属性关系表是否有isDel为0的数据
			int count=zzDeviceAttributeRelationMapper.getDeviceAttributeRelationByAttributeId(attributeId);
			if(count>0)
				return new SysResult(ErrorUtil.CODE2001, "设备属性关系表中有相关联的数据!",attributeId);
			
			zzDeviceAttribute.setIsDel("1"); // 删除
			zzDeviceAttribute.setUpdateTime(FormatDateUtil.dataFormat(new Date())); // 更新日期
			ZZDeviceAttributeExample example = new ZZDeviceAttributeExample();
			example.createCriteria().andAttributeIdEqualTo(attributeId);
			int result = zzDeviceAttributeMapper.updateByExampleSelective(zzDeviceAttribute, example);

			if (result > 0) {
				return new SysResult(ErrorUtil.CODE2000, "删除成功", zzDeviceAttribute);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "设备属性删除失败", null);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("设备属性删除失败，出现异常", e);
			throw new DoSqlFailedException("设备属性删除失败");
		}
	}

	@Override
	public SysResult updateZZDeviceAttribute(ZZDeviceAttribute zzDeviceAttribute) {
		try {

			String id = zzDeviceAttribute.getAttributeId(); // 得到设备信息的Id
			zzDeviceAttribute.setUpdateTime(FormatDateUtil.dataFormat(new Date())); // 更新日期
			ZZDeviceAttributeExample example = new ZZDeviceAttributeExample();
			example.createCriteria().andAttributeIdEqualTo(id);
			int result = zzDeviceAttributeMapper.updateByExampleSelective(zzDeviceAttribute, example);

			if (result > 0) {
				return new SysResult(ErrorUtil.CODE2000, "修改成功", zzDeviceAttribute);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "设备属性修改失败", null);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("设备属性修改失败，出现异常", e);
			throw new DoSqlFailedException("设备属性修改失败");
		}
	}

	@Override
	public SysResult selectById(String deviceAttributeId) {
		try {
			ZZDeviceAttributeExample example = new ZZDeviceAttributeExample();
			example.createCriteria().andAttributeIdEqualTo(deviceAttributeId);
			List<ZZDeviceAttribute> result = zzDeviceAttributeMapper.selectByExample(example);
			if (result != null) {
				return new SysResult(ErrorUtil.CODE2000, "查询成功", result);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "设备属性查询失败", null);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("设备属性查询失败，出现异常", e);
			throw new DoSqlFailedException("设备属性查询失败");
		}
	}

	// 分页查询的总数
	public int countDeviceAttribute(String deviceTypeId, String keyword) {
		if (null == deviceTypeId || deviceTypeId.equals("")) {
			if (null == keyword || keyword.equals("")) {
				return zzDeviceAttributeMapper.countDeviceAttribute(); // 当设备类型id，关键字都为空的时候，查找表中的所有个数
			}
			return zzDeviceAttributeMapper.countDeviceAttributeByKeyword(keyword); // 当设备类型Id为空，关键字不为空的时候，查找符合关键字的条数
		}
		if (null == keyword || keyword.equals("")) {
			return zzDeviceAttributeMapper.countDeviceAttributeByDeviceTypeId(deviceTypeId); // 当关键字为空，设备类型不为空的时，查找符合设备类型id的条数
		}
		// 当设备类型id，关键字都不为空时，查询符合双方条件的数量
		return zzDeviceAttributeMapper.countDeviceAttributeByDeviceTypeIddAndByKeyword(deviceTypeId, keyword);
	}

	@Override
	public SysResult listDeviceAttribute(String deviceTypeId, String keyword, Integer pageNo, Integer pageSize) {
		try {
			// 分页查询先查询结果总数
			int count = this.countDeviceAttribute(deviceTypeId, keyword);
			// 为pageNo和pageSize赋默认值
			if (null == pageNo || pageNo == 0)
				pageNo = 1;
			if (null == pageSize || pageSize == 0)
				pageSize = 11;
			// 封装到分页查询包装类中
			Integer rowNo = (pageNo - 1) * pageSize;
			List<ZZDeviceAttribute> list = listDeviceAttributeByPage(deviceTypeId, keyword, rowNo, pageSize);
			return new SysResult(ErrorUtil.CODE2000, "分页查询成功",
					new PageObject<ZZDeviceAttribute>(pageNo, pageSize, count, list));
		} catch (RuntimeException e) {
			logger.error("设备属性列表查询失败!", e);
			throw new DoSqlFailedException("设备属性列表查询失败,抛出异常", e);
		}
	}

	private List<ZZDeviceAttribute> listDeviceAttributeByPage(String deviceTypeId, String keyword, Integer rowNo,
			Integer pageSize) {
		if (null == deviceTypeId || deviceTypeId.equals("")) {
			if (null == keyword || keyword.equals("")) {
				List<ZZDeviceAttribute> list = zzDeviceAttributeMapper.listDeviceAttribute(rowNo, pageSize);
				logger.info(String.valueOf(list.size()));
				return list;
			}
			return zzDeviceAttributeMapper.listDeviceAttributeByKeyword(keyword, rowNo, pageSize);
		} else {
			if (null == keyword || keyword.equals("")) {
				List<ZZDeviceAttribute> list = zzDeviceAttributeMapper.listDeviceByDeviceTypeId(deviceTypeId, rowNo,
						pageSize);
				logger.info(String.valueOf(list.size()));
				return list;
			}
		}
		return zzDeviceAttributeMapper.listDeviceAttributeByDeviceTypeIdAndKeyword(deviceTypeId, keyword, rowNo,
				pageSize);
	}

	/**
	 * 对设备属性表进行批量删除  
	 * 循环调用单个删除设备属性
	 */
	public SysResult batDelDeviceAttribute(String ids) {
			
			try {
				String[] id=ids.split(",");			
				//将String类型数组转换为list集合
				List<String> idToList = Arrays.asList(id);
				for(String attributeId:idToList){
					ZZDeviceAttribute zzDeviceAttribute=zzDeviceAttributeMapper.selectDeviceAttribute(attributeId);
					SysResult sysResult=this.delZZDeviceAttribute(zzDeviceAttribute);
					if(sysResult.getCode().equals("2001"))
						return new SysResult(ErrorUtil.CODE2001, "批量删除设备属性失败  失败！！！", zzDeviceAttribute);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("批量删除设备属性列表失败!", e);
				throw new DoSqlFailedException("批量删除设备属性列表失败,抛出异常！！", e);
			}
			return new SysResult(ErrorUtil.CODE2000, "批量删除设备属性成功  成功", ids);		
	}
}
