package treenode;

import android.content.Context;
import android.support.annotation.NonNull;


import java.util.List;

public abstract class SimpleTreeNodeAdapter<T> extends TreeNodeAdapter<T> {
    /**
     * 构造器
     *
     * @param context  上下文
     * @param rootList 树节点根列表（所有数据）
     */
    public SimpleTreeNodeAdapter(Context context, int layoutId, @NonNull List<TreeNode<T>> rootList) {
        super(context, rootList);
        final int layoutResId = layoutId;
        addItemViewDelegate(new TreeNodeDelegate<T>() {
            @Override
            public boolean isItemType(TreeNode<T> treeNode) {
                return true;
            }

            @Override
            public int getLayoutId() {
                return layoutResId;
            }

            @Override
            public void convert(ViewHolder holder, TreeNode<T> treeNode) {
                SimpleTreeNodeAdapter.this.convert(adapter, holder, treeNode);
            }
        });
    }

    public abstract void convert(TreeNodeAdapter<T> adapter, ViewHolder holder, TreeNode<T> treeNode);
}
