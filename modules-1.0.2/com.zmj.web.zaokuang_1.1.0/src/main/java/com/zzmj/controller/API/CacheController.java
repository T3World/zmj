package com.zzmj.controller.API;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.PosEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.util.CompareDate;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.HttpClientUtil;
import com.zzmj.util.StringUtil;
import com.zzmj.util.exception.CustomerException;

/**
 * 
 * @author hushixian 缓存controller
 *
 */
@RestController
@RequestMapping("/API/Cache")
public class CacheController {
	@Value("${zmj.cacheip}")
	private String CACHEIP; // 缓存
	@Value("${zmj.historyip}")
	private String HISTORYIP; // 历史
//	http://115.28.91.111:8020/GetMQTTTopicCache',

	// 获取缓存数据
	@RequestMapping("/getMQTTCacheData")
	@ResponseBody
	public SysResult getMQTTCacheData(HttpServletRequest request, HttpServletResponse httpResponse)
			throws CustomerException {
		SysResult result = new SysResult();
//		Map<String, Object> resMap = new HashMap<String, Object>();
		String TopicList = request.getParameter("TopicList");
//		String TopicList = "zaokuang/tengdong/3x111/Support/Pressure1";
		if (StringUtil.isEmpty(TopicList)) {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("TopicList不能为空！");
			return result;
		}
		List<Map<String, Object>> listMaps = HttpClientUtil.getSynchrRadisData(CACHEIP + "/GetMQTTTopicCache",
				TopicList);
		if (listMaps == null) {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("没有缓存数据");
		} else {
			result.setCode(ErrorUtil.CODE2000);
			result.setErrorMsg("调用成功");
			result.setData(listMaps);
		}

		return result;
	}

	/**
	 * 根据主题，获取煤机轨迹缓存
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getShearerPosCacheData")
	@ResponseBody
	public SysResult getShearerPosCacheData(HttpServletRequest request, HttpServletResponse response) {

		SysResult result = new SysResult();
		// 获取前端传递过来的主题列表
		String TopicList = request.getParameter("TopicList");
		List<HashMap<String, Object>> posList = new ArrayList<HashMap<String, Object>>();

		List<PosEntity> runList = new ArrayList<PosEntity>();
		// 根据主题列表去,去MQTT服务器,获得缓存数据的列表
		List<Map<String, Object>> listMaps = HttpClientUtil.getSynchrRadisData(CACHEIP + "/GetMQTTTopicCache",
				TopicList);
		if (listMaps.size() > 0) {
			for (int i = 0; i < listMaps.size(); i++) {
				// 获得数据源，进行转换。
				posList.addAll((List<HashMap<String, Object>>) listMaps.get(i).get("TopicCache"));
			}
			// 获取Time，Value，的值，进行实体类的转化。
			for (int i = 0; i < posList.size(); i++) {
				PosEntity posEntity = new PosEntity();
				posEntity.setTime(posList.get(i).get("Time").toString());
				posEntity.setValue(posList.get(i).get("Value"));
				runList.add(posEntity);
			}
			// 对日期类型的数据进行排序。
			Collections.sort(runList, new Comparator<PosEntity>() {
				@Override
				public int compare(PosEntity o1, PosEntity o2) {
					return CompareDate.compare(o1.getTime().toString(), o2.getTime().toString());
				}
			});
			result.setCode(ErrorUtil.CODE2000);
			result.setErrorMsg("调用成功");
			result.setData(runList);
		} else {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("没有缓存数据");
		}
		return result;

	}

	/**
	 * 支架压力历史查询接口
	 * 
	 * @param request
	 * @param httpResponse
	 * @return
	 * @throws CustomerException
	 */
	@RequestMapping("/GetSingleSupportPressure")
	@ResponseBody
	public SysResult GetSingleSupportPressure(HttpServletRequest request, HttpServletResponse httpResponse)
			throws CustomerException {
		SysResult result = new SysResult();

		String startTime = request.getParameter("startTime");
//		String startTime = "2018-12-21 08:00:00 ";
		String endTime = request.getParameter("endTime");
//		String endTime = "2018-12-24 08:00:00 ";
		String scuNo = request.getParameter("scuNo");
//		String scuNo = "6";
		String DataSource = request.getParameter("dataSource");
//		String DataSource = "zaokuang/qiwu/217";
		String PressureType = request.getParameter("pressureType");
//		String PressureType = "0";
		if (StringUtil.isEmpty(startTime)) {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("startTime不能为空！");
		}
		if (StringUtil.isEmpty(endTime)) {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("endTime不能为空！");
		}
		if (StringUtil.isEmpty(scuNo)) {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("scuNo不能为空！");
		}
		if (StringUtil.isEmpty(DataSource)) {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("dataSource不能为空！");
		}
		if (StringUtil.isEmpty(PressureType)) {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("pressureType不能为空！");
		}
		List<Map<String, Object>> listMaps = HttpClientUtil.getPressHistoryData(HISTORYIP + "/GetSingleSupportPressure",
				DataSource, scuNo, PressureType, startTime, endTime);
		if (listMaps.size() > 0) {
			result.setCode(ErrorUtil.CODE2000);
			result.setErrorMsg("调用成功");
			result.setData(listMaps);
		} else {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("暂无历史数据");
		}
		return result;
	}

