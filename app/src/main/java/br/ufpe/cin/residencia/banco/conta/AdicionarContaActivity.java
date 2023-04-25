package br.ufpe.cin.residencia.banco.conta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class AdicionarContaActivity extends AppCompatActivity {

    ContaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_conta);
        viewModel = new ViewModelProvider(this).get(ContaViewModel.class);

        Button btnAtualizar = findViewById(R.id.btnAtualizar);
        Button btnRemover = findViewById(R.id.btnRemover);
        EditText campoNome = findViewById(R.id.nome);
        EditText campoNumero = findViewById(R.id.numero);
        EditText campoCPF = findViewById(R.id.cpf);
        EditText campoSaldo = findViewById(R.id.saldo);

        btnAtualizar.setText("Inserir");
        btnRemover.setVisibility(View.GONE);

        btnAtualizar.setOnClickListener(
                v -> {
                    String nomeCliente = campoNome.getText().toString();
                    String cpfCliente = campoCPF.getText().toString();
                    String numeroConta = campoNumero.getText().toString();
                    String saldoConta = campoSaldo.getText().toString();

                    if (nomeCliente.isEmpty() || cpfCliente.isEmpty() || numeroConta.isEmpty() || saldoConta.isEmpty()) {
                        Toast.makeText(AdicionarContaActivity.this, "Todos os campos são obrigatórios.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Double saldo;
                    try {
                        saldo = Double.parseDouble(saldoConta);
                    } catch (NumberFormatException e) {
                        Toast.makeText(AdicionarContaActivity.this, "O campo saldo deve ser um número.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (nomeCliente.length() < 5) {
                        Toast.makeText(AdicionarContaActivity.this, "O nome deve ter pelo menos 5 caracteres.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Conta c = new Conta(numeroConta, Double.valueOf(saldoConta), nomeCliente, cpfCliente);
                    viewModel.inserir(c);
                    finish();
                }
        );

    }
}