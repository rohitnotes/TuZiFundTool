package com.bryant.tuzifundtool.bean;

import java.util.List;

public class SearchBean {


    /**
     * Datas : [{"FCODE":"320001","ShowFCode":"320001","T":"H","FTYPE":"混合型","SHORTNAME":"诺安平衡混合","P":"F","HIGHTLIGHT":"<font color='#ff4400'>3<\/font><font color='#ff4400'>2<\/font><font color='#ff4400'>0<\/font><font color='#ff4400'>0<\/font><font color='#ff4400'>0<\/font>1_诺安平衡混合","DEBUGFIELD":null,"SYL":"0.71","SYLTYPE":"近1月","IsDelisting":false,"FCodeType":0,"SecondFCodeType":0,"ABBNAME":"NAPHHH","ABBTNAME":"NUOANPINGHENGHUNHE","EsScore":null,"ForeShortName":"诺安平衡混合"},{"FCODE":"320004","ShowFCode":"320004","T":"B","FTYPE":"债券型","SHORTNAME":"诺安优化收益债券","P":"F","HIGHTLIGHT":"<font color='#ff4400'>3<\/font><font color='#ff4400'>2<\/font><font color='#ff4400'>0<\/font><font color='#ff4400'>0<\/font><font color='#ff4400'>0<\/font>4_诺安优化收益债券","DEBUGFIELD":null,"SYL":"-0.05","SYLTYPE":"近1月","IsDelisting":false,"FCodeType":0,"SecondFCodeType":0,"ABBNAME":"NAYHSYZQ","ABBTNAME":"NUOANYOUHUASHOUYIZHAIQUAN","EsScore":null,"ForeShortName":"诺安优化收益债券"},{"FCODE":"320007","ShowFCode":"320007","T":"H","FTYPE":"混合型","SHORTNAME":"诺安成长混合","P":"F","HIGHTLIGHT":"<font color='#ff4400'>3<\/font><font color='#ff4400'>2<\/font><font color='#ff4400'>0<\/font><font color='#ff4400'>0<\/font><font color='#ff4400'>0<\/font>7_诺安成长混合","DEBUGFIELD":null,"SYL":"6.08","SYLTYPE":"近1月","IsDelisting":false,"FCodeType":0,"SecondFCodeType":0,"ABBNAME":"NACZHH","ABBTNAME":"NUOANCHENGZHANGHUNHE","EsScore":null,"ForeShortName":"诺安成长混合"},{"FCODE":"320006","ShowFCode":"320006","T":"H","FTYPE":"混合型","SHORTNAME":"诺安灵活配置混合","P":"F","HIGHTLIGHT":"<font color='#ff4400'>3<\/font><font color='#ff4400'>2<\/font><font color='#ff4400'>0<\/font><font color='#ff4400'>0<\/font><font color='#ff4400'>0<\/font>6_诺安灵活配置混合","DEBUGFIELD":null,"SYL":"-2.23","SYLTYPE":"近1月","IsDelisting":false,"FCodeType":0,"SecondFCodeType":0,"ABBNAME":"NALHPZHH","ABBTNAME":"NUOANLINGHUOPEIZHIHUNHE","EsScore":null,"ForeShortName":"诺安灵活配置混合"},{"FCODE":"320002","ShowFCode":"320002","T":"C","FTYPE":"货币型","SHORTNAME":"诺安货币A","P":"F","HIGHTLIGHT":"<font color='#ff4400'>3<\/font><font color='#ff4400'>2<\/font><font color='#ff4400'>0<\/font><font color='#ff4400'>0<\/font><font color='#ff4400'>0<\/font>2_诺安货币A","DEBUGFIELD":null,"SYL":"1.9650","SYLTYPE":"7日年化","IsDelisting":false,"FCodeType":0,"SecondFCodeType":0,"ABBNAME":"NAHBA","ABBTNAME":"NUOANHUOBIA","EsScore":null,"ForeShortName":"诺安货币A"}]
     * ErrCode : 0
     * Success : true
     * ErrMsg : null
     * Message : null
     * ErrorCode : 0
     * ErrorMessage : null
     * ErrorMsgLst : null
     * TotalCount : 9
     * Expansion : 模糊搜索
     */

    private int ErrCode;
    private boolean Success;
    private Object ErrMsg;
    private Object Message;
    private String ErrorCode;
    private Object ErrorMessage;
    private Object ErrorMsgLst;
    private int TotalCount;
    private String Expansion;
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

