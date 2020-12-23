package filipe.app.appip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

public class Main_add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void carregarLocalizacao(String ip) throws JSONException, IOException {
        Localizacao localizacao = ClienteGeoIP.localizar(ip);
        TextView pais = (TextView)findViewById(R.id.pais);
        pais.setText(localizacao.getCountryName());
        SharedPreferences prefs = getSharedPreferences("preferencias",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("pais", pais.getText().toString());
        ed.apply();
        Toast.makeText(getBaseContext(), "IP encontrado!", Toast.LENGTH_SHORT).show();
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void btnLocalizarOnClick(View v){
        EditText txtIP = (EditText) findViewById(R.id.txtIP);
        String ip = txtIP.getText().toString();
        hideSoftKeyboard();
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            }
            else{
                carregarLocalizacao(ip);
            }
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void btnTrashOnClick(View v) {
        TextView pais = (TextView)findViewById(R.id.pais);
        EditText txtIP = (EditText) findViewById(R.id.txtIP);
        pais.setText("");
        txtIP.setText("");
        txtIP.getText().clear();

        Toast.makeText(getBaseContext(), "Registro Excluído!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    EditText txtIP = (EditText) findViewById(R.id.txtIP);
                    String ip = txtIP.getText().toString();
                    try {
                        carregarLocalizacao(ip);
                    } catch (Exception e) {
                        Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}