	// 煤机轨迹历史查询接口
	@RequestMapping("/getCoalHistoryData")
	@ResponseBody
	public SysResult getCoalHistoryData(HttpServletRequest request, HttpServletResponse httpResponse) {
		SysResult result = new SysResult();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String DataSource = request.getParameter("dataSource");
//		String startTime = "2018-12-23 08:00:00 ";
//		String endTime = "2018-12-24 08:00:00 ";
//		String DataSource = "zaokuang/qiwu/217";

		List<Map<String, Object>> posList = new ArrayList<Map<String, Object>>();
		List<PosEntity> dataList = new ArrayList<PosEntity>();

		if (StringUtil.isEmpty(startTime)) {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("startTime不能为空！");
		}
		if (StringUtil.isEmpty(endTime)) {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("endTime不能为空！");
		}
		if (StringUtil.isEmpty(DataSource)) {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("dataSource不能为空！");
		}
		List<Map<String, Object>> getSupportList = HttpClientUtil
				.getCoalHistoryData(HISTORYIP + "/GetSupportAutoRunningState", DataSource, startTime, endTime);
		List<Map<String, Object>> getShearHistoryList = HttpClientUtil
				.getCoalHistoryData(HISTORYIP + "/GetHistoryShearerPos", DataSource, startTime, endTime);

		if (getSupportList != null || getShearHistoryList != null) {

			if (getSupportList == null) {

			} else {
				// 获得数据源，进行转换。
				posList.addAll(getSupportList);
			}
			if (getShearHistoryList != null) {
				// 获得数据源，进行转换。
				posList.addAll(getShearHistoryList);
			} else {
			}

			// 获取Time，Value，的值，进行实体类的转化。
			for (int i = 0; i < posList.size(); i++) {
				PosEntity posEntity = new PosEntity();
				posEntity.setTime(posList.get(i).get("Time").toString());
				posEntity.setValue(posList.get(i).get("Value"));
				dataList.add(posEntity);
			}
			// 对日期类型的数据进行排序。
			Collections.sort(dataList, new Comparator<PosEntity>() {
				@Override
				public int compare(PosEntity o1, PosEntity o2) {
					return CompareDate.compare(o1.getTime().toString(), o2.getTime().toString());
				}
			});
			result.setCode(ErrorUtil.CODE2000);
			result.setErrorMsg("调用成功");
			result.setData(dataList);
		} else {
			result.setCode(ErrorUtil.CODE2001);
			result.setErrorMsg("失败");
		}
		return result;
	}

}
