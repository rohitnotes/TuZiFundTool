package com.bryant.tuzifundtool.bean;

import java.util.List;

public class SzFundBean {


    /**
     * rc : 0
     * rt : 11
     * svr : 182482210
     * lt : 1
     * full : 1
     * data : {"total":1,"diff":[{"f1":2,"f2":2907.06,"f3":0.03,"f14":"上证指数"}]}
     */

    private int rc;
    private int rt;
    private long svr;
    private int lt;
    private int full;
    private DataBean data;

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public long getSvr() {
        return svr;
    }

    public void setSvr(long svr) {
        this.svr = svr;
    }

    public int getLt() {
        return lt;
    }

    public void setLt(int lt) {
        this.lt = lt;
    }

    public int getFull() {
        return full;
    }

    public void setFull(int full) {
        this.full = full;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 1
         * diff : [{"f1":2,"f2":2907.06,"f3":0.03,"f14":"上证指数"}]
         */

        private int total;
        private List<DiffBean> diff;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DiffBean> getDiff() {
            return diff;
        }

        public void setDiff(List<DiffBean> diff) {
            this.diff = diff;
        }

        public static class DiffBean {
            /**
             * f1 : 2
             * f2 : 2907.06
             * f3 : 0.03
             * f14 : 上证指数
             */

            private int f1;
            private double f2;
            private double f3;
            private String f14;

            public int getF1() {
                return f1;
            }

            public void setF1(int f1) {
                this.f1 = f1;
            }

            public double getF2() {
                return f2;
            }

            public void setF2(double f2) {
                this.f2 = f2;
            }

            public double getF3() {
                return f3;
            }

            public void setF3(double f3) {
                this.f3 = f3;
            }

            public String getF14() {
                return f14;
            }

            public void setF14(String f14) {
                this.f14 = f14;
            }
        }
    }
}
