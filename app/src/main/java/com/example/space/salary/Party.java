package com.example.space.salary;

import java.util.List;

public class Party {

    /**
     * code : 200
     * message : success
     * data : [{"year":"2021","sum":"156","partyMonthAndValueResponse":{"december":"156","november":"0","october":"0","september":"0","august":"0","july":"0","june":"0","may":"0","april":"0","march":"0","february":"0","january":"0"}},{"year":"2020","sum":"232","partyMonthAndValueResponse":{"december":"17","november":"20","october":"30","september":"30","august":"30","july":"30","june":"30","may":"23","april":"12","march":"0","february":"0","january":"0"}}]
     * currentTime : 1609818566694
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
         * year : 2021
         * sum : 156
         * partyMonthAndValueResponse : {"december":"156","november":"0","october":"0","september":"0","august":"0","july":"0","june":"0","may":"0","april":"0","march":"0","february":"0","january":"0"}
         */

        private String year;
        private String sum;
        private PartyMonthAndValueResponseBean partyMonthAndValueResponse;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public PartyMonthAndValueResponseBean getPartyMonthAndValueResponse() {
            return partyMonthAndValueResponse;
        }

        public void setPartyMonthAndValueResponse(PartyMonthAndValueResponseBean partyMonthAndValueResponse) {
            this.partyMonthAndValueResponse = partyMonthAndValueResponse;
        }

        public static class PartyMonthAndValueResponseBean {
            /**
             * december : 156
             * november : 0
             * october : 0
             * september : 0
             * august : 0
             * july : 0
             * june : 0
             * may : 0
             * april : 0
             * march : 0
             * february : 0
             * january : 0
             */

            private String december;
            private String november;
            private String october;
            private String september;
            private String august;
            private String july;
            private String june;
            private String may;
            private String april;
            private String march;
            private String february;
            private String january;

            public String getDecember() {
                return december;
            }

            public void setDecember(String december) {
                this.december = december;
            }

            public String getNovember() {
                return november;
            }

            public void setNovember(String november) {
                this.november = november;
            }

            public String getOctober() {
                return october;
            }

            public void setOctober(String october) {
                this.october = october;
            }

            public String getSeptember() {
                return september;
            }

            public void setSeptember(String september) {
                this.september = september;
            }

            public String getAugust() {
                return august;
            }

            public void setAugust(String august) {
                this.august = august;
            }

            public String getJuly() {
                return july;
            }

            public void setJuly(String july) {
                this.july = july;
            }

            public String getJune() {
                return june;
            }

            public void setJune(String june) {
                this.june = june;
            }

            public String getMay() {
                return may;
            }

            public void setMay(String may) {
                this.may = may;
            }

            public String getApril() {
                return april;
            }

            public void setApril(String april) {
                this.april = april;
            }

            public String getMarch() {
                return march;
            }

            public void setMarch(String march) {
                this.march = march;
            }

            public String getFebruary() {
                return february;
            }

            public void setFebruary(String february) {
                this.february = february;
            }

            public String getJanuary() {
                return january;
            }

            public void setJanuary(String january) {
                this.january = january;
            }
        }
    }
}
