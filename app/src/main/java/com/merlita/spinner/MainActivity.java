package com.merlita.spinner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner lista;
    TextView tv;
    Button bt;
    EditText et1, et2;

    private static String[] operaciones = {"+", "-", "*", "/", "^"};
    private String opcion="+";


    private final int id1 = R.id.MnuOpc1;
    private final int id2 = R.id.MnuOpc2;
    private final int id3 = R.id.MnuOpc3;
    private final int id4 = R.id.MnuOpc4;
    private final int id5 = R.id.MnuOpc5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/


        lista = findViewById(R.id.spinner);
        tv = findViewById(R.id.textView);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(
                this, R.array.datos_lista, android.R.layout.simple_list_item_1);
        bt = findViewById(R.id.button);
        et1 = findViewById(R.id.editTextNumberDecimal3);
        et2 = findViewById(R.id.editTextNumberDecimal2);
        lista.setAdapter(adaptador);


        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv.setText(lista.getSelectedItem().toString());
                Toast.makeText(getApplicationContext(),
                        lista.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                switch (lista.getSelectedItemPosition()){
                    case 0: opcion = operaciones[0]; break;
                    case 1: opcion = operaciones[1]; break;
                    case 2: opcion = operaciones[2]; break;
                    case 3: opcion = operaciones[3]; break;
                    case 4: opcion = operaciones[4]; break;
                }
                bt.setText(opcion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tv.setText("");
            }
        });



    }

    @SuppressLint("DefaultLocale")
    public void click(View v){
        String res = calcular();
        tv.setText(res);
    }

    private String calcular() {
        String t1 = String.valueOf(et1.getText());
        String t2 = String.valueOf(et2.getText());
        double n1=0, n2=0;
        String res="";
        try{
            n1 = Double.parseDouble(t1);
        }catch(NumberFormatException ex){
            makeToast(t1+" no es un número. ");
            n1=0;
        }
        try{
            n2 = Double.parseDouble(t2);
        }catch(NumberFormatException ex){
            makeToast(t2+" no es un número. ");
            n2=0;
        }
        switch (opcion){
            case "+": res = String.format("%.2f",(n1 + n2)); break;
            case "-": res = String.format("%.2f",(n1 - n2)); break;
            case "*": res = String.format("%.2f",(n1 * n2)); break;
            case "/":
                try{
                    res = String.format("%.2f",(n1 / n2));
                } catch (ArithmeticException ex){
                    makeToast(ex.getMessage()+"Error...");
                    res = "?";
                }
                break;
            case "^": res = String.format("%.2f",(Math.pow(n1, n2))); break;
        }
        return res;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Alternativa 1
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == id1) {
            bt.setText(operaciones[0]);
            opcion = operaciones[0];
            lista.setSelection(0);
            return true;
        } else if (id == id2){
            bt.setText(operaciones[1]);
            opcion = operaciones[1];
            lista.setSelection(1);
            tv.setText(opcion);
            return true;
        } else if (id==id3) {

            bt.setText(operaciones[2]);
            opcion = operaciones[2];
            lista.setSelection(2);
            tv.setText(opcion);
            return true;
        } else if (id==id4) {
            bt.setText(operaciones[3]);
            opcion = operaciones[3];
            lista.setSelection(3);
            tv.setText(opcion);
            return true;
        } else {
            bt.setText(operaciones[4]);
            opcion = operaciones[4];
            lista.setSelection(4);
            tv.setText(opcion);
            return true;}
    }
    public void makeToast(String text){
        Toast.makeText(getApplicationContext(),
                lista.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }
}