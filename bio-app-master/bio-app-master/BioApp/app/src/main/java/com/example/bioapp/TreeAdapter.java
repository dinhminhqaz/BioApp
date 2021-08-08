package com.example.bioapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.List;

public class TreeAdapter extends ArrayAdapter<TreeInfo> {
    private List<TreeInfo> treeInfoList;
    private Context context;
    private Integer[] imgid;

    public TreeAdapter(Context context, int resource, List<TreeInfo> treeInfoList,Integer[] imgid){
        super(context, resource);
        this.treeInfoList = treeInfoList;

    }

    @Override
    public int getCount(){
        return treeInfoList.size();
    }
    @Override
    public TreeInfo getItem(int Position){
        return treeInfoList.get(Position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TreeInfo treeInfo = getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tree_layout,parent,false);
        }
        TextView TreeName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView TreeDes = (TextView) convertView.findViewById(R.id.tv_des);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView2);
        TreeName.setText(treeInfo.getName());
        TreeDes.setText(treeInfo.getDescription());

        imageView.setImageResource(treeInfo.getImageid());

        return convertView;
    }
}
