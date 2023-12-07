package MassarraAmara.mycallerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyContactRecyclerAdapter extends RecyclerView.Adapter<MyContactRecyclerAdapter.MyViewHolder>{
    Context con;
    ArrayList <Contact> data;

    public MyContactRecyclerAdapter(Context con, ArrayList<Contact> data) {
        this.con = con;
        this.data = data;
    }

    @NonNull
    @Override
    public MyContactRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //renvoie un vue a creer
        //creation d'un view
        LayoutInflater inf =LayoutInflater.from(con);
        //parsing code xml

        View v = inf.inflate(R.layout.view_contact,null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyContactRecyclerAdapter.MyViewHolder holder, int position) {
        Log.i("","onBindViewHolder");
        Contact c = data.get(position);
        holder.tvnom_con.setText(c.nom);
        holder.tvprenom_con.setText(c.prenom);
        holder.tvnum_con.setText(c.numero);

    }



    @Override
    public int getItemCount() {
        return Acceuil.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  tvnom_con,tvprenom_con,tvnum_con;
        ImageView call, edit,del;
        AlertDialog dialog;
        public MyViewHolder(@NonNull View v) {
            super(v);
             tvnom_con=v.findViewById(R.id.tv_nom_contact);
             tvprenom_con=v.findViewById(R.id.tv_prenom_contact);
             tvnum_con=v.findViewById(R.id.tv_num_contact);
             call= v.findViewById(R.id.IVtel_contact);
             edit= v.findViewById(R.id.IVedit_contact);
             del= v.findViewById(R.id.IVcancel_contact);

            // evenements
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ind = getAdapterPosition();//retourne l'indice de selecyion
                    //data.remove(ind);
                    if (ind != RecyclerView.NO_POSITION) {
                        Log.i("ss","gabl");
                        MainActivity.dm.supprimer(tvnum_con.getText().toString());
                        Log.i("sa","ba3d");
                        //data.remove(p); // Remove the item from the data list.
                        Acceuil.data.remove(ind);
                        notifyDataSetChanged();
                    }
                }
            });
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int indice=getAdapterPosition();
                    Intent i =new Intent();
                    i.setAction(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:"+data.get(indice).numero));
                    con.startActivity(i);
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int indice=getAdapterPosition();
                    AlertDialog.Builder alert=new AlertDialog.Builder(con);
                    alert.setTitle("Modification");
                    alert.setMessage("Modifier vos informations");
                    LayoutInflater inf =LayoutInflater.from(con);
                    //parsing code xml

                    View vd = inf.inflate(R.layout.view_dialog,null);
                    EditText nom_dialog=vd.findViewById(R.id.ed_nom_dialog);
                    EditText prenom_dialog=vd.findViewById(R.id.ed_prenom_dialog);
                    EditText num_dialog=vd.findViewById(R.id.ed_num_dialog);
                    Button valider_dialog=vd.findViewById(R.id.btnval_dialog);
                    Button quitt_dialog=vd.findViewById(R.id.btnquit_dialog);
                    nom_dialog.setText(data.get(getAdapterPosition()).nom);
                    prenom_dialog.setText(data.get(getAdapterPosition()).prenom);
                    num_dialog.setText(data.get(getAdapterPosition()).numero);


                    valider_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Contact c= new Contact(nom_dialog.getText().toString(),prenom_dialog.getText().toString(),num_dialog.getText().toString());
                            MainActivity.dm.modifier(c);
                            data.set(indice,c);
                            notifyDataSetChanged();
                        }
                    });
                    quitt_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    alert.setView(vd);
                    dialog=alert.create();
                    alert.show();

                }
            });

        }
    }
}
