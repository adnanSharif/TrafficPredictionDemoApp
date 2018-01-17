package info.adnansharif.trafficprediction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // UI widget items
    private Spinner mSource;
    private Spinner mDestination;
    private Spinner mValueSource;
    private Spinner mValueDestination;
    private EditText mValue;


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

        List<String> items = new ArrayList<>();
        items.add("Please select a place");
        items.add("Mirpur-12");
        items.add("Mirpur-10");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSource.setAdapter(adapter);
        mDestination.setAdapter(adapter);
        mValueSource.setAdapter(adapter);
        mValueDestination.setAdapter(adapter);
    }
}
