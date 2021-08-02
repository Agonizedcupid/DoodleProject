package com.aariyan.doodle.Model;

public class SubCategoryModel {

    private String sub_category_name;
    private boolean isSelected = false;

    public SubCategoryModel(){}

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public SubCategoryModel(String sub_category_name) {
        this.sub_category_name = sub_category_name;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public void setSub_category_name(String sub_category_name) {
        this.sub_category_name = sub_category_name;
    }
}
