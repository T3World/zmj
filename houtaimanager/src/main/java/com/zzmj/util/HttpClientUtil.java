package com.zzmj.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

/**
 * 
 * Module: HttpClientUtil.java Description:
 * 以get/post的方式发送数据到指定的http接口---利用httpclient.jar包---HTTP接口的调用
 */

public class HttpClientUtil {

	private static final Log log = LogFactory.getLog(HttpClientUtil.class);

	/**
	 * get方式
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static String getHttp(String param1, String param2) {
		String responseMsg = "";
		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 用于测试的http接口的url
		String url = "http://localhost:8080/UpDown/httpServer?param1=" + param1 + "&param2=" + param2;
		// 2.创建GetMethod的实例
		GetMethod getMethod = new GetMethod(url);
		// 使用系统系统的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			// 3.执行getMethod,调用http接口
			httpClient.executeMethod(getMethod);
			// 4.读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 5.处理返回的内容
			responseMsg = new String(responseBody);
			log.info(responseMsg);

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 6.释放连接
			getMethod.releaseConnection();
		}
		return responseMsg;
	}

	/**
	 * post方式
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static String postHttp(String userName, String passWord) {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		// String url="http://115.28.91.111:8010/API/BaseData/getProvince";
		String url = "http://192.168.3.31:8080/appLogin/login";
		// 2.构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);
		// 3.把参数值放入到PostMethod对象中
		// 方式1：
		/*
		 * NameValuePair[] data = { new NameValuePair("param1", param1), new
		 * NameValuePair("param2", param2) }; postMethod.setRequestBody(data);
		 */

		// 方式2：
		postMethod.addParameter("X-Username", userName);
		postMethod.addParameter("X-Password", passWord);

		try {
			// 4.执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);// 200

			// 5.读取内容
			responseMsg = postMethod.getResponseBodyAsString().trim();
			log.info(responseMsg);
			// 6.处理返回的内容
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 7.释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
	}