    public String getExpansion() {
        return Expansion;
    }

    public void setExpansion(String Expansion) {
        this.Expansion = Expansion;
    }

    public List<DatasBean> getDatas() {
        return Datas;
    }

    public void setDatas(List<DatasBean> Datas) {
        this.Datas = Datas;
    }

    public static class DatasBean {
        /**
         * FCODE : 320001
         * ShowFCode : 320001
         * T : H
         * FTYPE : 混合型
         * SHORTNAME : 诺安平衡混合
         * P : F
         * HIGHTLIGHT : <font color='#ff4400'>3</font><font color='#ff4400'>2</font><font color='#ff4400'>0</font><font color='#ff4400'>0</font><font color='#ff4400'>0</font>1_诺安平衡混合
         * DEBUGFIELD : null
         * SYL : 0.71
         * SYLTYPE : 近1月
         * IsDelisting : false
         * FCodeType : 0
         * SecondFCodeType : 0
         * ABBNAME : NAPHHH
         * ABBTNAME : NUOANPINGHENGHUNHE
         * EsScore : null
         * ForeShortName : 诺安平衡混合
         */

        private String FCODE;
        private String ShowFCode;
        private String T;
        private String FTYPE;
        private String SHORTNAME;
        private String P;
        private String HIGHTLIGHT;
        private Object DEBUGFIELD;
        private String SYL;
        private String SYLTYPE;
        private boolean IsDelisting;
        private int FCodeType;
        private int SecondFCodeType;
        private String ABBNAME;
        private String ABBTNAME;
        private Object EsScore;
        private String ForeShortName;

        public String getFCODE() {
            return FCODE;
        }

        public void setFCODE(String FCODE) {
            this.FCODE = FCODE;
        }

        public String getShowFCode() {
            return ShowFCode;
        }

        public void setShowFCode(String ShowFCode) {
            this.ShowFCode = ShowFCode;
        }

        public String getT() {
            return T;
        }

        public void setT(String T) {
            this.T = T;
        }

        public String getFTYPE() {
            return FTYPE;
        }

        public void setFTYPE(String FTYPE) {
            this.FTYPE = FTYPE;
        }

        public String getSHORTNAME() {
            return SHORTNAME;
        }

        public void setSHORTNAME(String SHORTNAME) {
            this.SHORTNAME = SHORTNAME;
        }

        public String getP() {
            return P;
        }

        public void setP(String P) {
            this.P = P;
        }

        public String getHIGHTLIGHT() {
            return HIGHTLIGHT;
        }

        public void setHIGHTLIGHT(String HIGHTLIGHT) {
            this.HIGHTLIGHT = HIGHTLIGHT;
        }

        public Object getDEBUGFIELD() {
            return DEBUGFIELD;
        }

        public void setDEBUGFIELD(Object DEBUGFIELD) {
            this.DEBUGFIELD = DEBUGFIELD;
        }

        public String getSYL() {
            return SYL;
        }

        public void setSYL(String SYL) {
            this.SYL = SYL;
        }

        public String getSYLTYPE() {
            return SYLTYPE;
        }

        public void setSYLTYPE(String SYLTYPE) {
            this.SYLTYPE = SYLTYPE;
        }

        public boolean isIsDelisting() {
            return IsDelisting;
        }

        public void setIsDelisting(boolean IsDelisting) {
            this.IsDelisting = IsDelisting;
        }

        public int getFCodeType() {
            return FCodeType;
        }

        public void setFCodeType(int FCodeType) {
            this.FCodeType = FCodeType;
        }

        public int getSecondFCodeType() {
            return SecondFCodeType;
        }

        public void setSecondFCodeType(int SecondFCodeType) {
            this.SecondFCodeType = SecondFCodeType;
        }

        public String getABBNAME() {
            return ABBNAME;
        }

        public void setABBNAME(String ABBNAME) {
            this.ABBNAME = ABBNAME;
        }

        public String getABBTNAME() {
            return ABBTNAME;
        }

        public void setABBTNAME(String ABBTNAME) {
            this.ABBTNAME = ABBTNAME;
        }

        public Object getEsScore() {
            return EsScore;
        }

        public void setEsScore(Object EsScore) {
            this.EsScore = EsScore;
        }

        public String getForeShortName() {
            return ForeShortName;
        }

        public void setForeShortName(String ForeShortName) {
            this.ForeShortName = ForeShortName;
        }
    }
}
