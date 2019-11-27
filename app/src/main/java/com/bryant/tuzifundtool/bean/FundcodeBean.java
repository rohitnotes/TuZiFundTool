package com.bryant.tuzifundtool.bean;


import org.litepal.crud.LitePalSupport;

public class FundcodeBean extends LitePalSupport {

    private String fundcode;

    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }
}
