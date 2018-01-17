package info.adnansharif.trafficprediction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // UI widget items
    private Spinner mSource;
    private Spinner mDestination;
    private Spinner mValueSource;
    private Spinner mValueDestination;
    private EditText mValue;
    private HashMap<String, Integer> mPlace;
    private ArrayList<Double> mLattitude;
    private ArrayList<Double> mLongitude;
    private static double expected_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize all the UI Widgets
        mSource = findViewById(R.id.source_name_spinner);
        mDestination = findViewById(R.id.destination_name_spinner);
        mValueSource = findViewById(R.id.value_source_name_spinner);
        mValueDestination = findViewById(R.id.value_destination_name_spinner);
        mValue = findViewById(R.id.value);
        mPlace = new HashMap<>();
        mLongitude = new ArrayList<>();
        mLattitude = new ArrayList<>();
        expected_time = 0.0;

        List<String> items = new ArrayList<>();
        items.add("Please select a place");
        items.add("Mirpur-12");
        items.add("Mirpur-10");

        mPlace.put("Mirpur-12", 0);
        mPlace.put("Mirpur-10", 1);

        mLattitude.add(23.8280274);
        mLattitude.add(23.804488);

        mLongitude.add(90.3618099);
        mLongitude.add(90.3615719);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSource.setAdapter(adapter);
        mDestination.setAdapter(adapter);
        mValueSource.setAdapter(adapter);
        mValueDestination.setAdapter(adapter);
    }

    public void showMap(View view) {
        String source = mSource.getSelectedItem().toString();
        String destination = mDestination.getSelectedItem().toString();
        if(source.equals(destination)){
            Toast.makeText(getApplicationContext(), "Source and destination can't be identical", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("lat1", mLattitude.get(mPlace.get(source)));
        intent.putExtra("long1", mLongitude.get(mPlace.get(source)));
        intent.putExtra("lat2", mLattitude.get(mPlace.get(destination)));
        intent.putExtra("long2", mLongitude.get(mPlace.get(destination)));
        intent.putExtra("time", String.valueOf(expected_time));

        startActivity(intent);

    }

    public void saveData(View view) {
        Random random = new Random(System.currentTimeMillis());
        String value = mValue.getText().toString();
        double v =  Double.valueOf(value);
        if(expected_time == 0 ) expected_time= v;
        else{
            double tmp = Math.abs(expected_time-v);
            if(v>=expected_time) expected_time+= random.nextDouble()*tmp;
            else expected_time-= random.nextDouble()*tmp;
        }
        Toast.makeText(getApplicationContext(), "Your response is submitted!", Toast.LENGTH_SHORT).show();
    }
}
