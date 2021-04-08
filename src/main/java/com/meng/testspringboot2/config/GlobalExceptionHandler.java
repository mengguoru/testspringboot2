package com.meng.testspringboot2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class GlobalExceptionHandler  extends AbstractErrorWebExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private ObjectMapper springMvcJacksonConverter = Jackson2ObjectMapperBuilder.json().build();

	public GlobalExceptionHandler(GlobalErrorAttributes g, ApplicationContext applicationContext,
										  ServerCodecConfigurer serverCodecConfigurer) {
		super(g, new WebProperties.Resources(), applicationContext);
		super.setMessageWriters(serverCodecConfigurer.getWriters());
		super.setMessageReaders(serverCodecConfigurer.getReaders());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {

		final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());

		return ServerResponse.status(HttpStatus.BAD_REQUEST)
				   .contentType(MediaType.APPLICATION_JSON)
				   .body(BodyInserters.fromValue(errorPropertiesMap));
	}

//	@Override
//	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//
//		ServerHttpResponse response = exchange.getResponse();
//		if (response.isCommitted()) {
//			return Mono.error(ex);
//		}
//
//
//
//		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
//
//		long timeStamp = System.currentTimeMillis();
//
//		CommonResponse resp = new CommonResponse();
//
//		if(ex instanceof ApiException){
//			ApiException apiException = (ApiException) ex;
//
//			resp.setCode(apiException.getErrCode());
//			resp.setMsg(apiException.getErrMsg());
//		}
//		else {
//			resp.setCode("500");
//			resp.setMsg("未知错误: " + timeStamp);
//		}
//
//		logger.error("timeStamp: {}, error: ", timeStamp, ex);
//
//		try {
//			DataBuffer dataBuffer = response.bufferFactory()
//								  .wrap(springMvcJacksonConverter.writeValueAsBytes(resp));
//
//			response.writeWith(Mono.just(dataBuffer));
//			response.setComplete();
//		}
//		catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
