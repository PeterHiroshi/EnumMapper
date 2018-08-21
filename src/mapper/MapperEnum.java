package mapper;

import enums.PostFlagEnum;
import entities.TestClass;
import enums.AuthorityFlagEnum;
import enums.VideoQualityEnum;

public enum MapperEnum {

    AuthorityFlag(0, AuthorityFlagEnum.class, "authority"),
    PostFlag(1, PostFlagEnum.class, "post_status"),
    VideoQuality(2, VideoQualityEnum.class, "视频质量"),
    NewVideoQuality(3, TestClass.TE.class, "新视频质量");

    private int typeCode;
    private Class enumClass;
    private String msg;

    MapperEnum(int typeCode, Class enumClass, String msg) {
        this.enumClass = enumClass;
        this.typeCode = typeCode;
        this.msg = msg;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public Class getEnumClass() {
        return enumClass;
    }

    public void setEnumClass(Class enumClass) {
        this.enumClass = enumClass;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
