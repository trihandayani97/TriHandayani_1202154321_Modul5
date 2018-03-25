package com.example.trihandayani.trihandayani_1202154321_modul5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.LinkedList;

/**
 * Created by HMJ on 3/25/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private LinkedList<ToDoItem> list;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, LinkedList<ToDoItem> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_layout, parent, false);
        return new RecyclerViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.activityNameView.setText(list.get(position).getName());
        holder.descView.setText(list.get(position).getDesc());
        holder.priorityView.setText(list.get(position).getPriority());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView activityNameView, descView, priorityView;
        RecyclerViewAdapter adapter;

        public RecyclerViewHolder(View itemView, RecyclerViewAdapter adapter) {
            super(itemView);
            activityNameView = (TextView) itemView.findViewById(R.id.editText_activity_name);
            descView = (TextView) itemView.findViewById(R.id.editText_desc);
            priorityView = (TextView) itemView.findViewById(R.id.editText_priority);
            this.adapter = adapter;
        }
    }

    public void delData(int pos){
        SQLiteHelper db = new SQLiteHelper(inflater.getContext());
        boolean deleted = db.delete(list.get(pos));
        if(deleted){
            list.remove(pos);
            this.notifyItemRemoved(pos);
            Toast.makeText(inflater.getContext(), "List is deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(inflater.getContext(), "List is failed to delete", Toast.LENGTH_SHORT).show();
        }
    }
}
