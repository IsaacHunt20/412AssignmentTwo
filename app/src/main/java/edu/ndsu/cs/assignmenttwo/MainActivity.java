package edu.ndsu.cs.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonExplicit = findViewById(R.id.buttonExplicit);
        Button buttonImplicit = findViewById(R.id.buttonImplicit);
        Button viewImageActivityButton = findViewById(R.id.view_image_activity_button);

        buttonExplicit.setOnClickListener(v -> {
            // Explicit Intent
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        buttonImplicit.setOnClickListener(v -> {
            // Implicit Intent
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClass(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        viewImageActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intent);
        });
    }
}
