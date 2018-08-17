import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogShower {

    private static final String ENUMS_PACKAGE_NAME = "enums";

    private List<MyLog> logList;

    public LogShower(List<MyLog> logList) {
        this.logList = logList;
    }

    public void show() throws Exception {
        for (MyLog logEtem : logList) {
            Map<String, String> mapperCodeMsgMap = EnumUtil.getEnumValueByFieldName(logEtem, "type");
            String enumClassName = mapperCodeMsgMap.keySet().iterator().next();
            String enumClassMsg = mapperCodeMsgMap.values().iterator().next();
            Class enumclass = Class.forName(ENUMS_PACKAGE_NAME + "." + enumClassName);
            Map<Integer, String> codeMsgMap = EnumUtil.getCodeMsgMapByEnumClass(enumclass);
            String preMsg =
                    codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(logEtem.getPre())).map(mp->mp.getValue()).collect(Collectors.toList()).get(0);
            String postMsg =
                    codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(logEtem.getPost())).map(mp->mp.getValue()).collect(Collectors.toList()).get(0);
            System.out.println(enumClassMsg + ": " + preMsg + "------->" + postMsg);
        }
    }

}
