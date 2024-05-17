package fidelizeapp.app.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CartaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartao);

        TextView nome_cliente = findViewById(R.id.nome_cliente);

        // Recuperar os dados do Intent
        Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        if (cliente != null) {
            nome_cliente.setText(String.valueOf(cliente.getName()));
        }

        findViewById(R.id.btn_voltar).setOnClickListener((v) -> {
            startActivity(new Intent(CartaoActivity.this, ClientesActivity.class));
        });

        findViewById(R.id.btn_premio).setOnClickListener((v) -> {
            showPremioDialog(this);
        });

        findViewById(R.id.btn_configurar).setOnClickListener((v) -> {
            showConfigDialog(this);
        });
    }

    public void showPremioDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Sucesso")
                .setMessage("Prêmio enviado com sucesso!")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    public void showConfigDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alerta")
                .setMessage("Em desenvolvimento.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
}
