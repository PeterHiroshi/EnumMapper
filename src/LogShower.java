import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogShower {

    private List<MyLog> logList;

    public LogShower(List<MyLog> logList) {
        this.logList = logList;
    }

    public void show() {
        Map<String, String> enumClassMap;
        for (MyLog logEtem : logList) {
            enumClassMap = EnumMapperUtil.getEnumClassMap(logEtem, "type");
            if (enumClassMap != null && !enumClassMap.isEmpty()) {
                String enumClassName = enumClassMap.keySet().iterator().next();
                String enumClassMsg = enumClassMap.values().iterator().next();
                Class enumclass = EnumMapperUtil.getClassByClassName(enumClassName, EnumMapperUtil.ENUM_CLASS_PACKAGE_NAME);
                Map<Integer, String> codeMsgMap = EnumMapperUtil.getCodeMsgMapByEnumClass(enumclass);
                String preMsg =
                        codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(logEtem.getPre())).map(mp->mp.getValue()).findFirst().orElse("<invalid code>");
                String postMsg =
                        codeMsgMap.entrySet().stream().filter(codeMsg->codeMsg.getKey().equals(logEtem.getPost())).map(mp->mp.getValue()).findFirst().orElse("<invalid code>");
                System.out.println(enumClassMsg + ": " + preMsg + "------->" + postMsg);
            }

        }
    }

}
