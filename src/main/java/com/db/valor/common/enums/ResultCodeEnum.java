package com.db.valor.common.enums;

/**
 * <p>
 *  返回结果
 * </p>
 *
 * @author daibing
 * @since 2019/4/1
 */
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(1),

    /**
     * 失败
     */
    FAIL(-1);

    private Integer code;

    ResultCodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
