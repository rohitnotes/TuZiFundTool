package com.bryant.tuzifundtool.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

public class MultipleItemEntity implements MultiItemEntity, Serializable {
    public static final int mItem = 1;
    private int itemType;
    private FundEntity fundEntity;
    private HelpEntity helpEntity;
    public MultipleItemEntity(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public FundEntity getFundEntity() {
        return fundEntity;
    }

    public void setFundEntity(FundEntity fundEntity) {
        this.fundEntity = fundEntity;
    }

    public HelpEntity getHelpEntity() {
        return helpEntity;
    }

    public void setHelpEntity(HelpEntity helpEntity) {
        this.helpEntity = helpEntity;
    }
}
