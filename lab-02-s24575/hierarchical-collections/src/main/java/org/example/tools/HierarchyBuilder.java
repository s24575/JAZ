package org.example.tools;

import org.example.model.abstraction.IHaveHierarchicalStructure;

import java.util.HashMap;
import java.util.List;

public class HierarchyBuilder<TItem extends IHaveHierarchicalStructure<TItem>> {
    private List<TItem> items;
    private TItem rootElement;

    public void setElements(List<TItem> items){
        this.items = items;
    }

    public void buildHierarchy(){
        HashMap<Integer,TItem> map = new HashMap<>();

        for(TItem item : items){
            map.put(item.getId(), item);
        }
        
        // give parent, add child
        for(TItem item : items){
            if(item.getParentId() == null) {
                rootElement = item;
            }
            else if(map.containsKey(item.getParentId())){
                TItem parent = map.get(item.getParentId());
                item.setParent(parent);
                parent.getChildren().add(item);
            }
        }
    }

    public TItem getRootElement(){
        return rootElement;
    }
}
