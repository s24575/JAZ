package org.example.tools;

import org.example.model.abstraction.IHaveHierarchicalStructure;

import java.util.List;

public class Hierarchy<TItem extends IHaveHierarchicalStructure<TItem>> {
    private TItem rootElement;

    public void setRootElement(TItem rootElement){
        this.rootElement = rootElement;
    }

    private TItem lastFoundElement;
    public boolean searchHierarchy(TItem root, int id){
        if(root.getId() == id){
            lastFoundElement = root;
            return true;
        }
        for(TItem item : root.getChildren()){
            if(searchHierarchy(item, id)) return true;
        }
        return false;
    }

    public TItem findElementById(int id){
        if(searchHierarchy(rootElement, id)) return lastFoundElement;
        return rootElement;
    }
}
