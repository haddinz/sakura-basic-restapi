package com.sakura.resfullapi.dto;

public class SearchDto {
    private String searchKey;

    private String otherSearchKey;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getOtherSearchKey() {
        return otherSearchKey;
    }

    public void setOtherSearchKey(String otherSearchKey) {
        this.otherSearchKey = otherSearchKey;
    }
}
