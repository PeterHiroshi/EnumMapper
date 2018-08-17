package enums;

public enum PostFlag {

    NO(0, "not post"),
    YES(1, "posted"),
    ERROR(2, "error"),
    ;

    private int code;
    private String msg;

    PostFlag(int code, String msg) {
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
