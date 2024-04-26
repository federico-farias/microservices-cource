package com.bintics.orders.gateway;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class ProductsErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = feign.FeignException.errorStatus(methodKey, response);
        int status = response.status();
        if (status >= 500) {
            return new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    null,
                    response.request());
        }
        return exception;
    }
}
