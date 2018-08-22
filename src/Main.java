import entities.TestClass;
import enums.AuthorityFlagEnum;
import enums.PostFlagEnum;
import enums.VideoQualityEnum;

import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        List<MyLog> logList = new ArrayList<>();
        logList.add(new MyLog(AuthorityFlagEnum.class, 1, -1));
        logList.add(new MyLog(TestClass.TE.class, 0, 3));
        logList.add(new MyLog(PostFlagEnum.class, 2, 1));

        logList.add(new MyLog(VideoQualityEnum.class, 1, 2));
        logList.add(new MyLog(AuthorityFlagEnum.class, 1, 2));
        logList.add(new MyLog(TestClass.TE.class, 0, 3));
        logList.add(new MyLog(PostFlagEnum.class, 2, 1));
        logList.add(new MyLog(VideoQualityEnum.class, 1, 2));
        logList.add(new MyLog(AuthorityFlagEnum.class, 1, 2));
        logList.add(new MyLog(TestClass.TE.class, 0, 3));
        logList.add(new MyLog(PostFlagEnum.class, 2, 1));
        logList.add(new MyLog(VideoQualityEnum.class, 1, 2));
        logList.add(new MyLog(AuthorityFlagEnum.class, 1, 2));
        logList.add(new MyLog(TestClass.TE.class, 1, 3));
        logList.add(new MyLog(PostFlagEnum.class, 2, 1));
        logList.add(new MyLog(VideoQualityEnum.class, 0, 2));

        long t = System.currentTimeMillis();
        LogShower logShower = new LogShower(logList);
        logShower.show();
        System.out.println(">>Costs " + (System.currentTimeMillis()-t) + "ms");

    }
}