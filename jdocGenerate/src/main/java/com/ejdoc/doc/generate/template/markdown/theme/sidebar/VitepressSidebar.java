package com.ejdoc.doc.generate.template.markdown.theme.sidebar;

import java.util.List;

public class VitepressSidebar {

    private String text;

    private List<VitepressSidebarItem> items;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<VitepressSidebarItem> getItems() {
        return items;
    }

    public void setItems(List<VitepressSidebarItem> items) {
        this.items = items;
    }
}
