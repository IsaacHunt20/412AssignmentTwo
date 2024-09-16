package edu.ndsu.cs.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ListView listView = findViewById(R.id.listViewChallenges);
        Button buttonMainActivity = findViewById(R.id.buttonMainActivity);

        List<String> challenges = new ArrayList<>();
        challenges.add("Challenge");
        challenges.add("Challenge");
        challenges.add("Challenge");
        challenges.add("Challenge");
        challenges.add("Challenge");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, challenges);
        listView.setAdapter(adapter);

        buttonMainActivity.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
