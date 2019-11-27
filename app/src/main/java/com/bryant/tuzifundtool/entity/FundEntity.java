package com.bryant.tuzifundtool.entity;

public class FundEntity {

    private String fundName;//基金名称
    private String fundCode;//基金代码
    private String unitValue;//单位净值
    private String budgetValue;//今日估算
    private String pastGain;//昨日涨幅

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(String unitValue) {
        this.unitValue = unitValue;
    }

    public String getBudgetValue() {
        return budgetValue;
    }

    public void setBudgetValue(String budgetValue) {
        this.budgetValue = budgetValue;
    }

    public String getPastGain() {
        return pastGain;
    }

    public void setPastGain(String pastGain) {
        this.pastGain = pastGain;
    }
}
