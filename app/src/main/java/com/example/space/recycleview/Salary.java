package com.example.space.recycleview;

import java.util.List;

public class Salary {

    /**
     * code : 200
     * message : success
     * data : [{"year":2020,"sum":"20000","list":[{"month":5,"value":5000},{"month":4,"value":5000},{"month":3,"value":5000},{"month":2,"value":5000},{"month":1,"value":5000}]},{"year":2019,"sum":"20000","list":[{"month":12,"value":5000},{"month":11,"value":5000},{"month":10,"value":5000},{"month":9,"value":5000},{"month":8,"value":5000}]}]
     * currentTime : 1608630006829
     */

    private int code;
    private String message;
    private long currentTime;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * year : 2020
         * sum : 20000
         * list : [{"month":5,"value":5000},{"month":4,"value":5000},{"month":3,"value":5000},{"month":2,"value":5000},{"month":1,"value":5000}]
         */

        private int year;
        private String sum;
        private List<ListBean> list;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * month : 5
             * value : 5000
             */

            private int month;
            private int value;

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}
