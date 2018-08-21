import enums.mapper.MapperEnum;

import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        List<MyLog> logList = new ArrayList<>();
        logList.add(new MyLog(MapperEnum.PostFlagEnum.getCode(), -1, 1));
        logList.add(new MyLog(2, 0, 3));
        logList.add(new MyLog(1, -1, 1));
        long t = System.currentTimeMillis();
        LogShower logShower = new LogShower(logList);
        logShower.show();
        System.out.println(">>Costs " + (System.currentTimeMillis()-t) + "ms");
    }
}