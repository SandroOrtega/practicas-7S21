package mx.edu.tesoem.isc.t2p17s21sog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SegundoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
    }

    public void llamar (View v){
        Intent intent = new Intent(this, PrincipalActivity.class);
        intent.putExtra("cadena", "Segunda");
        startActivity(intent);
        finish();
    }
}