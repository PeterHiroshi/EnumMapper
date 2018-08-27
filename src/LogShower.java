import enums.EnumType;

import java.util.List;

class LogShower {

    private List<MyLog> logList;

    LogShower(List<MyLog> logList) {
        this.logList = logList;
    }

    void show() {
        String desc;
        String preMsg;
        String postMsg;
        for (MyLog log : logList) {
            EnumType enumTypeInstancePre = EnumMapperUtil.getEnumTypeByCodeFromEnumClass(log.getEnumClass(), log.getPre());
            EnumType enumTypeInstancePost = EnumMapperUtil.getEnumTypeByCodeFromEnumClass(log.getEnumClass(), log.getPost());
            if (enumTypeInstancePre == null || enumTypeInstancePost == null) {
                continue;
            }
            desc = enumTypeInstancePre.getDesc();
            preMsg = enumTypeInstancePre.getMsg();
            postMsg = enumTypeInstancePost.getMsg();
            System.out.println(desc + ": " + preMsg + "--->" + postMsg);
        }
    }

}
