import mapper.MapperClass;

import java.util.List;
import java.util.Map;

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
                Map<Integer, String> codeMsgMap = EnumMapperUtil.getCodeMsgMapByEnumClass(enumClass);
                if (codeMsgMap != null && !codeMsgMap.isEmpty()) {
                    String preMsg =
                            codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(log.getPre())).map(Map.Entry::getValue).findFirst().orElse("<invalid code>");
                    String postMsg =
                            codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(log.getPost())).map(Map.Entry::getValue).findFirst().orElse("<invalid code>");
                    System.out.println(enumClassMsg + ": " + preMsg + "------->" + postMsg);
                }
            }
        }
    }

}
