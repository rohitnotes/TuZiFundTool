package com.bryant.tuzifundtool.bean;

import java.util.List;

public class QueryBean {

    /**
     * Datas : [{"FCODE":"320007","SHORTNAME":"诺安成长混合","PDATE":"2019-11-21","NAV":"1.1380","ACCNAV":"1.583","NAVCHGRT":"0.26","GSZ":"1.1131","GSZZL":"-2.19","GZTIME":"2019-11-22 15:00","NEWPRICE":"--","CHANGERATIO":"--","ZJL":"--","HQDATE":"--","ISHAVEREDPACKET":false},{"FCODE":"213001","SHORTNAME":"宝盈鸿利收益灵活配置混合A","PDATE":"2019-11-21","NAV":"1.3720","ACCNAV":"3.688","NAVCHGRT":"-0.65","GSZ":"1.3323","GSZZL":"-2.90","GZTIME":"2019-11-22 15:00","NEWPRICE":"--","CHANGERATIO":"--","ZJL":"--","HQDATE":"--","ISHAVEREDPACKET":false}]
     * ErrCode : 0
     * Success : true
     * ErrMsg : null
     * Message : null
     * ErrorCode : 0
     * ErrorMessage : null
     * ErrorMsgLst : null
     * TotalCount : 2
     * Expansion : {"GZTIME":"2019-11-22","FSRQ":"2019-11-21"}
     */

    private int ErrCode;
    private boolean Success;
    private Object ErrMsg;
    private Object Message;
    private String ErrorCode;
    private Object ErrorMessage;
    private Object ErrorMsgLst;
    private int TotalCount;
    private ExpansionBean Expansion;
    private List<DatasBean> Datas;

    public int getErrCode() {
        return ErrCode;
    }

    public void setErrCode(int ErrCode) {
        this.ErrCode = ErrCode;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public Object getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(Object ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
        this.Message = Message;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public Object getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(Object ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public Object getErrorMsgLst() {
        return ErrorMsgLst;
    }

    public void setErrorMsgLst(Object ErrorMsgLst) {
        this.ErrorMsgLst = ErrorMsgLst;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int TotalCount) {
        this.TotalCount = TotalCount;
    }

    public ExpansionBean getExpansion() {
        return Expansion;
    }

    public void setExpansion(ExpansionBean Expansion) {
        this.Expansion = Expansion;
    }

    public List<DatasBean> getDatas() {
        return Datas;
    }

    public void setDatas(List<DatasBean> Datas) {
        this.Datas = Datas;
    }

    public static class ExpansionBean {
        /**
         * GZTIME : 2019-11-22
         * FSRQ : 2019-11-21
         */

        private String GZTIME;
        private String FSRQ;

        public String getGZTIME() {
            return GZTIME;
        }

        public void setGZTIME(String GZTIME) {
            this.GZTIME = GZTIME;
        }

        public String getFSRQ() {
            return FSRQ;
        }

        public void setFSRQ(String FSRQ) {
            this.FSRQ = FSRQ;
        }
    }

    public static class DatasBean {
        /**
         * FCODE : 320007
         * SHORTNAME : 诺安成长混合
         * PDATE : 2019-11-21
         * NAV : 1.1380
         * ACCNAV : 1.583
         * NAVCHGRT : 0.26
         * GSZ : 1.1131
         * GSZZL : -2.19
         * GZTIME : 2019-11-22 15:00
         * NEWPRICE : --
         * CHANGERATIO : --
         * ZJL : --
         * HQDATE : --
         * ISHAVEREDPACKET : false
         */

        private String FCODE;
        private String SHORTNAME;
        private String PDATE;
        private String NAV;
        private String ACCNAV;
        private String NAVCHGRT;
        private String GSZ;
        private String GSZZL;
        private String GZTIME;
        private String NEWPRICE;
        private String CHANGERATIO;
        private String ZJL;
        private String HQDATE;
        private boolean ISHAVEREDPACKET;

        public String getFCODE() {
            return FCODE;
        }

        public void setFCODE(String FCODE) {
            this.FCODE = FCODE;
        }

        public String getSHORTNAME() {
            return SHORTNAME;
        }

        public void setSHORTNAME(String SHORTNAME) {
            this.SHORTNAME = SHORTNAME;
        }

        public String getPDATE() {
            return PDATE;
        }

        public void setPDATE(String PDATE) {
            this.PDATE = PDATE;
        }

        public String getNAV() {
            return NAV;
        }

        public void setNAV(String NAV) {
            this.NAV = NAV;
        }

        public String getACCNAV() {
            return ACCNAV;
        }

        public void setACCNAV(String ACCNAV) {
            this.ACCNAV = ACCNAV;
        }

        public String getNAVCHGRT() {
            return NAVCHGRT;
        }

        public void setNAVCHGRT(String NAVCHGRT) {
            this.NAVCHGRT = NAVCHGRT;
        }

        public String getGSZ() {
            return GSZ;
        }

        public void setGSZ(String GSZ) {
            this.GSZ = GSZ;
        }

        public String getGSZZL() {
            return GSZZL;
        }

        public void setGSZZL(String GSZZL) {
            this.GSZZL = GSZZL;
        }

        public String getGZTIME() {
            return GZTIME;
        }

        public void setGZTIME(String GZTIME) {
            this.GZTIME = GZTIME;
        }

        public String getNEWPRICE() {
            return NEWPRICE;
        }

        public void setNEWPRICE(String NEWPRICE) {
            this.NEWPRICE = NEWPRICE;
        }

        public String getCHANGERATIO() {
            return CHANGERATIO;
        }

        public void setCHANGERATIO(String CHANGERATIO) {
            this.CHANGERATIO = CHANGERATIO;
        }

        public String getZJL() {
            return ZJL;
        }

        public void setZJL(String ZJL) {
            this.ZJL = ZJL;
        }

        public String getHQDATE() {
            return HQDATE;
        }

        public void setHQDATE(String HQDATE) {
            this.HQDATE = HQDATE;
        }

        public boolean isISHAVEREDPACKET() {
            return ISHAVEREDPACKET;
        }

        public void setISHAVEREDPACKET(boolean ISHAVEREDPACKET) {
            this.ISHAVEREDPACKET = ISHAVEREDPACKET;
        }
    }
}
