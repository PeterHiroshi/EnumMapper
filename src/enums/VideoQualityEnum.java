package enums;

public enum VideoQualityEnum {

    AUDITING(0, "未审核"),
    WEAK(1, "差"),
    MEDIUM(2, "中等"),
    HIGH(3, "优质"),
    QUALITY_SECOND_BEST(4, "质量次优"),
    PENDING(5, "待定"),
    FEATURE_HEAD(6, "特色头部"),
    COMMION_SECOND_BEST(7, "普通次优"),
    FLOW_SECOND_BEST(8, "流量次优");

    private int code;
    private String msg;

    VideoQualityEnum(int code, String msg) {
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
