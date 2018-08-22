import annotation.EnumTypeMapper;

public class MyLog {

    @EnumTypeMapper(type = EnumTypeMapper.MAPPER)
    private Integer type;

    private Integer pre;
    private Integer post;

    MyLog(Integer type, Integer pre, Integer post) {
        this.type = type;
        this.pre = pre;
        this.post = post;
    }

    Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
