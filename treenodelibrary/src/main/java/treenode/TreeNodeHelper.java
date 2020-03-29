package treenode;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeHelper {
    public static <T> List<TreeNode<T>> getExpendedChildren(TreeNode<T> parent) {
        List<TreeNode<T>> childrenList = new ArrayList<>();
        List<TreeNode<T>> children = parent.getChildren();
        if (children != null) {
            for (TreeNode<T> child : children) {
                childrenList.add(child);
                if (child.isExpand()) {
                    childrenList.addAll(getExpendedChildren(child));
                }
            }
        }
        return childrenList;
    }

    public static <T> void expandAll(List<TreeNode<T>> parentList) {
        if (parentList == null) return;
        for (TreeNode<T> parent : parentList) {
            parent.setExpand(true);
            expandAll(parent.getChildren());
        }
    }

    public static <T> void collapseAll(List<TreeNode<T>> parentList) {
        if (parentList == null) return;
        for (TreeNode<T> parent : parentList) {
            parent.setExpand(false);
            collapseAll(parent.getChildren());
        }
    }

    public static <T> void expandLevel(List<TreeNode<T>> parentList, int level) {
        if (parentList == null) return;
        for (TreeNode<T> parent : parentList) {
            if (parent.getLevel() < level) {
                parent.setExpand(true);
                expandLevel(parent.getChildren(), level);
            }
        }
    }
}
