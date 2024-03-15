package yakovskij.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class SecAsActivity extends AppCompatActivity {
    private EditText txt;
    private Switch a;
    private Switch b;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_as);

        Intent i = getIntent();
        EditText txt = findViewById(R.id.DialogTextas);
        Switch a = findViewById(R.id.switch1as);
        Switch b = findViewById(R.id.switch2as);

        txt.setText(i.getStringExtra("myname"));
        a.setChecked(i.getBooleanExtra("boolch1", false));
        b.setChecked(i.getBooleanExtra("boolch2", false));

    }


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public void ok_activity_click(View v){
        Intent i = new Intent();

        EditText txt = findViewById(R.id.DialogTextas);
        Switch a = findViewById(R.id.switch1as);
        Switch b = findViewById(R.id.switch2as);

        i.putExtra("newn", txt.getText().toString());
        i.putExtra("news1", a.isChecked());
        i.putExtra("news2", b.isChecked());

        setResult(RESULT_OK, i);
        finish();
    }
    public void cancel_activity_click(View v){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you sure to Exit")
                .setMessage("Exiting will call finish() method")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }
}