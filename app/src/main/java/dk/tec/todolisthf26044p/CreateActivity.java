package dk.tec.todolisthf26044p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDateTime;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton finishButton = findViewById(R.id.btn_finish);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = findViewById(R.id.edit_title);
                String title = editText.getText().toString();
                TodoItem todoItem = new TodoItem(title, LocalDateTime.now(), false);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("TodoItem", todoItem);
                startActivity(intent);
            }
        });

    }
}