package edu.ndsu.cs.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
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

        List<String> challenges = getStrings();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, challenges);
        listView.setAdapter(adapter);

        buttonMainActivity.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private static List<String> getStrings() {
        List<String> challenges = new ArrayList<>();
        challenges.add("Device Fragmentation - making an application work on devices that can have different settings");
        challenges.add("User Experience - If the users do not like the app it will be quickly dropped on the App store");
        challenges.add("Battery Drain - The more complex the app the faster the battery usage");
        challenges.add("Security - Since the numerous wireless connections a mobile device will have managing them can be a struggle");
        challenges.add("Enabling Integration - Users want all apps to be integrable across the whole device");
        return challenges;
    }
}
