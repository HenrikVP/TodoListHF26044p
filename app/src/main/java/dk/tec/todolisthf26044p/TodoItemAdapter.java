package dk.tec.todolisthf26044p;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemAdapter.MyViewHolder> {

    List<TodoItem> todoItemList;

    public TodoItemAdapter(List<TodoItem> todoItemList) {
        this.todoItemList = todoItemList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView titleview, dateview;

        public MyViewHolder(View item_row){
            super(item_row);
            titleview = item_row.findViewById(R.id.tv_title);
            dateview = item_row.findViewById(R.id.tv_date);
        }
    }

    @Override
    public TodoItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TodoItem item = todoItemList.get(position);
        holder.titleview.setText(item.getTitle());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = formatter.format(item.getCreate());

        holder.dateview.setText(formattedDate);
     }

    @Override
    public int getItemCount() {
        return todoItemList.size();
    }
}
