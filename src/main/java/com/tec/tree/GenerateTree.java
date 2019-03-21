package com.tec.tree;

import java.util.ArrayList;
import java.util.List;

/**  
 * @ClassName: GenerateTree  
 * @Description: 生成树  
 * @author sunlei
 */
public abstract class GenerateTree {
    /**
     * 构建树.
     * @param nodes 节点
     * @return Tree<T> 
     */
    public static <T> TreeNode build(final List<TreeNode> nodes) {
        if (nodes == null) {
            return null;
        }
        List<TreeNode> topNodes = new ArrayList<TreeNode>();
        for (TreeNode children : nodes) {
            String parentId = children.getParentId();
            if (parentId == null || "".equals(parentId)) {
                topNodes.add(children);
                continue;
            }
            //将子节点全部放到根节点下
            for (TreeNode parent : nodes) {
                String id = parent.getId();
                if (id != null && id.equals(parentId)) {
                    parent.getChildren().add(children);
                    continue;
                }
            }
        }
        TreeNode root = new TreeNode();
        if (topNodes.size() == 1) {
            root = topNodes.get(0);
        } else {
            root.setId("-1");
            root.setParentId("");
            root.setChildren(topNodes);
            root.setName("根节点");
        }
        return root;
    }
}
