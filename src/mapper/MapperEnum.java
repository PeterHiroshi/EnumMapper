package mapper;

public enum MapperEnum {

    AuthorityFlag(0, MapperClass.AUTHORITYFLAGMAPPER),
    PostFlag(1, MapperClass.POSTFLAGMAPPER),
    VideoQuality(2, MapperClass.VIDEOQUALITYMAPPER),
    NewVideoQuality(3, MapperClass.NEWVIDEOQUALITYMAPPER);

    private int typeCode;
    private MapperClass mc;

    MapperEnum(int typeCode, MapperClass mc) {
        this.typeCode = typeCode;
        this.mc = mc;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public MapperClass getMc() {
        return mc;
    }

    public void setMc(MapperClass mc) {
        this.mc = mc;
    }
}
