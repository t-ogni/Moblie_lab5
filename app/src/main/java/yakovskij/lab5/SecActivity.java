package yakovskij.lab5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SecActivity extends AppCompatDialogFragment {

    private EditText txt;
    private Switch a;
    private Switch b;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_sec, null);

        txt = view.findViewById(R.id.DialogText);
        a = view.findViewById(R.id.switch1);
        b = view.findViewById(R.id.switch2);

        // Установка значений из интента, если они были переданы
        Bundle args = getArguments();
        if (args != null) {
            txt.setText(args.getString("myname"));
            a.setChecked(args.getBoolean("boolch1", false));
            b.setChecked(args.getBoolean("boolch2", false));
        }

        builder.setView(view)
                .setTitle("Dialog Menu")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                        dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Обработка нажатия кнопки "OK"
                        // Создание интента и передача данных обратно в основную активность
                        String newName = txt.getText().toString();
                        boolean newS1 = a.isChecked();
                        boolean newS2 = b.isChecked();

                        // Создание интента и передача данных обратно в основную активность
                        Intent intent = new Intent();
                        intent.putExtra("newn", newName);
                        intent.putExtra("news1", newS1);
                        intent.putExtra("news2", newS2);
                        getTargetFragment().onActivityResult(getTargetRequestCode(), 1, intent);
                        dismiss();
                    }
                });

        return builder.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        setTargetFragment(null, 1);
    }

}