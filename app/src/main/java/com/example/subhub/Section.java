package com.example.subhub;

import java.util.List;

public class Section {

    private String sectionName;
    private List<LSVideo> sectionItems;

    public Section(String sectionName, List<LSVideo> sectionItems) {
        this.sectionName = sectionName;
        this.sectionItems = sectionItems;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<LSVideo> getSectionItems() {
        return sectionItems;
    }

    public void setSectionItems(List<LSVideo> sectionItems) {
        this.sectionItems = sectionItems;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionName='" + sectionName + '\'' +
                ", sectionItems=" + sectionItems +
                '}';
    }
}
