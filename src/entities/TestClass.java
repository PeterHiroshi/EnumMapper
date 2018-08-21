package entities;

import enums.EnumType;

public class TestClass {

    public enum TE implements EnumType {
        NO(0, "无级别"),
        S4(1, "S4"),
        S3(2, "S3"),
        S2(3, "S2"),
        S1(4, "S1");

        private int code;
        private String msg;

        TE(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }

}
