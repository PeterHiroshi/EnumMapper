package enums;

public enum PostFlagEnum {

    NO(0, "not post"),
    YES(1, "posted"),
    ERROR(-1, "error"),
    ;

    private int code;
    private String msg;

    PostFlagEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
