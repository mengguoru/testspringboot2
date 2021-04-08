package com.meng.testspringboot2.config;

import java.util.List;
import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class ResponseWrapper extends ResponseBodyResultHandler {

	private static MethodParameter param;

	static {
		try {
			param = new MethodParameter(ResponseWrapper.class
											.getDeclaredMethod("methodForParams"), -1);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public ResponseWrapper(List<HttpMessageWriter<?>> writers,
						   RequestedContentTypeResolver resolver) {
		super(writers, resolver);
	}


	private static Mono<CommonResponse> methodForParams() {
		return null;
	}

	@Override
	public boolean supports(HandlerResult result) {
		boolean isMono = result.getReturnType().resolve() == Mono.class;
		boolean isAlreadyResponse = result.getReturnType().resolveGeneric(0) == CommonResponse.class;

		return isMono && !isAlreadyResponse;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
		Object returnValue = result.getReturnValue();
		Objects.requireNonNull(returnValue, "response is null!");

		Mono<CommonResponse> body = ((Mono<Object>) returnValue)
								  .map(CommonResponse::success)
								  .defaultIfEmpty(CommonResponse.success());

		return writeBody(body, param, exchange);
	}
}
