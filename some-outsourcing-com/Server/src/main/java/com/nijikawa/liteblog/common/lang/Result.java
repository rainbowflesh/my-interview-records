package com.nijikawa.liteblog.common.lang;
import lombok.Data;

import java.io.Serializable;

/**
 * 脱裤子放屁之封装 http 响应码
 *
 * @author nijikawa
 */
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 43835046611545584L;
    private int code;
    private String msg;
    private Object data;
    public static Result succ(Object data) {
        return succ(200, "操作成功", data);
    }
    private static Result succ(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    public static Result fail(String msg) {
        return fail(400, msg, null);
    }
    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }
    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException
            , java.io.NotSerializableException {
        throw new java.io.NotSerializableException("com.nijikawa.liteblog.common.lang.Result");
    }
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.NotSerializableException {
        throw new java.io.NotSerializableException("com.nijikawa.liteblog.common.lang.Result");
    }
}
