package mapper;

public class MapperClass {

    private Class enumClass;
    private String msg;

    public MapperClass(Class enumClass, String msg) {
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
