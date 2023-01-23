package com.example.precioustime_one;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private final ArrayList<Schedule> schedulesArrayList ;
    private OnItemClickListener listener;

    public ScheduleAdapter(ArrayList<Schedule> schedules) {
        this.schedulesArrayList = schedules;
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener=clickListener;

    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View scheduleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem_schedule,parent,false);
        return new ScheduleViewHolder(scheduleView ,listener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule currentSchedule = schedulesArrayList.get(position);
        holder.ScheduleDate.setText("date: " +currentSchedule.getDate());
        holder.ScheduleNew.setText("event: " +currentSchedule.getEvent());
        holder.ScheduleTime.setText("time: " + currentSchedule.getTime());
        holder.ScheduleTeam.setText("team: "+currentSchedule.getTeam());

    }

    @Override
    public int getItemCount() {
        return schedulesArrayList.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder{

        public TextView ScheduleNew;
        public TextView ScheduleDate;
        public TextView ScheduleTime;
        public TextView ScheduleTeam;
        public TextView ScheduleRemove;

        public ScheduleViewHolder(@NonNull View itemView ,OnItemClickListener listener) {
                super(itemView);
            ScheduleNew = itemView.findViewById(R.id.textViewSchedule);
            ScheduleDate = itemView.findViewById(R.id.textViewDate);
            ScheduleTime = itemView.findViewById(R.id.textViewTime1);
            ScheduleTeam =itemView.findViewById(R.id.textView_team2);
            ScheduleRemove = itemView.findViewById(R.id.textView_remove);

            ScheduleRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

}
