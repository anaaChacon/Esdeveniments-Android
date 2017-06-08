package com.example.anabel.esdevenimentsvalencia.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anabel.esdevenimentsvalencia.R;

import es.dmoral.toasty.Toasty;

/**
 * Created by Anabel on 24/05/2017.
 */

public class DialogFragmentConnection extends DialogFragment implements View.OnClickListener {

    private EditText editIP, editPuerto;
    private Button conectar;

    public static String dir;

    public static DialogFragmentConnection newInstance() {

        Bundle args = new Bundle();

        DialogFragmentConnection fragment = new DialogFragmentConnection();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Construyo y muestro el diálogo
        //Primero genero un constructor de diálogos de alerta
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Inflo el layout del dialogo
        View layoutDialogo1;
        layoutDialogo1 = getActivity().getLayoutInflater().inflate(R.layout.dialog_conection, null);

        editIP = (EditText)layoutDialogo1.findViewById(R.id.numIp);
        editPuerto = (EditText)layoutDialogo1.findViewById(R.id.numPuerto);
        conectar = (Button)layoutDialogo1.findViewById(R.id.connect);

        conectar.setOnClickListener(this);

        //Seteo el layout en el diálogo
        builder.setView(layoutDialogo1);

        //No permito huir del login presionando fuera de su area
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.connect){
            if(editIP.getText().toString().isEmpty() || editPuerto.getText().toString().isEmpty()){
                Toasty.info(getActivity(), getString(R.string.emptyFileds), Toast.LENGTH_LONG, true).show();
            }else {
                dir = editIP.getText().toString() + ":" + editPuerto.getText().toString();
                Toasty.info(getActivity(), "Conexión realizada", Toast.LENGTH_LONG, true).show();
                dismiss();
            }
        }
    }

}
