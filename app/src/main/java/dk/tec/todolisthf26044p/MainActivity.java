package dk.tec.todolisthf26044p;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

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

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    public List<TodoItem> todoItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        //Todo Load TodoItemList
        todoItemList = load();

        TodoItem todoItem = (TodoItem)getIntent().getSerializableExtra("TodoItem");
        if (todoItem != null)
        {
            todoItemList.add(todoItem);
            save(todoItemList);
            //adapter.notifyDataSetChanged();
            //TODO Save item to our list if todoItem is not null
        }

        FloatingActionButton fob = findViewById(R.id.fab);
        fob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rv_todoitemlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TodoItemAdapter adapter = new TodoItemAdapter(todoItemList);
        recyclerView.setAdapter(adapter);
    }

    List<TodoItem> load()
    {
        String json = prefs.getString("todolist", null);
        if (json == null) return new ArrayList<TodoItem>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<TodoItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    void save(List<TodoItem> list)
    {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("todolist", new Gson().toJson(list));
        edit.apply();
    }
}