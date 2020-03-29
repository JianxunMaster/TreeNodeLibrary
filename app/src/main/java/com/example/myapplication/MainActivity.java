package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import treenode.TreeNode;
import treenode.TreeNodeAdapter;
import treenode.TreeNodeDelegate;
import treenode.ViewHolder;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<TreeNode<String>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.testView);
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
//        recyclerView.setAdapter(new SimpleTreeNodeAdapter<String>(this, R.layout.item_test_1, list) {
//            @Override
//            public void convert(final TreeNodeAdapter<String> adapter, ViewHolder holder, final TreeNode<String> treeNode) {
//                LinearLayout testLayout = holder.getView(R.id.testLayout);
//                testLayout.setPadding(treeNode.getLevel() * 50, 0, 0, 0);
//                TextView textTv = holder.getView(R.id.textTv);
//                textTv.setText(treeNode.getValue());
//                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        adapter.expandOrCollapseTreeNode(treeNode);
//                    }
//                });
//            }
//        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
