package com.yichuan.wuzhenpay.openapi.base.mvc.log.appender;

enum Helpers {;

    static void requireArgument(boolean condition, String messageFormat, Object... messageArguments) {
        if (!condition) {
            String message = String.format(messageFormat, messageArguments);
            throw new IllegalArgumentException(message);
        }
    }

}
