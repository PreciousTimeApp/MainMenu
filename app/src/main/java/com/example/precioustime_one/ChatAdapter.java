package com.example.precioustime_one;

import android.annotation.SuppressLint;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private ArrayList<Chat> chats;

    public ChatAdapter(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleitem_chat,parent,false);

        return new ChatViewHolder(chatView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat currentChat = chats.get(position);
        holder.nameTextView.setText(currentChat.getName());
        holder.messageTextView.setText(currentChat.getMessage());
        holder.timeTextView.setText(currentChat.getTime());
            holder.teamTextView.setText("T: " + currentChat.getTeam());

//        holder.iconTextView.setImageResource(
//                holder.nameTextView.getResources().getIdentifier(currentChat.getIcon(),
//                "drawable",holder.nameTextView.getContext().getPackageName()));

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public static  class ChatViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;
        public TextView messageTextView;
        public TextView timeTextView;
        public TextView teamTextView;

//        public TextView unreadTextView;
//        public ImageView iconTextView;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView =  itemView.findViewById(R.id.textView_group);
            messageTextView = itemView.findViewById(R.id.textView3message);
            timeTextView = itemView.findViewById(R.id.textView_time);
            teamTextView = itemView.findViewById(R.id.textView_unread);
//            unreadTextView = itemView.findViewById(R.id.textView_unread);
//            iconTextView = itemView.findViewById(R.id.imageView_icon);
        }

    }
}
