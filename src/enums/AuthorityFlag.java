package enums;

public enum AuthorityFlag {

    NONE(-1, "none"),
    ADMIN(0, "administrator"),
    OPTERATOR(1, "operator");

    private int code;
    private String msg;

    AuthorityFlag(int code, String msg) {
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
