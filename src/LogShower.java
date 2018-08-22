import mapper.MapperClass;

import java.util.List;

class LogShower {

    private List<MyLog> logList;

    LogShower(List<MyLog> logList) {
        this.logList = logList;
    }

    void show() {
        MapperClass mc;
        for (MyLog log : logList) {
            mc = EnumMapperUtil.getMapperClass(log, log.getType());
            if (mc != null) {
                String enumClassMsg = mc.getMsg();
                Class enumClass = mc.getEnumClass();
                String preMsg = EnumMapperUtil.getMsgByCodeFromEnumClass(enumClass, log.getPre());
                String postMsg = EnumMapperUtil.getMsgByCodeFromEnumClass(enumClass, log.getPost());
                System.out.println(enumClassMsg + ": " + preMsg + "------->" + postMsg);
            }
        }
    }

}
