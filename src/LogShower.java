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
            desc = EnumMapperUtil.getDescFromEnumType(enumTypeInstancePre);
            preMsg = EnumMapperUtil.getMsgFromEnumType(enumTypeInstancePre);
            postMsg = EnumMapperUtil.getMsgFromEnumType(enumTypeInstancePost);
            System.out.println(desc + ": " + preMsg + "--->" + postMsg);
        }
    }

}
