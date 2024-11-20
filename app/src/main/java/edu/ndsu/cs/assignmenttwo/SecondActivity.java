package edu.ndsu.cs.assignmenttwo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private static final String MSE412_PERMISSION = "edu.ndsu.cs.assignmenttwo.permission.MSE412";
    private ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Register the permission request launcher
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean isGranted) {
                        if (isGranted) {
                            // If permission granted, proceed to initialize UI
                            initUI();
                        } else {
                            // If permission denied, show a rationale dialog
                            showPermissionDeniedDialog();
                        }
                    }
                });

        if (ContextCompat.checkSelfPermission(this, MSE412_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted, initialize UI
            initUI();
        } else {
            // Request permission
            requestPermissionLauncher.launch(MSE412_PERMISSION);
        }
    }

    private void initUI() {
        // If permission is granted, initialize the ListView and buttons
        ListView listView = findViewById(R.id.listViewChallenges);
        Button buttonMainActivity = findViewById(R.id.buttonMainActivity);

        // Prepare the data for challenges
        List<String> challenges = getStrings();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, challenges);
        listView.setAdapter(adapter);

        // Set button click listener to navigate to MainActivity
        buttonMainActivity.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @NonNull
    private static List<String> getStrings() {
        List<String> challenges = new ArrayList<>();
        challenges.add("Device Fragmentation - making an application work on devices that can have different settings");
        challenges.add("User Experience - If the users do not like the app it will be quickly dropped on the App store");
        challenges.add("Battery Drain - The more complex the app the faster the battery usage");
        challenges.add("Security - Since the numerous wireless connections a mobile device will have managing them can be a struggle");
        challenges.add("Enabling Integration - Users want all apps to be integrable across the whole device");
        return challenges;
    }

    private void showPermissionDeniedDialog() {
        // Show a dialog asking the user to retry if permission is denied
        new android.app.AlertDialog.Builder(this)
                .setTitle("Permission Denied")
                .setMessage("You denied this permission. The app requires this permission to proceed.")
                .setPositiveButton("Retry", (dialogInterface, i) -> {
                    // Retry permission request if user presses retry
                    requestPermissionLauncher.launch(MSE412_PERMISSION);
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                    // Navigate back to MainActivity if user cancels
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }
}
