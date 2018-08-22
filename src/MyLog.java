public class MyLog {

    private Class enumClass;
    private Integer pre;
    private Integer post;

    MyLog(Class enumClass, Integer pre, Integer post) {
        this.enumClass = enumClass;
        this.pre = pre;
        this.post = post;
    }

    public Class getEnumClass() {
        return enumClass;
    }

    public void setEnumClass(Class enumClass) {
        this.enumClass = enumClass;
    }

    Integer getPre() {
        return pre;
    }

    public void setPre(Integer pre) {
        this.pre = pre;
    }

    Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

}
