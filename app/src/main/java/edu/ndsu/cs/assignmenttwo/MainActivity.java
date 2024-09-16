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

        buttonExplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Explicit Intent
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        buttonImplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implicit Intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
