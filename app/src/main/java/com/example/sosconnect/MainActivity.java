package com.example.sosconnect;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private RecyclerView recyclerContacts;
    private ArrayList<ContactModel> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ✅ Make status bar icons dark (if light theme is active)
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        recyclerContacts = findViewById(R.id.RecyclerContacts);
        recyclerContacts.setLayoutManager(new LinearLayoutManager(this));

        contactList = new ArrayList<>();
        populateContactList();

        RecyclerContactAdapter adapter = new RecyclerContactAdapter(this, contactList);
        recyclerContacts.setAdapter(adapter);

        checkPermissions();
    }

    private void populateContactList() {
        contactList.add(new ContactModel(R.drawable.police, "Police", "100", "Emergency Help Needed!", "police@example.com"));
        contactList.add(new ContactModel(R.drawable.fire, "Fire Brigade", "101", "Fire emergency at my location", "fire@example.com"));
        contactList.add(new ContactModel(R.drawable.ambulance, "Ambulance", "102", "Medical emergency. Need ambulance", "ambulance@example.com"));
        contactList.add(new ContactModel(R.drawable.traffic_police, "Traffic Police", "103", "Traffic-related emergency", "traffic@example.com"));
        contactList.add(new ContactModel(R.drawable.health, "State Health Helpline", "104", "Need health-related help", "health@example.com"));
        contactList.add(new ContactModel(R.drawable.disaster, "Disaster Management", "108", "Disaster or medical emergency", "disaster@example.com"));
        contactList.add(new ContactModel(R.drawable.emergency, "All-in-One Emergency", "112", "Emergency! Need help urgently", "help@example.com"));
        contactList.add(new ContactModel(R.drawable.railway, "Indian Railway Enquiry", "131", "Need railway info", "railgen@example.com"));
        contactList.add(new ContactModel(R.drawable.railway, "Railway Enquiry", "139", "Train enquiry help needed", "railinfo@example.com"));
        contactList.add(new ContactModel(R.drawable.telecom, "Telephone Complaint", "198", "Facing telecom service issues", "telecom@example.com"));
        contactList.add(new ContactModel(R.drawable.corruption, "Anti-Corruption Helpline", "1031", "Report corruption", "corruption@example.com"));
        contactList.add(new ContactModel(R.drawable.poison, "Anti-Poison Center", "1066", "Poisoning case reported", "poison@example.com"));
        contactList.add(new ContactModel(R.drawable.airplane, "Air Accident", "1071", "Reporting air accident", "airaccident@example.com"));
        contactList.add(new ContactModel(R.drawable.railway, "Train Accident", "1072", "Train accident help needed", "trainaccident@example.com"));
        contactList.add(new ContactModel(R.drawable.terror, "Anti-Terror Helpline", "1090", "Suspicious activity reported", "terror@example.com"));
        contactList.add(new ContactModel(R.drawable.disaster, "Disaster Control Room", "1096", "Reporting natural disaster", "disastercontrol@example.com"));
        contactList.add(new ContactModel(R.drawable.child, "Child Abuse Helpline", "1098", "Report child abuse", "childline@example.com"));
        contactList.add(new ContactModel(R.drawable.trauma, "Accident & Trauma Services", "1099", "Accident emergency", "trauma@example.com"));
        contactList.add(new ContactModel(R.drawable.kisan, "Kisan Call Center", "1551", "Farmer support needed", "kisan@example.com"));
        contactList.add(new ContactModel(R.drawable.lpg, "LPG Emergency", "1906", "LPG leak or gas emergency", "lpg@example.com"));
        contactList.add(new ContactModel(R.drawable.blood, "Blood Bank Info", "1910", "Need blood urgently", "bloodbank@example.com"));
        contactList.add(new ContactModel(R.drawable.eye, "Eye Donation Services", "1919", "Eye donation info needed", "eyebank@example.com"));
        contactList.add(new ContactModel(R.drawable.uidai, "Aadhaar - UIDAI", "1947", "Aadhaar card issues", "aadhaar@example.com"));
        contactList.add(new ContactModel(R.drawable.election, "Election Commission", "1950", "Voter ID or election help", "eci@example.com"));
        contactList.add(new ContactModel(R.drawable.road_accident, "Road Accident", "1073", "Road accident emergency", "roadaccident@example.com"));
        contactList.add(new ContactModel(R.drawable.tourist, "Tourist Helpline", "1363", "Help for tourists in need", "tourist@example.com"));
        contactList.add(new ContactModel(R.drawable.women, "Women in Distress", "1091", "Help! I'm in danger", "womenhelp@example.com"));
        contactList.add(new ContactModel(R.drawable.child, "Childline", "1098", "Reporting child abuse", "childline@example.com"));
        contactList.add(new ContactModel(R.drawable.highway, "National Highway Helpline", "1033", "Highway emergency assistance", "highway@example.com"));
        contactList.add(new ContactModel(R.drawable.relief, "Relief Commissioners", "1070", "Need relief support after disaster", "relief@example.com"));
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissions Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permissions Denied. Some features may not work.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
