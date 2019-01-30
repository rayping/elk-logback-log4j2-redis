
package com.yichuan.wuzhenpay.openapi.base.mvc.log;

/**
 * 用户访问日志
 *
 * @version
 * @author leiyongping 2019年1月13日 下午5:14:38
 * 
 */
public class AccessLog {
	/**用户访问ip地址*/
	private String ip;
	/**用户访问url地址*/
	private String url;
	/**用户会话sid*/
	private String sid;
	/**用户id*/
	private String userId;
	/**用户ua信息*/
	private String ua;
	/**终端类型*/
	private String terminalType;
	/**终端型号*/
	private String terminalName;
	/**渠道号*/
	private String channelId;
	/**设备ID*/
	private String devicesId;
	/**访问时间*/
	private String accessTime;
	private String port;
	private String action;
	private boolean isSecure;
	
	private Integer httpStatus;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	public String getTerminalName() {
		return terminalName;
	}
	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getDevicesId() {
		return devicesId;
	}
	public void setDevicesId(String devicesId) {
		this.devicesId = devicesId;
	}
	
	public String getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public boolean isSecure() {
		return isSecure;
	}
	public void setSecure(boolean isSecure) {
		this.isSecure = isSecure;
	}
	/**
	 * @return the httpStatus
	 */
	public Integer getHttpStatus() {
		return httpStatus;
	}
	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
	@Override
	public String toString() {
		return "ip=" + ip + "`httpStatus=" + httpStatus + "`url=" + url + "`action="+action+"`ua=" + ua
				+ "`terminalType=" + terminalType + "`terminalName=" + terminalName + "`channelId=" + channelId
				+ "`devicesId=" + devicesId+"`accessTime=" + accessTime+"`port="+port;
	}		
	
}
