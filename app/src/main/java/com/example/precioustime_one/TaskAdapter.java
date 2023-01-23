package com.example.precioustime_one;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


    public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

        private final ArrayList<Task> tasksArrayList;
        private OnItemClickListener listener;
        public TaskAdapter(ArrayList<Task> tasks)
        {
            this.tasksArrayList = tasks;
        }

        public interface OnItemClickListener{
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener clickListener){
            listener=clickListener;
        }

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View taskView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem_task, parent, false);
            return new TaskViewHolder(taskView,listener);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
            Task currentTask = tasksArrayList.get(position);
            holder.taskDescription.setText(currentTask.getDescriptionTask());
            holder.taskOwner.setText(currentTask.getOwnerTaskName());
            holder.taskStatus.setText(currentTask.getStatusTask());
            holder.taskTeam.setText(currentTask.getTeamTask());



        }



        @Override
        public int getItemCount() {
            return tasksArrayList.size();
        }

        public static class TaskViewHolder extends RecyclerView.ViewHolder{

            public TextView taskDescription;
            public TextView taskOwner;
            public TextView taskStatus;
            public TextView taskUpdate;
            public TextView taskTeam;

            public TaskViewHolder(@NonNull View itemView, OnItemClickListener listener) {
                super(itemView);
                taskDescription = itemView.findViewById(R.id.textViewDis);
                taskOwner= itemView.findViewById(R.id.textViewRespons);
                taskStatus = itemView.findViewById(R.id.textViewStatus);
                taskUpdate = itemView.findViewById(R.id.textView_index);
                taskTeam = itemView.findViewById(R.id.textView_team1);

                taskUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(getAdapterPosition());
                    }
                });

            }
        }

}
