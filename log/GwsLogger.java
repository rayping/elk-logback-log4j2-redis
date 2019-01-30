
package com.yichuan.wuzhenpay.openapi.base.mvc.log;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yichuan.wuzhenpay.openapi.utils.message.FormattedMessage;

/**
 * 全局日志类
 *
 * @author leiyongping 2018年11月6日 上午10:55:85
 */
public final class GwsLogger {
	public static final String GWS_LOG = "GWS";
	public static final String CON_QUOTE = "`";

	// 定义基础的Log日志
	private static final Logger gwslog = LogManager.getLogger(GWS_LOG);

	public static ThreadLocal<AccessLog> accessLog = new ThreadLocal<AccessLog>();

	private static ConcurrentHashMap<GwsLoggerTypeEnum, Logger> logMaps = new ConcurrentHashMap<>();

	/**
	 *
	 * 全局日志logger调用
	 *
	 * @author leiyongping 2018年4月19日
	 * @param loggerType
	 * @return
	 */
	public static Logger getLogger(GwsLoggerTypeEnum loggerType) {
		Logger logger = logMaps.get(loggerType);
		if (logger == null) {
			// 发现未定义的日志发现未创建，则自动创建，只需在枚举类创建即可
			logMaps.put(loggerType, LogManager.getLogger(loggerType));
		}
		return logger;
	}

	/**
	 *
	 * 全局日志默认GWS枚举
	 *
	 * @author leiyongping 2018年4月19日
	 * @return
	 */
	public static Logger getLogger() {
		return logMaps.get(GwsLoggerTypeEnum.GWS);
	}

	/**
	 *
	 * 输出已定义日志类别的info日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param loggerType
	 * @param msg
	 * @param args
	 */
	public static void info(GwsLoggerTypeEnum loggerType, String msg, Object... args) {
		try {
			Logger logger = getLogger(loggerType);
			msg = addImportantToLog(msg);
			logger.info(msg, args);
		} catch (Exception e) {
		}
	}

	/**
	 *
	 * 输出已定义日志类别的info日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param loggerType
	 * @param throwable
	 * @param msg
	 * @param args
	 */
	public static void info(GwsLoggerTypeEnum loggerType, Throwable throwable, String msg, Object... args) {
		Logger logger = getLogger(loggerType);
		FormattedMessage message = new FormattedMessage(msg, args);
		String formattedMessage = message.getFormattedMessage();
		String importFormattedMessage = addImportantToLog(formattedMessage);
		logger.info(importFormattedMessage, throwable);
	}

	/**
	 *
	 * 输出默认的GWS类别info日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param msg
	 * @param args
	 */
	public static void info(String msg, Object... args) {
		Logger logger = getLogger(GwsLoggerTypeEnum.GWS);
		msg = addImportantToLog(msg);
		logger.info(msg, args);
	}

	/**
	 *
	 * 输出默认的GWS类别info日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param throwable
	 * @param msg
	 * @param args
	 */
	public static void info(Throwable throwable, String msg, Object... args) {
		Logger logger = getLogger();
		FormattedMessage message = new FormattedMessage(msg, args);
		String formattedMessage = message.getFormattedMessage();
		String importFormattedMessage = addImportantToLog(formattedMessage);
		logger.info(importFormattedMessage, throwable);
	}

	/**
	 *
	 * 输出已定义日志类别的info日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param loggerType
	 * @param msg
	 * @param args
	 */
	public static void debug(GwsLoggerTypeEnum loggerType, String msg, Object... args) {
		try {
			Logger logger = getLogger(loggerType);
			msg = addImportantToLog(msg);
			logger.debug(msg, args);
		} catch (Exception e) {
		}
	}

	/**
	 *
	 * 输出默认的GWS类别debug日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param msg
	 * @param args
	 */
	public static void debug(String msg, Object... args) {
		Logger logger = getLogger();
		msg = addImportantToLog(msg);
		logger.debug(msg, args);
	}

	/**
	 *
	 * 输出默认GWS日志类别的debug日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param throwable
	 * @param msg
	 * @param args
	 */
	public static void debug(Throwable throwable, String msg, Object... args) {
		Logger logger = getLogger();
		FormattedMessage message = new FormattedMessage(msg, args);
		String formattedMessage = message.getFormattedMessage();
		String importFormattedMessage = addImportantToLog(formattedMessage);
		logger.debug(importFormattedMessage, throwable);
	}

	/**
	 *
	 * 输出已定义日志类别的info日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param loggerType
	 * @param msg
	 * @param args
	 */
	public static void warn(GwsLoggerTypeEnum loggerType, String msg, Object... args) {
		try {
			Logger logger = getLogger(loggerType);
			msg = addImportantToLog(msg);
			logger.warn(msg, args);
		} catch (Exception e) {
		}
	}

	/**
	 *
	 * 输出默认的GWS类别warn日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param msg
	 * @param args
	 */
	public static void warn(String msg, Object... args) {
		Logger logger = getLogger();
		msg = addImportantToLog(msg);
		logger.warn(msg, args);
	}

	/**
	 *
	 * 输出默认GWS日志类别的warn日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param throwable
	 * @param msg
	 * @param args
	 */
	public static void warn(Throwable throwable, String msg, Object... args) {
		Logger logger = getLogger();
		FormattedMessage message = new FormattedMessage(msg, args);
		String formattedMessage = message.getFormattedMessage();
		String importFormattedMessage = addImportantToLog(formattedMessage);
		logger.warn(importFormattedMessage, throwable);
	}

	/**
	 *
	 * 输出默认GWS日志类别的错误日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param msg
	 * @param args
	 */
	public static void error(String msg, Object... args) {
		Logger logger = getLogger();
		msg = addImportantToLog(msg);
		logger.error(msg, args);
	}

	/**
	 *
	 * 输出默认GWS日志类别的错误日志
	 *
	 * @author leiyongping 2018年4月20日
	 * @param throwable
	 * @param msg
	 * @param args
	 */
	public static void error(Throwable throwable, String msg, Object... args) {
		Logger logger = getLogger();
		FormattedMessage message = new FormattedMessage(msg, args);
		String formattedMessage = message.getFormattedMessage();
		String importFormattedMessage = addImportantToLog(formattedMessage);
		logger.error(importFormattedMessage, throwable);
	}


	/**
	 *
	 * 增加重要信息到日志中
	 *
	 * @author leiyongping 2018年4月20日
	 * @param msg
	 * @return
	 */
	private static String addImportantToLog(String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		AccessLog threadData = GwsLogger.accessLog.get();
		if (null != threadData) {
			if (!msg.endsWith(GwsLogger.CON_QUOTE)) {
				sb.append(GwsLogger.CON_QUOTE);
			}

			sb.append(String.format("action=%s`sid=%s`remoteIp=%s`", threadData.getAction(), threadData.getSid(),
					threadData.getIp()));
		}
		return sb.toString();
	}

	static {
		logMaps.put(GwsLoggerTypeEnum.GWS, gwslog);
	}
}