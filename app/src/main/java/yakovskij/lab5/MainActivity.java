package yakovskij.lab5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int DIALOG_REQUEST_CODE = 1;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.MyName);
    }

    @Override
    protected void onActivityResult(int requestCode, int result, @Nullable Intent data) {

        if (requestCode == 1 && data != null) {
            String s = data.getStringExtra("newn");
            boolean s1 = data.getBooleanExtra("news1", false);
            boolean s2 = data.getBooleanExtra("news2", false);
            CheckBox a = findViewById(R.id.checkBox);
            CheckBox b = findViewById(R.id.checkBox2);
            txt.setText(s);
            a.setChecked(s1);
            b.setChecked(s2);
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

        }
        super.onActivityResult(requestCode, result, data);
    }

    public void openDialogActivity(View view) {
        showCustomDialog();
    }
    public void openActivity(View v){
        Intent i = new Intent(this, SecAsActivity.class);
        i.putExtra("myname", txt.getText().toString());
        CheckBox a = findViewById(R.id.checkBox);
        CheckBox b = findViewById(R.id.checkBox2);
        i.putExtra("boolch1", a.isChecked());
        i.putExtra("boolch2", b.isChecked());
        startActivityForResult(i, 1);
        txt.setText(i.getStringExtra("newn"));
    }

    private void showCustomDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_sec, null);
        dialogBuilder.setView(dialogView);

        EditText editText = dialogView.findViewById(R.id.DialogText);
        CheckBox ma = findViewById(R.id.checkBox);
        CheckBox mb = findViewById(R.id.checkBox2);
        Switch a = dialogView.findViewById(R.id.switch1);
        Switch b = dialogView.findViewById(R.id.switch2);
        editText.setText(txt.getText().toString());
        a.setChecked(ma.isChecked());
        b.setChecked(mb.isChecked());

        dialogBuilder.setTitle("Dialog Window");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                txt.setText(editText.getText().toString());
                ma.setChecked(a.isChecked());
                mb.setChecked(b.isChecked());
                Toast.makeText(MainActivity.this,txt.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(MainActivity.this,"Nothing Happened",Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog customDialog = dialogBuilder.create();
        customDialog.show();
    }
    public void ok_cancel_click(View v){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Подтверждение")
                .setMessage("Вы хотите выйти?")

                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setResult(RESULT_OK);
                        finish();
                    }
                })

                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                    }
                })
                .show();

    }
}