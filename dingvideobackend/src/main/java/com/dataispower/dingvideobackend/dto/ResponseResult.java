package com.dataispower.dingvideobackend.dto;

import com.dataispower.dingvideobackend.common.Constants;
import lombok.Data;

/**
 * author:heroding
 * date:2023/10/31 21:02
 * description：http返回内容
 **/

@Data
public class ResponseResult {

    private String code;

    private String result;

    private String message;

    private Object data;

    public ResponseResult() {
        this.code = Constants.Internet.OK;
        this.result = "true";
    }

    public ResponseResult(String code, String result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }
    public ResponseResult(String code, String result, String message,Object data) {
        this.code = code;
        this.result = result;
        this.message = message;
        this.data = data;
    }
    /*
     * 成功返回结果1
     * */
    public static ResponseResult success(String message) {
        return new ResponseResult(Constants.Internet.OK,"true",message);
    }
    /*
     * 成功返回结果2
     * */
    public static ResponseResult success(String message,Object data) {
        return new ResponseResult(Constants.Internet.OK,"true",message,data);
    }
    /*
     * 失败返回1
     * */
    public static ResponseResult error(String message) {
        return new ResponseResult(Constants.Internet.ERROR,"false",message);
    }
    /*
     * 失败返回2
     * */
    public static ResponseResult error(String message,Object data) {
        return new ResponseResult(Constants.Internet.ERROR,"false",message,data);
    }
}
