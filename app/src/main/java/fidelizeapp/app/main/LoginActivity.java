package fidelizeapp.app.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText input_email;
    EditText input_senha;
    RadioButton rb_manterLogin;
    Button btn_login;
    Button btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        input_email = findViewById(R.id.input_email);
        input_senha = findViewById(R.id.input_senha);
        rb_manterLogin = findViewById(R.id.rb_manterLogin);
        btn_login = findViewById(R.id.btn_login);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);

        rb_manterLogin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.d("RADIO", "Manter login: $isChecked");
            //salvar valor ao banco e validar ao abrir tela para passar diretamente para ClientesActivity
        });

        btn_login.setOnClickListener((v) -> {
            String email = input_email.getText().toString();
            String senha = input_senha.getText().toString();

            // Verificando se o campo não está vazio
            if (!email.isEmpty() && !senha.isEmpty()) {
                if (!(email.equals("teste@teste.com") && senha.equals("123"))) {
                    showErrorDialog(this);
                    return;
                }

                startActivity(new Intent(LoginActivity.this, ClientesActivity.class));
                return;
            }

            showErrorDialog(this);
            return;
        });

        btn_cadastrar.setOnClickListener((v) -> {
            startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
        });

    }


    public void showErrorDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Erro")
                .setMessage("Login inválido! Verifique os campos preenchidos.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

}