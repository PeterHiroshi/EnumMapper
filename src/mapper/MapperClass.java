package mapper;

import enums.AuthorityFlagEnum;
import enums.NewVideoQualityEnum;
import enums.PostFlagEnum;
import enums.VideoQualityEnum;


public class MapperClass {

    public static final MapperClass AUTHORITYFLAGMAPPER = new MapperClass(AuthorityFlagEnum.class, "authority");
    public static final MapperClass POSTFLAGMAPPER = new MapperClass(PostFlagEnum.class, "post_status");
    public static final MapperClass VIDEOQUALITYMAPPER = new MapperClass(VideoQualityEnum.class, "视频质量");
    public static final MapperClass NEWVIDEOQUALITYMAPPER = new MapperClass(NewVideoQualityEnum.class, "新视频质量");

    private Class enumClass;
    private String msg;

    private MapperClass(Class enumClass, String msg) {
        this.enumClass = enumClass;
        this.msg = msg;
    }

    public Class getEnumClass() {
        return enumClass;
    }

    public String getMsg() {
        return msg;
    }
}
