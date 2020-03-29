package com.library.treenode;

import java.util.List;

/**
 * 树节点
 */
public class TreeNode {
    private Object value;// 数据
    private TreeNode parent;// 父类
    private List<TreeNode> children;// 子集
    private int type;// 类型
    private boolean expand;// 是否展开

    public TreeNode() {
    }

    public TreeNode(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
        if (children != null) {
            for (TreeNode child : children) {
                if (child != null) {
                    child.setParent(this);
                }
            }
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children == null || children.size() == 0;
    }
}
