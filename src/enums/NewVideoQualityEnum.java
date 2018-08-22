package enums;

public enum NewVideoQualityEnum implements EnumType {
    NO(0, "无级别"),
    S4(1, "S4"),
    S3(2, "S3"),
    S2(3, "S2"),
    S1(4, "S1");

    private Integer code;
    private String msg;

    NewVideoQualityEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getDesc() {
        return "新视频质量";
    }
}
