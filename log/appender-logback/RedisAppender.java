
package com.wuzhenpay.payment.utils.gwslogs.appender;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.wuzhenpay.payment.utils.gwslogs.GwsLoggerTypeEnum;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
	private JedisPool pool;
	private JSONEventLayout jsonlayout;
	private Layout<ILoggingEvent> layout;
	private String host = "localhost";
	private int port = 6379;
	private String key = null;
	private int timeout = 5000;
	private String password = null;
	private int database = 0;
	private boolean initRedis = false;

	public RedisAppender() {
		this.jsonlayout = new JSONEventLayout();
	}

	@Override
	protected void append(ILoggingEvent event) {
		String loggerName = event.getLoggerName();
		if (StringUtils.isNotBlank(loggerName)) {
			if (GwsLoggerTypeEnum.GWS.name().equals(loggerName) || GwsLoggerTypeEnum.SQLTRACE.name().equals(loggerName)
					|| (event.getLevel().levelStr.equals(Level.DEBUG.levelStr)
							&& loggerName.toLowerCase().contains("mapper"))) {
				Jedis client = this.pool.getResource();
				try {
					String json = this.layout == null ? this.jsonlayout.doLayout(event) : this.layout.doLayout(event);
					client.rpush(this.key, new String[] { json });
				} catch (Exception e) {
					client.close();
					client = null;
				} finally {
					if (client != null) {
						client.close();
					}
				}
			}
		}
	}

	@Deprecated
	public String getSource() {
		return this.jsonlayout.getSource();
	}

	@Deprecated
	public void setSource(String source) {
		this.jsonlayout.setSource(source);
	}

	@Deprecated
	public String getSourceHost() {
		return this.jsonlayout.getSourceHost();
	}

	@Deprecated
	public void setSourceHost(String sourceHost) {
		this.jsonlayout.setSourceHost(sourceHost);
	}

	@Deprecated
	public String getSourcePath() {
		return this.jsonlayout.getSourcePath();
	}

	@Deprecated
	public void setSourcePath(String sourcePath) {
		this.jsonlayout.setSourcePath(sourcePath);
	}

	@Deprecated
	public String getTags() {
		if (this.jsonlayout.getTags() != null) {
			Iterator<String> i = this.jsonlayout.getTags().iterator();
			StringBuilder sb = new StringBuilder();
			while (i.hasNext()) {
				sb.append(i.next());
				if (i.hasNext()) {
					sb.append(',');
				}
			}
			return sb.toString();
		}
		return null;
	}

	@Deprecated
	public void setTags(String tags) {
		if (tags != null) {
			String[] atags = tags.split(",");
			this.jsonlayout.setTags(Arrays.asList(atags));
		}
	}

	@Deprecated
	public String getType() {
		return this.jsonlayout.getType();
	}

	@Deprecated
	public void setType(String type) {
		this.jsonlayout.setType(type);
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getTimeout() {
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDatabase() {
		return this.database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	@Deprecated
	public void setMdc(boolean flag) {
		this.jsonlayout.setProperties(flag);
	}

	@Deprecated
	public boolean getMdc() {
		return this.jsonlayout.getProperties();
	}

	@Deprecated
	public void setLocation(boolean flag) {
		this.jsonlayout.setLocationInfo(flag);
	}

	@Deprecated
	public boolean getLocation() {
		return this.jsonlayout.getLocationInfo();
	}

	@Deprecated
	public void setCallerStackIndex(int index) {
		this.jsonlayout.setCallerStackIdx(index);
	}

	@Deprecated
	public int getCallerStackIndex() {
		return this.jsonlayout.getCallerStackIdx();
	}

	@Deprecated
	public void addAdditionalField(AdditionalField p) {
		this.jsonlayout.addAdditionalField(p);
	}

	public Layout<ILoggingEvent> getLayout() {
		return this.layout;
	}

	public void setLayout(Layout<ILoggingEvent> layout) {
		this.layout = layout;
	}

	@Override
	public void start() {
		if (initRedis) {
			super.start();
			GenericObjectPoolConfig config = new GenericObjectPoolConfig();
			config.setTestOnBorrow(true);
			this.pool = new JedisPool(config, this.host, this.port, this.timeout, this.password, this.database);
		}
	}

	@Override
	public void stop() {
		if (null != this.pool) {
			super.stop();
			this.pool.destroy();
		}
	}

	public boolean isInitRedis() {
		return initRedis;
	}

	public void setInitRedis(boolean initRedis) {
		this.initRedis = initRedis;
	}
}
