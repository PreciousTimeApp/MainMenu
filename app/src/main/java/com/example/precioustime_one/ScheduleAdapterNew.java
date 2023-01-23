package com.example.precioustime_one;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleAdapterNew extends RecyclerView.Adapter<ScheduleAdapterNew.ScheduleViewHolder> {

    ArrayList<Schedule> schedules;

    public ScheduleAdapterNew(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View scheduleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem_schedule, parent,false);

        return new ScheduleViewHolder(scheduleView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule currentSchedule = schedules.get(position);
        holder.ScheduleDate.setText(currentSchedule.getDate());
        holder.ScheduleNew.setText(currentSchedule.getEvent());
        holder.ScheduleTime.setText(currentSchedule.getTime());
        holder.ScheduleTeam.setText(currentSchedule.getTeam());
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView ScheduleNew;
        public TextView ScheduleDate;
        public TextView ScheduleTime;
        public TextView ScheduleTeam;


        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            ScheduleNew = itemView.findViewById(R.id.textViewSchedule);
            ScheduleDate = itemView.findViewById(R.id.textViewDate);
            ScheduleTime = itemView.findViewById(R.id.textViewTime1);
            ScheduleTeam =itemView.findViewById(R.id.textView_team2);
        }
    }
}
