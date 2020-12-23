package filipe.app.appip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main_home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);


        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.action_perfil:
                        Toast.makeText(Main_home.this, "Botão Perfil Clicado", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_add:
                        startActivity(new Intent(Main_home.this, Main_add.class));
                        break;

                    case R.id.action_DB:
                        Toast.makeText(Main_home.this, "Botão Dados Clicado", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
    });
}}