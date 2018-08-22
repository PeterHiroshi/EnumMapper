package enums;

public enum PostFlagEnum implements EnumType {

    NO(0, "not post"),
    YES(1, "posted"),
    ERROR(-1, "error");

    private int code;
    private String msg;

    PostFlagEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getDesc() {
        return "post_status";
    }


}
