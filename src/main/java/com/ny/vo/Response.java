package com.ny.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 响应对象
 *
 *
 */
@ApiModel(value = "响应对象")
public class Response<T> {
    private static final String OK = "ok";
    private static final String ERROR = "error";

    @ApiModelProperty(value = "响应信息")
    private Meta meta;
    @ApiModelProperty(value = "响应数据")
    private T data;

    public Response<T> success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public Response<T> success(T data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public Response<T> failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    public Response<T> failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public T getData() {
        return data;
    }

    public class Meta {

        private boolean success;
        private String message;

        public Meta(boolean success) {
            this.success = success;
        }

        Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
