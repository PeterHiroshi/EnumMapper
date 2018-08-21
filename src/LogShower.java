import mapper.MapperClass;

import java.util.List;
import java.util.Map;

public class LogShower {

    private List<MyLog> logList;

    public LogShower(List<MyLog> logList) {
        this.logList = logList;
    }

    public void show() {
        MapperClass mc;
        for (MyLog logEtem : logList) {
            mc = EnumMapperUtil.getMapperClassByFieldName(logEtem, "type");
            if (mc != null) {
                String enumClassMsg = mc.getMsg();
                Class enumClass = mc.getEnumClass();
                Map<Integer, String> codeMsgMap = EnumMapperUtil.getCodeMsgMapByEnumClass(enumClass);
                String preMsg =
                        codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(logEtem.getPre())).map(mp->mp.getValue()).findFirst().orElse("<invalid code>");
                String postMsg =
                        codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(logEtem.getPost())).map(mp->mp.getValue()).findFirst().orElse("<invalid code>");
                System.out.println(enumClassMsg + ": " + preMsg + "------->" + postMsg);
            }
        }
    }

}
