package com.zzmj.util;

import java.io.IOException;
import java.text.DateFormat;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.ser.FilterProvider;

public class Jackson2Utils {
	private static final Logger log = LoggerFactory.getLogger(Jackson2Utils.class);

	private final static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	/**
	 * 返回映射对象
	 * 
	 * @return
	 */
	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	/**
	 * List,Map,array,Object等转json
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error("json error:" + e.getMessage());
			return "";
		}

	}

	/**
	 * List,Map,array,Object等转json并格式化日期类型
	 * 
	 * @param object
	 * @param dateFormat日期类型
	 * @return
	 */
	public static String toJson(Object object, DateFormat dateFormat) {
		try {
			objectMapper.setDateFormat(dateFormat);
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error("json error:" + e.getMessage());
			return "";
		}
	}

	/*
	 * SimpleFilterProvider fileter = new SimpleFilterProvider();
	 * fileter.addFilter("executeFilter",
	 * SimpleBeanPropertyFilter.serializeAllExcept(new String[] { "quality" }));
	 * objectMapper.setFilters(fileter);
	 */
	/**
	 * List,Map,array,Object等转json并过滤掉不想转的字段
	 * 
	 * @param object
	 * @param filterProvider
	 * @return
	 */
	public static String toJson(Object object, FilterProvider filterProvider) {
		try {
			objectMapper.setFilters(filterProvider);
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error("json error:" + e.getMessage());
			return "";
		}
	}

	/**
	 * List,Map,array,Object等转json并过滤掉不想转的字段,并格式化日期
	 * 
	 * @param object
	 * @param dateFormat
	 * @param filterProvider
	 * @return
	 */
	public static String toJson(Object object, DateFormat dateFormat, FilterProvider filterProvider) {
		try {
			objectMapper.setDateFormat(dateFormat);
			objectMapper.setFilters(filterProvider);
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error("json error:" + e.getMessage());
			return "";
		}
	}

	/**
	 * json字符串转java对象
	 * 
	 * @param jsonString
	 * @param valueType
	 * @return
	 */
	public static <T> T json2Object(String jsonString, Class<T> valueType) {
		if (jsonString == null || "".equals(jsonString)) {
			return null;
		} else {
			try {
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return objectMapper.readValue(jsonString, valueType);
			} catch (Exception e) {
				log.error("json error:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * json字符串转java对象数组
	 * 
	 * @param jsonString
	 * @param valueType
	 * @return
	 */
	public static <T> T[] json2ArrayObject(String jsonString, Class<T[]> valueType) {
		if (jsonString == null || "".equals(jsonString)) {
			return null;
		} else {
			try {
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return objectMapper.readValue(jsonString, valueType);
			} catch (Exception e) {
				log.error("json error:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * json字符串转List对象
	 * 
	 * @param jsonString
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T json2ListObject(String jsonString, TypeReference<T> valueTypeRef) {
		if (jsonString == null || "".equals(jsonString)) {
			return null;
		} else {
			try {
				objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return (T) objectMapper.readValue(jsonString, valueTypeRef);
			} catch (Exception e) {
				log.error("json error:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * Json字符串转Map
	 * 
	 * @param jsonString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(String jsonString) {
		if (jsonString == null || "".equals(jsonString)) {
			return null;
		} else {
			try {
				return objectMapper.readValue(jsonString, Map.class);
			} catch (Exception e) {
				log.error("json error:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * Json字符串转List<Map>
	 * 
	 * @param jsonString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> json2ListMap(String jsonString) {
		if (jsonString == null || "".equals(jsonString)) {
			return null;
		} else {
			try {
				return objectMapper.readValue(jsonString, List.class);
			} catch (Exception e) {
				log.error("json error:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * 根据json中的节点名称取出对应节点
	 * 
	 * @param json
	 * @param nodeName
	 * @return
	 */
	public static JsonNode getNode(String json, String nodeName) {
		JsonNode node = null;
		try {
			node = Jackson2Utils.getObjectMapper().readTree(json);
			return node.get(nodeName);
		} catch (JsonProcessingException e) {
			log.error("json error:" + e.getMessage());
		} catch (IOException e) {
			log.error("json error:" + e.getMessage());
		}
		return node;
	}

	/**
	 * 根据JSON节点转List对象
	 * 
	 * @param node
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T jsonNode2ListObject(JsonNode node, TypeReference<T> valueTypeRef) {

		if (node == null || "".equals(node)) {
			return null;
		} else {
			try {
				objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				JsonParser jp = new TreeTraversingParser(node);
				T t = objectMapper.readValue(jp, valueTypeRef);
				jp.close();
				return t;
			} catch (Exception e) {
				log.warn("json error:" + e.getMessage());
			}
		}
		return null;
	}

}
