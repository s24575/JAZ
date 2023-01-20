package org.example.model.abstraction;

import java.util.List;

public interface IHaveHierarchicalStructure<TItem> {
    public void setParent(TItem item);
    public TItem getParent();
    public List<TItem> getChildren();
    public int getId();
    public Integer getParentId();
}
