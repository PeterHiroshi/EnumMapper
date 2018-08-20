import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogShower {

    private List<MyLog> logList;

    public LogShower(List<MyLog> logList) {
        this.logList = logList;
    }

    public void show() {
        for (MyLog logEtem : logList) {
            Map<String, String> enumClassMap = EnumMapperUtil.getEnumClassMap(logEtem, "type");
            if (enumClassMap != null && !enumClassMap.isEmpty()) {
                String enumClassName = enumClassMap.keySet().iterator().next();
                String enumClassMsg = enumClassMap.values().iterator().next();
                Class enumclass = EnumMapperUtil.getClassByClassName(enumClassName, EnumMapperUtil.ENUM_CLASS_PACKAGE_NAME);
                Map<Integer, String> codeMsgMap = EnumMapperUtil.getCodeMsgMapByEnumClass(enumclass);
                List<String> preMsgs = codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(logEtem.getPre())).map(mp->mp.getValue()).collect(Collectors.toList());
                String preMsg = preMsgs.isEmpty() ? "<error code>" : preMsgs.get(0);
                List<String> postMsgs = codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(logEtem.getPost())).map(mp->mp.getValue()).collect(Collectors.toList());
                String postMsg = postMsgs.isEmpty() ? "<error code>" : postMsgs.get(0);
                System.out.println(enumClassMsg + ": " + preMsg + "------->" + postMsg);
            }

        }
    }

}
