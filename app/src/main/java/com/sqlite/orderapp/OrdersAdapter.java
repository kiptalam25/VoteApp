package com.sqlite.orderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sqlite.orderapp.services.model.Orders;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder>{
    private static final int MSG_TYPE_LEFT_RECEIVED = 1;
    private static final int MSG_TYPE_RIGHT_RECEIVED = 0;
    private FirebaseUser currentUser_sender = FirebaseAuth.getInstance().getCurrentUser();
    List<Orders> ordersList;
    Context context;

    public OrdersAdapter(List<Orders> ordersList, final Context context) {
        this.ordersList=ordersList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_view, parent, false);
//        return new MyViewHolder(itemView);

        if (viewType == MSG_TYPE_RIGHT_RECEIVED) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right_sent, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left_received, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Orders data = ordersList.get(position);
//        holder.product.setText(data.getName());
//        holder.price.setText(String.valueOf(data.getQuantity()));
//        holder.imageView.setImageResource(R.drawable.image1);
        holder.tv_msg.setText(data.getName());
        String ImageUrl =data.getImageurl();
        ImageUrl="url";
        if(ImageUrl.equals("url")|| ImageUrl.isEmpty()){
//            holder.imageView.setImageResource(R.drawable.food);
        }else {
//            Glide.with(context).load(ImageUrl).into(holder.imageView);
        }

//        Toast.makeText(context, "stdtrsraea", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (ordersList.get(position).getOrderoto().equals(currentUser_sender.getUid())) {
            return MSG_TYPE_LEFT_RECEIVED;
        } else return MSG_TYPE_RIGHT_RECEIVED;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product,price,tv_msg;
        ImageView imageView;
//        ProgressBar progressBar3;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product=itemView.findViewById(R.id.product);
            price=itemView.findViewById(R.id.price);
            imageView=itemView.findViewById(R.id.imageView);
            tv_msg=itemView.findViewById(R.id.tv_chat_received);
//            progressBar3=itemView.findViewById(R.id.progressBar3);
//            progressBar3.setVisibility(View.GONE);
        }
    }
}
