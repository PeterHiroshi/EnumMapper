import java.util.List;
import java.util.Map;

class LogShower {

    private List<MyLog> logList;

    LogShower(List<MyLog> logList) {
        this.logList = logList;
    }

    void show() {
        String desc;
        String pre;
        String post;
        for (MyLog log : logList) {
            Map<String, String> descMsgPreMap = EnumMapperUtil.getDescMsgMapByCodeFromEnumClass(log.getEnumClass(), log.getPre());
            if (descMsgPreMap == null) {
                continue;
            }
            Map<String, String> descMsgPostMap = EnumMapperUtil.getDescMsgMapByCodeFromEnumClass(log.getEnumClass(), log.getPost());
            desc = descMsgPreMap.keySet().iterator().next();
            pre = descMsgPreMap.values().iterator().next();
            post = descMsgPostMap.values().iterator().next();
            System.out.println(desc + ": " + pre + "--->" + post);
        }
    }

}
