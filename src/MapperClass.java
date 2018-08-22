public class MapperClass {

    private String desc;
    private String msg;

    MapperClass(String desc, String msg) {
        this.desc = desc;
        this.msg = msg;
    }

    String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
