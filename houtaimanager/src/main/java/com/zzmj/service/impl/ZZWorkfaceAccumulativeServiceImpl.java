package com.zzmj.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZWorkfaceAccumulativeMapper;
import com.zzmj.pojo.entity.ZZWorkfaceAccumulative;
import com.zzmj.pojo.entity.ZZWorkfaceAccumulativeExample;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZWorkfaceAccumulativeService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.FormatDateUtil;
import com.zzmj.util.exception.DoSqlFailedException;

@Service
public class ZZWorkfaceAccumulativeServiceImpl implements ZZWorkfaceAccumulativeService {

	private static final Logger logger = LoggerFactory.getLogger(ZZUserServiceImpl.class);

	@Autowired
	private ZZWorkfaceAccumulativeMapper zzWorkfaceAccumulativeMapper;

	@Override
	public SysResult addZZWorkfaceAccumulative(ZZWorkfaceAccumulative zzWorkfaceAccumulative) {
		try {
			ZZWorkfaceAccumulative workfaceAccumulative = new ZZWorkfaceAccumulative();
			workfaceAccumulative.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
			int result = this.zzWorkfaceAccumulativeMapper.insert(workfaceAccumulative);
			if (result > 0) {
				return new SysResult(ErrorUtil.CODE2000, "新增工作面累积量成功", workfaceAccumulative);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "新增工作面累积量失败", null);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("新增工作面累积量失败，出现异常", e);
			throw new DoSqlFailedException("新增工作面累积量失败");
		}

	}

	@Override
	public SysResult delZZWorkfaceAccumulative(ZZWorkfaceAccumulative zzWorkfaceAccumulative) {
		return null;
	}

	/**
	 * 最主要的方法操作
	 */
	@Override
	public SysResult updateZZWorkfaceAccumulative(ZZWorkfaceAccumulative workfaceAccumulative) {
		try {
			// 工作面Id
			String workfaceId = workfaceAccumulative.getWorkfaceId();
			ZZWorkfaceAccumulativeExample example = new ZZWorkfaceAccumulativeExample();
			example.createCriteria().andWorkfaceIdEqualTo(workfaceId);
			int count = zzWorkfaceAccumulativeMapper.countByExample(example);
			workfaceAccumulative.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
			if (count < 1) {
				zzWorkfaceAccumulativeMapper.insertSelective(workfaceAccumulative);
			} else {
				zzWorkfaceAccumulativeMapper.updateByPrimaryKeySelective(workfaceAccumulative);
			}
			return new SysResult(ErrorUtil.CODE2000, "修改工作面累积量成功", workfaceAccumulative);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("修改工作面累积量失败，出现异常", e);
			throw new DoSqlFailedException("修改工作面累积量失败");
		}
	}

	@Override
	public SysResult selectById(String workfaceId) {
		ZZWorkfaceAccumulative zzWorkfaceAccumulative;
		try {
			// 工作面Id
			ZZWorkfaceAccumulativeExample example = new ZZWorkfaceAccumulativeExample();
			example.createCriteria().andWorkfaceIdEqualTo(workfaceId);
			int count = zzWorkfaceAccumulativeMapper.countByExample(example);
			if (count < 1) {
				ZZWorkfaceAccumulative workfaceAccumulative = new ZZWorkfaceAccumulative();
				workfaceAccumulative.setWorkfaceId(workfaceId);
				workfaceAccumulative.setWorkfaceAccuPropulsion("0");
				workfaceAccumulative.setWorkfaceCycleNumber("0");
				workfaceAccumulative.setWorkfaceExcessCoal("0");
				workfaceAccumulative.setWorkfaceRunningTime("0");
				workfaceAccumulative.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
				zzWorkfaceAccumulativeMapper.insertSelective(workfaceAccumulative);
			}
			zzWorkfaceAccumulative = this.zzWorkfaceAccumulativeMapper.selectByPrimaryKey(workfaceId);
			if (zzWorkfaceAccumulative == null)
				return new SysResult(ErrorUtil.CODE2001, "查找工作面累积量失败", null);
			return new SysResult(ErrorUtil.CODE2000, "查找工作面累积量成功", zzWorkfaceAccumulative);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("查找工作面累积量失败，出现异常", e);
			throw new DoSqlFailedException("查找工作面累积量失败");
		}
	}

}
