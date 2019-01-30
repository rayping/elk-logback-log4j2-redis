
package com.wuzhenpay.payment.utils.gwslogs.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

public class GwsLoggerFilter extends AbstractMatcherFilter<ILoggingEvent> {

	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (!isStarted()) {
			return FilterReply.NEUTRAL;
		}
		return null;
	}

}