	/**
	 * post方式
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static List<Map<String, Object>> postHttpData(String url) {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");

		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		// String url="http://115.28.91.111:8010/API/BaseData/getProvince";
		// String url="http://115.28.91.111:8010/API/BaseData/getProvince";
		// 2.构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);
		// 3.把参数值放入到PostMethod对象中

		// 方式2：
		postMethod.addParameter("Token",
				"96a792e485d7fcfc47ea0a6dbfa64cd7-5b4203303ccc211da49c3d2d04c5aa7f-c0d2605d7ef868a654d6e499ab2b80d4");
		postMethod.addParameter("PageNo", "0");
		postMethod.addParameter("PageSize", "0");
		try {
			// 4.执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);// 200

			// 5.读取内容
			responseMsg = postMethod.getResponseBodyAsString().trim();
			String ss = responseMsg.substring(22, responseMsg.length() - 1);
			ObjectMapper om = new ObjectMapper();
			JsonNode node = om.readTree(ss);
			listMaps = Jackson2Utils.json2ListMap(node.toString());
			// 6.处理返回的内容
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 7.释放连接
			postMethod.releaseConnection();
		}
		return listMaps;
	}

	/**
	 * post方式 获取郑煤机图表初始化缓存数据
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static List<Map<String, Object>> postHttpGetCacheData(String url, String TopicList) {
		String responseMsg = "";
		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		PostMethod postMethod = new PostMethod(url);
		// 3.把参数值放入到PostMethod对象中
		postMethod.addParameter("Token",
				"96a792e485d7fcfc47ea0a6dbfa64cd7-5b4203303ccc211da49c3d2d04c5aa7f-c0d2605d7ef868a654d6e499ab2b80d4");
		postMethod.addParameter("TopicList", TopicList);
		try {
			// 4.执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);// 200
			// 5.读取内容
			responseMsg = postMethod.getResponseBodyAsString().trim();
			Map<String, Object> json = Jackson2Utils.json2Map(responseMsg);
			listMaps.add(json);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 7.释放连接
			postMethod.releaseConnection();
		}
		return listMaps;
	}

	public static List<Map<String, Object>> postHttpData(String url, String groupId) {
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("Token",
				"96a792e485d7fcfc47ea0a6dbfa64cd7-5b4203303ccc211da49c3d2d04c5aa7f-c0d2605d7ef868a654d6e499ab2b80d4");
		postMethod.addParameter("Group_Id", groupId);
		try {
			httpClient.executeMethod(postMethod);// 200
			responseMsg = postMethod.getResponseBodyAsString().trim();
			String ss = responseMsg.substring(22, responseMsg.length() - 1);
			ObjectMapper om = new ObjectMapper();
			JsonNode node = om.readTree(ss);
			listMaps = Jackson2Utils.json2ListMap(node.toString());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return listMaps;
	}

	public static List<Map<String, Object>> postHttpData2(String url, String Cmp_Id) {
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("Token",
				"96a792e485d7fcfc47ea0a6dbfa64cd7-5b4203303ccc211da49c3d2d04c5aa7f-c0d2605d7ef868a654d6e499ab2b80d4");
		postMethod.addParameter("Cmp_Id", Cmp_Id);
		try {
			httpClient.executeMethod(postMethod);// 200
			responseMsg = postMethod.getResponseBodyAsString().trim();
			String ss = responseMsg.substring(22, responseMsg.length() - 1);
			ObjectMapper om = new ObjectMapper();
			JsonNode node = om.readTree(ss);
			listMaps = Jackson2Utils.json2ListMap(node.toString());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return listMaps;
	}

	/**
	 * 同步数据库mqtt user
	 * 
	 * @param args
	 */
	public static List<Map<String, Object>> postHttpDataSynMqttUser(String url) {
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("Token",
				"96a792e485d7fcfc47ea0a6dbfa64cd7-5b4203303ccc211da49c3d2d04c5aa7f-c0d2605d7ef868a654d6e499ab2b80d4");
		postMethod.addParameter("PageNo", "0");
		postMethod.addParameter("PageSize", "0");
		try {
			httpClient.executeMethod(postMethod);// 200
			responseMsg = postMethod.getResponseBodyAsString().trim();
			if (true == (boolean) JSONObject.fromObject(responseMsg).get("Result")) {
				ObjectMapper om = new ObjectMapper();
				if (JSONObject.fromObject(responseMsg).get("Data") != null) {
					JsonNode node = om.readTree(JSONObject.fromObject(responseMsg).get("Data").toString());
					listMaps = Jackson2Utils.json2ListMap(node.toString());
				}
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return listMaps;
	}

	/**
	 * 同步数据库mqtt user对应的数据库列表
	 * 
	 * @param args
	 */
	public static List<Map<String, Object>> postHttpDataSynMqttDataSource(String url, String client_Id) {
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("Token",
				"96a792e485d7fcfc47ea0a6dbfa64cd7-5b4203303ccc211da49c3d2d04c5aa7f-c0d2605d7ef868a654d6e499ab2b80d4");
		postMethod.addParameter("Client_Id", client_Id);
		try {
			httpClient.executeMethod(postMethod);// 200
			responseMsg = postMethod.getResponseBodyAsString().trim();
			if (true == (boolean) JSONObject.fromObject(responseMsg).get("Result")) {
				ObjectMapper om = new ObjectMapper();
				JsonNode node = om.readTree(JSONObject.fromObject(responseMsg).get("Data").toString());
				listMaps = Jackson2Utils.json2ListMap(node.toString());
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return listMaps;
	}

	/**
	 * 根据对应的主题列表获取缓存数据
	 * 
	 * @param args
	 */
	public static List<Map<String, Object>> getSynchrRadisData(String url, String TopicList) {
		String responseMsg = "";
		String ss = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		PostMethod postMethod = new PostMethod(url);
//		postMethod.addParameter("Token",
//				"96a792e485d7fcfc47ea0a6dbfa64cd7-5b4203303ccc211da49c3d2d04c5aa7f-c0d2605d7ef868a654d6e499ab2b80d4");
		postMethod.addParameter("TopicList", TopicList);
		try {
			httpClient.executeMethod(postMethod);// 200
			responseMsg = postMethod.getResponseBodyAsString().trim();
			if (true == (boolean) JSONObject.fromObject(responseMsg).get("Result")) {
				ObjectMapper om = new ObjectMapper();
				JsonNode node = om.readTree(JSONObject.fromObject(responseMsg).get("Data").toString());
				listMaps = Jackson2Utils.json2ListMap(node.toString());
			} else {
				listMaps = null;
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return listMaps;
	}

	/**
	 * 按照条件查询支架压力历史数据
	 * 
	 * @param args
	 */
	public static List<Map<String, Object>> getPressHistoryData(String url, String DataSource, String ScuNo,
			String PressureType, String StartTime, String StopTime) {
		String responseMsg = "";
		String ss = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		PostMethod postMethod = new PostMethod(url);
//		postMethod.addParameter("Token",
//				"96a792e485d7fcfc47ea0a6dbfa64cd7-5b4203303ccc211da49c3d2d04c5aa7f-c0d2605d7ef868a654d6e499ab2b80d4");
		postMethod.addParameter("DataSource", DataSource);
		postMethod.addParameter("ScuNo", ScuNo);
		postMethod.addParameter("PressureType", PressureType);
		postMethod.addParameter("StartTime", StartTime);
		postMethod.addParameter("StopTime", StopTime);
		try {
			httpClient.executeMethod(postMethod);// 200

			responseMsg = postMethod.getResponseBodyAsString().trim();
			if (true == (boolean) JSONObject.fromObject(responseMsg).get("Result")) {
				ObjectMapper om = new ObjectMapper();
				if (JSONObject.fromObject(responseMsg).get("Data") != null) {
					JsonNode node = om.readTree(JSONObject.fromObject(responseMsg).get("Data").toString());
					listMaps = Jackson2Utils.json2ListMap(node.toString());
				}
			} /*
				 * else{ Map<String, Object> json = Jackson2Utils.json2Map(responseMsg);
				 * listMaps.add(json); }
				 */

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return listMaps;
	}

	/**
	 * 按照条件查询采煤机轨迹历史数据
	 * 
	 * @param args
	 */
	public static List<Map<String, Object>> getCoalHistoryData(String url, String DataSource, String StartTime,
			String StopTime) {
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("DataSource", DataSource);
		postMethod.addParameter("StartTime", StartTime);
		postMethod.addParameter("StopTime", StopTime);
		try {
//			httpClient.executeMethod(postMethod);// 200
//			responseMsg = postMethod.getResponseBodyAsString().trim();
//			if (true == (boolean) JSONObject.fromObject(responseMsg).get("Result")) {
//				ObjectMapper om = new ObjectMapper();
//				if (JSONObject.fromObject(responseMsg).get("Data") != null) {
//					JsonNode node = om.readTree(JSONObject.fromObject(responseMsg).get("Data").toString());
//					listMaps = Jackson2Utils.json2ListMap(node.toString());
//				}
//			}

			httpClient.executeMethod(postMethod);// 200
			responseMsg = postMethod.getResponseBodyAsString().trim();
			if (true == (boolean) JSONObject.fromObject(responseMsg).get("Result")) {
				ObjectMapper om = new ObjectMapper();
				System.out.println();
				if (JSONObject.fromObject(responseMsg).get("Data") == null) {
					listMaps = null;
				} else {
					JsonNode node = om.readTree(JSONObject.fromObject(responseMsg).get("Data").toString());
					listMaps = Jackson2Utils.json2ListMap(node.toString());
				}
			} else {
				listMaps = null;
			}

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return listMaps;
	}

	/**
	 * 测试的main方法
	 * 
	 * @param args
	 */
	public static void main1(String[] args) {
		String userName = "test";
		String passWord = "zz123456";
		System.out.println("post方式调用http接口\n" + postHttp(userName, passWord));
	}

//	public static void main(String[] args) {
//		String key = "27ef49d2e5a042f283c439cde49edfb4";
//		String token = "";
//		String responseMsg = "";
//		HttpClient httpClient = new HttpClient();
//		httpClient.getParams().setContentCharset("UTF-8");
//		// String url="http://120.27.16.188:8090/pub/queryAllColorParam";
//		String url = "http://47.104.25.241:8090/pub/queryAllColorParam";
//		// 2.构造PostMethod的实例
//		PostMethod postMethod = new PostMethod(url);
//		// 3.把参数值放入到PostMethod对象中
//		// 方式2：
//		try {
//			token = AESNew.AES_Encode("/pub/queryAllColorParam.shtml-" + new Date().getTime(), key);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		// postMethod.addParameter("token", token);
//		try {
//			// 4.执行postMethod,调用http接口
//			httpClient.executeMethod(postMethod);// 200
//
//			// 5.读取内容
//			responseMsg = postMethod.getResponseBodyAsString().trim();
//			log.info("=========" + responseMsg);
//			// 6.处理返回的内容
//		} catch (HttpException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			// 7.释放连接
//			postMethod.releaseConnection();
//		}
//
//	}

	// 用户登录login
	public static Map getLoginTest(String url, String userName, String userPass) {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");

		Map<String, Object> listMaps = new HashMap<String, Object>();
		// String url="http://115.28.91.111:8010/API/BaseData/getProvince";
		// String url="http://115.28.91.111:8010/API/BaseData/getProvince";
		// 2.构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);
		// 3.把参数值放入到PostMethod对象中
		// 方式2：
		postMethod.addParameter("Token",
				"96a792e485d7fcfc47ea0a6dbfa64cd7-5b4203303ccc211da49c3d2d04c5aa7f-c0d2605d7ef868a654d6e499ab2b80d4");
		postMethod.addParameter("User_Name", userName);
		postMethod.addParameter("User_Password", userPass);
		try {
//    // 4.执行postMethod,调用http接口
//      httpClient.executeMethod(postMethod);//200
//      //5.读取内容
//		responseMsg = postMethod.getResponseBodyAsString().trim();
//		String ss = responseMsg.substring(22, responseMsg.length()-1);
//		ObjectMapper om = new ObjectMapper();  
//		JsonNode node=om.readTree(ss); 
//		listMaps = Jackson2Utils.json2ListMap(node.toString());
//       //6.处理返回的内容
			httpClient.executeMethod(postMethod);// 200
			responseMsg = postMethod.getResponseBodyAsString().trim();
			if (true == (boolean) JSONObject.fromObject(responseMsg).get("Result")) {
				ObjectMapper om = new ObjectMapper();
				if (JSONObject.fromObject(responseMsg).get("Data") != null) {
					JsonNode node = om.readTree(JSONObject.fromObject(responseMsg).get("Data").toString());
					listMaps = Jackson2Utils.json2Map(node.toString());
				}
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 7.释放连接
			postMethod.releaseConnection();
		}
		return listMaps;
	}

}
