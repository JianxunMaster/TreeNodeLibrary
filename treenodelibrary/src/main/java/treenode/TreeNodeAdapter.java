package treenode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeMultiItemTypeAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;// 上下文
    private LayoutInflater layoutInflater;// 视图渲染器
    private List<ItemViewDelegate> itemViewDelegateList;// 所有布局类型
    private List<TreeNode> showTreeNodeList;// 当前显示的树节点
    private List<TreeNode> rootTreeNodeList;// 所有树节点

    /**
     * 构造器
     *
     * @param context  上下文
     * @param rootList 树节点根列表（所有数据）
     */
    public TreeNodeMultiItemTypeAdapter(Context context, @NonNull List<TreeNode> rootList) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.itemViewDelegateList = new ArrayList<>();
        this.showTreeNodeList = new ArrayList<>();
        this.rootTreeNodeList = rootList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewDelegate holder = itemViewDelegateList.get(viewType);
        View itemView = layoutInflater.inflate(holder.getLayoutId(), parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        ItemViewDelegate delegate = itemViewDelegateList.get(viewType);
        delegate.convert(holder, showTreeNodeList.get(position));
    }

    @Override
    public int getItemCount() {
        return showTreeNodeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        TreeNode treeNode = showTreeNodeList.get(position);
        for (int i = 0; i < itemViewDelegateList.size(); i++) {
            ItemViewDelegate type = itemViewDelegateList.get(i);
            if (type.isItemType(treeNode)) {
                return i;
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public void addItemViewDelegate(ItemViewDelegate delegate) {
        itemViewDelegateList.add(delegate);
    }

    public void removeItemViewDelegate(ItemViewDelegate delegate) {
        itemViewDelegateList.remove(delegate);
    }
}
