package com.hs.commons.lang;

public enum CommonErrorCode implements ErrorCode {

    RUNTIME_EXCEPTION("Common-00", "运行时内部调用错误."),
    CONNECTION_EXCEPTION("Common-01", "连接异常."),
    IO_EXCEPTION("Common-03", "IO异常."),
    ;

    private final String code;

    private final String describe;

    private CommonErrorCode(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.describe;
    }

    @Override
    public String toString() {
        return String.format("Code:[%s], Describe:[%s]", this.code,
                this.describe);
    }

}
