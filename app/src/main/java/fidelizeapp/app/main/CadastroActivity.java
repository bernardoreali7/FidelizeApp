package fidelizeapp.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.AlertDialog;
import android.content.Context;

public class CadastroActivity extends AppCompatActivity {
    EditText input_nome;
    EditText input_telefone;
    EditText input_email;
    EditText input_senha;
    EditText input_senha_confirmacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        input_nome = findViewById(R.id.input_nome);
        input_telefone = findViewById(R.id.input_telefone);
        input_email = findViewById(R.id.input_email);
        input_senha = findViewById(R.id.input_senha);
        input_senha_confirmacao = findViewById(R.id.input_senha_confirmacao);

        findViewById(R.id.btn_logo).setOnClickListener((v) -> {
            showLogoDialog(this);
        });

        findViewById(R.id.btn_voltar).setOnClickListener((v) -> {
            startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
        });

        findViewById(R.id.btn_salvar).setOnClickListener((v) -> {
            String nome = input_nome.getText().toString();
            String telefone = input_telefone.getText().toString();
            String email = input_email.getText().toString();
            String senha = input_senha.getText().toString();
            String senha_confirmacao = input_senha_confirmacao.getText().toString();

            Intent intentErro = new Intent(CadastroActivity.this, CadastroErroActivity.class);

            if (nome.isEmpty()) intentErro.putExtra("STRING", "Nome");
            if (telefone.isEmpty()) intentErro.putExtra("STRING", "Telefone");
            if (email.isEmpty()) intentErro.putExtra("STRING", "E-mail");
            if (senha.isEmpty()) intentErro.putExtra("STRING", "Senha");
            if (senha_confirmacao.isEmpty()) intentErro.putExtra("STRING", "Confirmação de senha");

            if (intentErro.getExtras() != null && !intentErro.getExtras().isEmpty()){
                startActivity(intentErro);
                return;
            }

            Intent intentSucesso = new Intent(CadastroActivity.this, LoginActivity.class);
            showSuccessDialog(this, intentSucesso);
        });
    }

    public void showSuccessDialog(Context context, Intent intennt) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cadastro")
                .setMessage("Cadastro efetuado com sucesso!    Realize login para continuar.")
                .setPositiveButton("OK", (dialog, which) -> { dialog.dismiss(); startActivity(intennt); })
                .create()
                .show();
    }

    public void showLogoDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alerta")
                .setMessage("Abrir album de fotos do celular")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
}