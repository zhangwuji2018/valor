package com.db.valor.common;

import com.db.valor.common.enums.ResultCodeEnum;

import java.util.HashMap;

/**
 * <p>
 *     返回json格式数据
 * </p>
 *
 * @author daibing
 * @since 2019/4/1
 */
public class JsonResult extends HashMap<String, Object> {

    /**
     * 默认构造器
     */
    private JsonResult() {}

    /**
     *失败时返回数据
     * @param msg 返回信息
     * @param resultData 返回数据
     * @return JsonResult
     */
    public static JsonResult error(String msg, Object resultData) {
        return getJsonResult(msg, ResultCodeEnum.FAIL.getCode(), resultData);
    }

    /**
     * 操作失败只返回信息
     * @param msg 返回信息
     * @return JsonResult
     */
    public static JsonResult error(String msg) {
        return error(msg, null);
    }

    /**
     * 操作成功时
     * @param msg 返回信息
     * @param resultData 返回的数据
     * @return JsonResult
     */
    public static JsonResult success(String msg, Object resultData) {
        return getJsonResult(msg, ResultCodeEnum.SUCCESS.getCode(), resultData);
    }

    /**
     * 操作成功只返回信息
     * @param msg
     * @return
     */
    public static JsonResult success(String msg) {
        return success(msg, null);
    }

    /**
     * 空参时
     * @return
     */
    public static JsonResult success() {
        return success("操作成功");
    }

    private static JsonResult getJsonResult(String msg, Integer code, Object resultData) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.put("code",code);
        jsonResult.put("msg",msg);
        jsonResult.put("data",resultData);
        return jsonResult;
    }

    @Override
    public Object put(String key, Object value) {
        return super.put(key, value);
    }
}
