package com.meng.testspringboot2.config;

import com.meng.testspringboot2.ApiException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class GlobalErrorAttributes  extends DefaultErrorAttributes {

	private static final Logger logger = LoggerFactory.getLogger(GlobalErrorAttributes.class);

	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

		long timeStamp = System.currentTimeMillis();

		Throwable throwable = getError(request);
		logger.error("timeStamp: {}, error: ", timeStamp, throwable);

		Map<String, Object> map = new HashMap<>();

		if(throwable instanceof ApiException){
			ApiException apiException = (ApiException) throwable;

			map.put("code", apiException.getErrCode());
			map.put("msg", apiException.getErrMsg());

			return map;
		}
		else {
			map.put("code", HttpStatus.BAD_REQUEST);
			map.put("message", "未知错误: " + timeStamp);

			return map;
		}
	}

}