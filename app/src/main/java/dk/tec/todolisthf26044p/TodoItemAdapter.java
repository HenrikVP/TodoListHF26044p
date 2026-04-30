package dk.tec.todolisthf26044p;

// Import classes for creating and managing views
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

// Adapter class connects data (TodoItem list) to RecyclerView UI
public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemAdapter.MyViewHolder> {

    // List containing all todo items to display
    List<TodoItem> todoItemList;

    // Constructor receives the data list
    public TodoItemAdapter(List<TodoItem> todoItemList) {
        this.todoItemList = todoItemList;
    }

    // ViewHolder class represents ONE row/item in the RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        // UI components for each row
        TextView titleview, dateview;

        // Constructor links UI components to layout elements
        public MyViewHolder(View item_row){
            super(item_row);

            // Find views inside item_row layout
            titleview = item_row.findViewById(R.id.tv_title);
            dateview = item_row.findViewById(R.id.tv_date);
        }
    }

    // Called when RecyclerView needs to CREATE a new row (ViewHolder)
    @Override
    public TodoItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate (create) layout from XML file (item_row.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent,false);

        // Return a new ViewHolder with that layout
        return new MyViewHolder(view);
    }

    // Called when RecyclerView needs to DISPLAY data in a row
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        // Get the TodoItem for this position
        TodoItem item = todoItemList.get(position);

        // Set title text
        holder.titleview.setText(item.getTitle());

        // Format date to readable format (e.g. 30-04-2026 14:30)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        // Convert LocalDateTime to formatted string
        String formattedDate = formatter.format(item.getCreate());

        // Set formatted date text
        holder.dateview.setText(formattedDate);
    }

    // Returns total number of items in the list
    @Override
    public int getItemCount() {
        return todoItemList.size();
    }
}