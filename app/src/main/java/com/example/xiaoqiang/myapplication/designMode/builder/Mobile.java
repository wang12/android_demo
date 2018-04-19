package com.example.xiaoqiang.myapplication.designMode.builder;

/**
 * @Author: [xiaoqiang]
 * @Description: [Mobile]
 * @CreateDate: [2018/4/19]
 * @UpdateDate: [2018/4/19]
 * @UpdateUser: [xiaoqiang]
 * @UpdateRemark: []
 */

public class Mobile {

    String mMobile;

    public Mobile(String Cpu, String cell, String exterior) {
        mMobile = "生产的手机型号是:" + Cpu + ",电池是:" + cell + ",外观是:" + exterior;
    }

    @Override
    public String toString() {
        return mMobile;
    }

    public static class Builder {
        private String mCPU;
        private String mCell;
        private String mExterior;

        public Builder bindCpu(String cpu) {
            this.mCPU = cpu;
            return this;
        }

        public Builder bindCell(String cell) {
            this.mCell = cell;
            return this;
        }

        public Builder bindExterior(String exterior) {
            this.mExterior = exterior;
            return this;
        }

        public Mobile build() {
            return new Mobile(mCPU, mCell, mExterior);
        }
    }

}
