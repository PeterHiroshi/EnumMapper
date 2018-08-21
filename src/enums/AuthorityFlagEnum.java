package enums;

public enum AuthorityFlagEnum implements EnumType {

    NONE(-1, "none"),
    ADMIN(0, "administrator"),
    OPTERATOR(1, "operator");

    private int code;
    private String msg;

    AuthorityFlagEnum(int code, String msg) {
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
}
