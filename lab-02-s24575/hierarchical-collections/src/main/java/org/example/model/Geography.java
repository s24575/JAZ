package org.example.model;

import org.example.model.abstraction.IHaveHierarchicalStructure;

import java.util.List;
import java.util.ArrayList;

public class Geography implements IHaveHierarchicalStructure<Geography> {
    private int id;
    private String name;
    private String type;
    private String code;
    private Integer parentId;
    private Geography parent;
    private List<Geography> children = new ArrayList<Geography>();

    public Geography() {}
    public Geography(int id, String name, String type, String code, Integer parentId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.code = code;
        this.parentId = parentId;
    }

    @Override
    public void setParent(Geography parent){
        this.parent = parent;
    }

    @Override
    public Geography getParent(){
        return parent;
    }

    @Override
    public List<Geography> getChildren(){
        return this.children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
