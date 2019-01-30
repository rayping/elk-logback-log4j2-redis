
package com.yichuan.wuzhenpay.openapi.base.mvc.log.appender;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.impl.ThrowableProxy;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import lombok.Data;

@Data
public class JSONEventLayout {
	private String source;
	private String type;
	private String tags;
	private String sourceHost;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");

	public JSONEventLayout(String source, String type, String tags) {
		try {
			setTags(tags);
			setSourceHost(InetAddress.getLocalHost().getHostName());
			setSource(source);
			setType(type);
		} catch (Exception e) {
		}
	}

	public synchronized byte[] doLayout(LogEvent event) {
		Map<String, Object> map = Maps.newConcurrentMap();
		try {
			map.put("source", this.source);
			map.put("host", this.sourceHost);
			map.put("path", "-");
			map.put("type", this.type);
			map.put("tags", this.tags);
			map.put("message", event.getMessage().getFormattedMessage());
			map.put("logdate", this.df.format(new Date(event.getTimeMillis())));
			map.put("logger", event.getLoggerName());
			map.put("level", event.getLevel().toString());
			map.put("thread", event.getThreadName());
			map.put("threadId", event.getThreadId());

			ReadOnlyStringMap readOnlyStringMap = event.getContextData();
			if (null != readOnlyStringMap) {
				Map<String, String> stringMap = readOnlyStringMap.toMap();
				if (null != stringMap && !stringMap.isEmpty()) {
					map.putAll(stringMap);
				}
			}

			ThrowableProxy tp = event.getThrownProxy();
			if (tp != null) {
				Throwable throwable = tp.getThrowable();
				if (null != throwable) {
					String message = throwable.getMessage();
					map.put("throwable", message);
				}
			}

		} catch (Exception e) {
		}
		String jsonStr = JSONObject.toJSONString(map);
		return jsonStr.getBytes();
	}

}
