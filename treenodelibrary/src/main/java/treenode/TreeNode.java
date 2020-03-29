package treenode;

import java.util.ArrayList;
import java.util.List;

/**
 * 树节点
 */
public class TreeNode<T> {
    private T value;// 数据
    private TreeNode<T> parent;// 父类
    private List<TreeNode<T>> children;// 子集
    private boolean expand;// 是否展开
    private boolean select;// 是否选中

    public TreeNode() {
    }

    public TreeNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
        if (children != null) {
            for (TreeNode<T> child : children) {
                if (child != null) {
                    child.setParent(this);
                }
            }
        }
    }

    public void addChild(TreeNode<T> child) {
        if (child == null) return;
        if (children == null) children = new ArrayList<>();
        children.add(child);
        child.setParent(this);
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

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children == null || children.size() == 0;
    }
}
