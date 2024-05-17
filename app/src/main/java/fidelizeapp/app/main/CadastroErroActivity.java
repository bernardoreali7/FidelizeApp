package fidelizeapp.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Set;

public class CadastroErroActivity extends AppCompatActivity {
    TextView textViewMsg;
    Button btn_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cadastro_erro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referenciando os elementos da interface

        Bundle extras = getIntent().getExtras();
        StringBuilder interpolada = getStringBuilder(extras);

        textViewMsg = findViewById(R.id.textViewMsg);
        textViewMsg.setText(interpolada);

        btn_voltar = findViewById(R.id.btn_voltar);
        btn_voltar.setOnClickListener((v) -> startActivity(new Intent(CadastroErroActivity.this, CadastroActivity.class)));
    }

    @NonNull
    private StringBuilder getStringBuilder(Bundle extras) {
        ArrayList<String> campos = new ArrayList<String>();
        if (extras != null) {
            if (!extras.isEmpty()) {
                // Se o Bundle não estiver vazio, você pode verificar as chaves adicionadas
                Set<String> keys = extras.keySet();
                for (String key : keys) {
                    Object value = extras.get(key);
                    campos.add(value.toString());
                }
            }
        }

        StringBuilder interpolada = new StringBuilder("Erro no Cadastro. Os campos ");
        // Adicione cada elemento à string interpolada, separando por vírgulas
        for (int i = 0; i < campos.size(); i++) {
            interpolada.append(campos.get(i));
            // Adicione uma vírgula se não for o último elemento
            if (i < campos.size() - 1) {
                interpolada.append(", ");
            }
        }
        interpolada.append(" devem ser preenchidos.");
        return interpolada;
    }
}