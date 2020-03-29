# TreeNodeLibrary

   多级列表适配器依赖库
   
   # 添加依赖

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.JianxunMaster:TreeNodeLibrary:1.0.0'
	}
	

[![](https://jitpack.io/v/JianxunMaster/TreeNodeLibrary.svg)](https://jitpack.io/#JianxunMaster/TreeNodeLibrary)



# 简介

    适用于RecyclerView的多级列表适配器，用法简单
    内置选中状态，可设置选中数量
  
# 适配器控件介绍

    通用适配器 TreeNodeAdapter<T>
    简单适配器 SimpleTreeNodeAdapter<T> 
    层级样式管理 TreeNodeDelegate<T>
    树节点 TreeNode<T>
  
# 用法介绍

  简单说明： 
    
    本依赖库只对适配器封装，其他的设置照常使用
    若只有一种item布局，使用SimpleTreeNodeAdapter
    若有多种item布局，使用TreeNodeAdapter，依次添加item布局管理TreeNodeDelegate
    SimpleTreeNodeAdapter其实是TreeNodeAdapter添加了一个TreeNodeDelegate的实例。
  
  具体使用如下：
  
    1 数据源设置，注意泛型的使用，这里以String为例
  
    List<TreeNode<String>> list = new ArrayList<>();
     for (int i = 0; i < 10; i++) {
            TreeNode<String> treeNode0 = new TreeNode<>("0级:" + i);
            list.add(treeNode0);
            for (int j = 0; j < 10; j++) {
                TreeNode<String> treeNode1 = new TreeNode<>("1级:" + j);
                treeNode0.addChild(treeNode1);
                for (int k = 0; k < 10; k++) {
                    TreeNode<String> treeNode2 = new TreeNode<>("2级:" + k);
                    treeNode1.addChild(treeNode2);
                }
            }
        }
  
  2.1 设置相同布局的简单适配器
  
     recyclerView.setAdapter(new SimpleTreeNodeAdapter<String>(this, R.layout.item_test_1, list) {
            @Override
            public void convert(final TreeNodeAdapter<String> adapter, ViewHolder holder, final TreeNode<String> treeNode) {
                LinearLayout testLayout = holder.getView(R.id.testLayout);
                testLayout.setPadding(treeNode.getLevel() * 50, 0, 0, 0);
                TextView textTv = holder.getView(R.id.textTv);
                textTv.setText(treeNode.getValue());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.expandOrCollapseTreeNode(treeNode);
                    }
                });
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
  2.2 设置多种布局的适配器
  
                TreeNodeAdapter<String> adapter = new TreeNodeAdapter<>(this, list);
        adapter.addItemViewDelegate(new TreeNodeDelegate<String>() {
            @Override
            public boolean isItemType(TreeNode<String> treeNode) {
                return treeNode.isRoot();
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_test_1;
            }

            @Override
            public void convert(ViewHolder holder, final TreeNode<String> treeNode) {
                LinearLayout testLayout = holder.getView(R.id.testLayout);
                testLayout.setPadding(treeNode.getLevel() * 50, 0, 0, 0);
                TextView textTv = holder.getView(R.id.textTv);
                textTv.setText(treeNode.getValue());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.expandOrCollapseTreeNode(treeNode);
                    }
                });
            }
        });
        adapter.addItemViewDelegate(new TreeNodeDelegate<String>() {
            @Override
            public boolean isItemType(TreeNode<String> treeNode) {
                return !treeNode.isRoot() && !treeNode.isLeaf();
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_test_2;
            }

            @Override
            public void convert(ViewHolder holder, final TreeNode<String> treeNode) {
                LinearLayout testLayout = holder.getView(R.id.testLayout);
                testLayout.setPadding(treeNode.getLevel() * 50, 0, 0, 0);
                TextView textTv = holder.getView(R.id.textTv);
                textTv.setText(treeNode.getValue());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.expandOrCollapseTreeNode(treeNode);
                    }
                });
            }
        });
        adapter.addItemViewDelegate(new TreeNodeDelegate<String>() {
            @Override
            public boolean isItemType(TreeNode<String> treeNode) {
                return treeNode.isLeaf();
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_test_3;
            }

            @Override
            public void convert(ViewHolder holder, final TreeNode<String> treeNode) {
                LinearLayout testLayout = holder.getView(R.id.testLayout);
                testLayout.setPadding(treeNode.getLevel() * 50, 0, 0, 0);
                TextView textTv = holder.getView(R.id.textTv);
                textTv.setText(treeNode.getValue());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.expandOrCollapseTreeNode(treeNode);
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
# 适配器的其他功能

    /**
     * 添加视图样式
     *
     * @param delegate 样式
     */
    public void addItemViewDelegate(TreeNodeDelegate<T> delegate)

    /**
     * 移除视图样式
     *
     * @param delegate 样式
     */
    public void removeItemViewDelegate(TreeNodeDelegate<T> delegate)

    /**
     * 刷新
     */
    public void refreshTreeNode() 
    
    /**
     * 展开树节点
     *
     * @param treeNode 树节点
     */
    public void expandTreeNode(TreeNode<T> treeNode) 

    /**
     * 收缩树节点
     *
     * @param treeNode 树节点
     */
    public void collapseTreeNode(TreeNode<T> treeNode) 

    /**
     * 伸缩书树节点
     *
     * @param treeNode 树节点
     */
    public void expandOrCollapseTreeNode(TreeNode<T> treeNode) 

    /**
     * 刷新树节点
     *
     * @param treeNode 树节点
     */
    public void notifyTreeNode(TreeNode<T> treeNode) 

    /**
     * 展开所有层级的树节点
     */
    public void expandAllTreeNode() 

    /**
     * 收缩所有层级的树节点
     */
    public void collapseAllTreeNode()
    
    /**
     * 展开层级内的树节点
     *
     * @param level 层级
     */
    public void expandLevelTreeNode(int level)

    /**
     * 设置可选树节点最大数量
     *
     * @param maxSelectCount 数量
     */
    public void setMaxSelectCount(int maxSelectCount)

    /**
     * 获取选中树节点集合
     *
     * @return 选中树节点集合
     */
    public List<TreeNode<T>> getSelectTreeNodeList() 

    /**
     * 改变选中状态，并刷新
     * 当限制最多选中数量，超出的部分会把先加入的树节点移除
     *
     * @param treeNode 树节点
     * @param select   选中状态
     */
    public void selectTreeNode(TreeNode<T> treeNode, boolean select)

    /**
     * 改变树节点选中状态
     *
     * @param treeNode 树节点
     */
    public void changeSelectTreeNode(TreeNode<T> treeNode)
