package com.example.space.recycleview;

public class Detail {

    /**
     * code : 200
     * message : success
     * data : {"officeLevelWage":900,"rankSalary":1600,"probationarySalary":0,"subsistenceAllowance":2389,"workAllowance":221,"communicationsGrants":3451,"checkAllowance":0,"parentalIncentiveFees":0,"postAllowance":0,"wageGap":0,"other":2000,"heatingSubsidies":0,"yearEndAward":0,"rankAllowance":760,"noEndYear":0,"temporarySubsidies":340,"transportationAssistance":660,"issueItems":0,"wagesPayable":14325,"housingProvidentFund":0,"housingDeductions":0,"personalTax":0,"totalDeductions":0,"taxableWages":14325}
     * currentTime : 1609818176721
     */

    private int code;
    private String message;
    private DataBean data;
    private long currentTime;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public static class DataBean {
        /**
         * officeLevelWage : 900
         * rankSalary : 1600
         * probationarySalary : 0
         * subsistenceAllowance : 2389
         * workAllowance : 221
         * communicationsGrants : 3451
         * checkAllowance : 0
         * parentalIncentiveFees : 0
         * postAllowance : 0
         * wageGap : 0
         * other : 2000
         * heatingSubsidies : 0
         * yearEndAward : 0
         * rankAllowance : 760
         * noEndYear : 0
         * temporarySubsidies : 340
         * transportationAssistance : 660
         * issueItems : 0
         * wagesPayable : 14325
         * housingProvidentFund : 0
         * housingDeductions : 0
         * personalTax : 0
         * totalDeductions : 0
         * taxableWages : 14325
         */

        private int officeLevelWage;
        private int rankSalary;
        private int probationarySalary;
        private int subsistenceAllowance;
        private int workAllowance;
        private int communicationsGrants;
        private int checkAllowance;
        private int parentalIncentiveFees;
        private int postAllowance;
        private int wageGap;
        private int other;
        private int heatingSubsidies;
        private int yearEndAward;
        private int rankAllowance;
        private int noEndYear;
        private int temporarySubsidies;
        private int transportationAssistance;
        private int issueItems;
        private int wagesPayable;
        private int housingProvidentFund;
        private int housingDeductions;
        private int personalTax;
        private int totalDeductions;
        private int taxableWages;

        public int getOfficeLevelWage() {
            return officeLevelWage;
        }

        public void setOfficeLevelWage(int officeLevelWage) {
            this.officeLevelWage = officeLevelWage;
        }

        public int getRankSalary() {
            return rankSalary;
        }

        public void setRankSalary(int rankSalary) {
            this.rankSalary = rankSalary;
        }

        public int getProbationarySalary() {
            return probationarySalary;
        }

        public void setProbationarySalary(int probationarySalary) {
            this.probationarySalary = probationarySalary;
        }

        public int getSubsistenceAllowance() {
            return subsistenceAllowance;
        }

        public void setSubsistenceAllowance(int subsistenceAllowance) {
            this.subsistenceAllowance = subsistenceAllowance;
        }

        public int getWorkAllowance() {
            return workAllowance;
        }

        public void setWorkAllowance(int workAllowance) {
            this.workAllowance = workAllowance;
        }

        public int getCommunicationsGrants() {
            return communicationsGrants;
        }

        public void setCommunicationsGrants(int communicationsGrants) {
            this.communicationsGrants = communicationsGrants;
        }

        public int getCheckAllowance() {
            return checkAllowance;
        }

        public void setCheckAllowance(int checkAllowance) {
            this.checkAllowance = checkAllowance;
        }

        public int getParentalIncentiveFees() {
            return parentalIncentiveFees;
        }

        public void setParentalIncentiveFees(int parentalIncentiveFees) {
            this.parentalIncentiveFees = parentalIncentiveFees;
        }

        public int getPostAllowance() {
            return postAllowance;
        }

        public void setPostAllowance(int postAllowance) {
            this.postAllowance = postAllowance;
        }

        public int getWageGap() {
            return wageGap;
        }

        public void setWageGap(int wageGap) {
            this.wageGap = wageGap;
        }

        public int getOther() {
            return other;
        }

        public void setOther(int other) {
            this.other = other;
        }

        public int getHeatingSubsidies() {
            return heatingSubsidies;
        }

        public void setHeatingSubsidies(int heatingSubsidies) {
            this.heatingSubsidies = heatingSubsidies;
        }

        public int getYearEndAward() {
            return yearEndAward;
        }

        public void setYearEndAward(int yearEndAward) {
            this.yearEndAward = yearEndAward;
        }

        public int getRankAllowance() {
            return rankAllowance;
        }

        public void setRankAllowance(int rankAllowance) {
            this.rankAllowance = rankAllowance;
        }

        public int getNoEndYear() {
            return noEndYear;
        }

        public void setNoEndYear(int noEndYear) {
            this.noEndYear = noEndYear;
        }

        public int getTemporarySubsidies() {
            return temporarySubsidies;
        }

        public void setTemporarySubsidies(int temporarySubsidies) {
            this.temporarySubsidies = temporarySubsidies;
        }

        public int getTransportationAssistance() {
            return transportationAssistance;
        }

        public void setTransportationAssistance(int transportationAssistance) {
            this.transportationAssistance = transportationAssistance;
        }

        public int getIssueItems() {
            return issueItems;
        }

        public void setIssueItems(int issueItems) {
            this.issueItems = issueItems;
        }

        public int getWagesPayable() {
            return wagesPayable;
        }

        public void setWagesPayable(int wagesPayable) {
            this.wagesPayable = wagesPayable;
        }

        public int getHousingProvidentFund() {
            return housingProvidentFund;
        }

        public void setHousingProvidentFund(int housingProvidentFund) {
            this.housingProvidentFund = housingProvidentFund;
        }

        public int getHousingDeductions() {
            return housingDeductions;
        }

        public void setHousingDeductions(int housingDeductions) {
            this.housingDeductions = housingDeductions;
        }

        public int getPersonalTax() {
            return personalTax;
        }

        public void setPersonalTax(int personalTax) {
            this.personalTax = personalTax;
        }

        public int getTotalDeductions() {
            return totalDeductions;
        }

        public void setTotalDeductions(int totalDeductions) {
            this.totalDeductions = totalDeductions;
        }

        public int getTaxableWages() {
            return taxableWages;
        }

        public void setTaxableWages(int taxableWages) {
            this.taxableWages = taxableWages;
        }
    }

    @Override
    public String toString() {
        return "Detail{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", currentTime=" + currentTime +
                '}';
    }
}
