package com.tec.tree;

import java.util.ArrayList;
import java.util.List;

/**  
 * @ClassName: NewTree  
 * @Description: (这里用一句话描述这个类的作用)  
 * @author sunlei
 *    
 */
public class TreeNode {
    private String id;
    private String parentId;
    private List<TreeNode> children = new ArrayList<TreeNode>();
    private String name;
    public TreeNode(){
    }
    /**
     * Constructors.
     * @param id
     * @param parentId
     * @param name
     */
    public TreeNode(String id, String parentId, String name) {
        super();
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        
    }
    /**
     * @return id - {return content description}
     */
    public String getId() {
        return id;
    }
    /**
     * @param id - {parameter description}.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return parentId - {return content description}
     */
    public String getParentId() {
        return parentId;
    }
    /**
     * @param parentId - {parameter description}.
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    /**
     * @return name - {return content description}
     */
    public String getName() {
        return name;
    }
    /**
     * @param name - {parameter description}.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return children - {return content description}
     */
    public List<TreeNode> getChildren() {
        return children;
    }
    /**
     * @param children - {parameter description}.
     */
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
