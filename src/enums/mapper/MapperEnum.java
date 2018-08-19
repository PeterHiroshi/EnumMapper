package enums.mapper;

public enum MapperEnum {

    AuthorityFlag(0, "authority"),
    PostFlag(1, "post status");

    private int code;
    private String msg;

    MapperEnum(int code, String msg) {
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
