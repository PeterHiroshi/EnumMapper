package enums.mapper;

public enum MapperEnum {

    AuthorityFlagEnum(0, "authority"),
    PostFlagEnum(1, "post_status"),
    VideoQualityEnum(2, "视频审核");

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
