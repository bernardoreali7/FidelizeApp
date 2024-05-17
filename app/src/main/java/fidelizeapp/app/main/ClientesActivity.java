package fidelizeapp.app.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;
import java.util.List;

public class ClientesActivity extends AppCompatActivity {
    ImageButton btn_sair;
    ImageButton btn_addCliente;

    private List<Cliente> clientes;
    private ClienteAdapter adapter;

    private List<Cliente> dummyData() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "John Doe", "123-456-7890"));
        clientes.add(new Cliente(2, "Jane Smith", "098-765-4321"));
        clientes.add(new Cliente(3, "Mike Johnson", "555-123-4567"));
        return clientes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.clientes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listViewClientes = findViewById(R.id.listViewClientes);
        clientes = dummyData();
        adapter = new ClienteAdapter(this, clientes);
        listViewClientes.setAdapter(adapter);

        listViewClientes.setOnItemClickListener((parent, view, position, id) -> {
            // Get the selected cliente
            Cliente selectedCliente = (Cliente) parent.getItemAtPosition(position);

            // Create an Intent to start CartaoActivity
            Intent intent = new Intent(ClientesActivity.this, CartaoActivity.class);
            intent.putExtra("cliente", selectedCliente);

            // Start the new activity
            startActivity(intent);
        });

        btn_sair = findViewById(R.id.btn_sair);
        btn_addCliente = findViewById(R.id.btn_addCliente);

        btn_sair.setOnClickListener((v) -> {
            startActivity(new Intent(ClientesActivity.this, LoginActivity.class));
        });

        btn_addCliente.setOnClickListener((v) -> {
            showFormDialog();
        });
    }


    private void showFormDialog() {
        // Inflate the custom layout/view
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.clientes_cadastro, null);

        // Initialize the EditTexts
        EditText editTextName = dialogView.findViewById(R.id.editTextName);
        EditText editTextPhone = dialogView.findViewById(R.id.editTextPhone);

        // Create the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setTitle("Cadastrar cliente")
                .setPositiveButton("Salvar", (dialog, which) -> {
                    String name = editTextName.getText().toString();
                    String phone = editTextPhone.getText().toString();

                    Cliente newCliente = new Cliente(clientes.size() + 1, name, phone);
                    clientes.add(newCliente);
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
}
