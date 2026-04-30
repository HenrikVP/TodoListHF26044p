// Package declaration for the TodoList application
package dk.tec.todolisthf26044p;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// MainActivity class: Entry point for the TodoList app
public class MainActivity extends AppCompatActivity {

    // SharedPreferences for storing todo list data locally
    SharedPreferences prefs;
    // List to hold TodoItem objects
    public List<TodoItem> todoItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge display for immersive UI
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Adjust padding to account for system bars (status/navigation bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize SharedPreferences for data persistence
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        // Load saved todo items from SharedPreferences
        todoItemList = load();

        // Retrieve any new TodoItem passed from CreateActivity
        getTodoItemFromCreate();
        // Set up the Floating Action Button (FAB) for adding new tasks
        initFloatingActionButton();
        // Initialize and configure the RecyclerView to display todo items
        initRecyclerview();
    }

    // Retrieves a TodoItem from the Intent extras (if available)
    // This is used when returning from CreateActivity with a new task
    private void getTodoItemFromCreate() {
        TodoItem todoItem = (TodoItem) getIntent().getSerializableExtra("TodoItem");
        if (todoItem != null) {
            // Add the new TodoItem to the list
            todoItemList.add(todoItem);
            // Save the updated list to SharedPreferences
            save(todoItemList);
        }
    }

    // Initializes the Floating Action Button (FAB) to open CreateActivity
    private void initFloatingActionButton() {
        FloatingActionButton fob = findViewById(R.id.fab);
        fob.setOnClickListener(view -> {
            // Launch CreateActivity to add a new todo item
            Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
            startActivity(intent);
        });
    }

    // Initializes the RecyclerView to display the list of todo items
    private void initRecyclerview() {
        RecyclerView recyclerView = findViewById(R.id.rv_todoitemlist);
        // Set a LinearLayoutManager for vertical scrolling
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Create and set the adapter for the RecyclerView
        TodoItemAdapter adapter = new TodoItemAdapter(todoItemList);
        recyclerView.setAdapter(adapter);
    }

    // Loads the list of TodoItems from SharedPreferences
    // Returns an empty list if no data is found
    List<TodoItem> load() {
        String json = prefs.getString("todolist", null);
        if (json == null) return new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<TodoItem>>() {}.getType();
        // Deserialize JSON string back to a List<TodoItem>
        return gson.fromJson(json, type);
    }

    // Saves the current list of TodoItems to SharedPreferences as JSON
    void save(List<TodoItem> list) {
        SharedPreferences.Editor edit = prefs.edit();
        // Serialize the list to JSON and save it
        edit.putString("todolist", new Gson().toJson(list));
        edit.apply(); // Asynchronously commit changes
    }
}