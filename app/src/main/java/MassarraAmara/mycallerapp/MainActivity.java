package MassarraAmara.mycallerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //declaration des composants
    Button btnquitter,btnvalider;
    EditText edemail,edmdp;
    public static DbManager dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mettre un xml sur l'ecran
        setContentView(R.layout.activity_main);

        btnquitter = findViewById(R.id.btnQuit_Auth);
        btnvalider = findViewById(R.id.btnvalider_Auth);
        edemail = findViewById(R.id.edEmail_Auth);
        edmdp = findViewById(R.id.edPassw_auth);

        //evenements
        btnquitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnvalider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = edemail.getText().toString();
                String pwd = edmdp.getText().toString();
                if (mail.equalsIgnoreCase("massarra")&&pwd.equals("1234")){
                    //passage entre activité
                    /**
                     * pour  lancer une activitée il faut:
                     * declarer l'intention intent
                     * lancer votre intention
                     */
                  /*  Intent i= new Intent();
                    i.setAction(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:54708240"));
                    startActivity(i);*/
                    dm= new DbManager(MainActivity.this);
                    dm.ouvrir();


                    Intent i = new Intent(MainActivity.this, Acceuil.class);

                    startActivity(i);

                } else {
                    //message d'erreur
                    Toast.makeText(MainActivity.this, "erreur de saisie", Toast.LENGTH_SHORT).show();

                }
            }
            });

    }
}