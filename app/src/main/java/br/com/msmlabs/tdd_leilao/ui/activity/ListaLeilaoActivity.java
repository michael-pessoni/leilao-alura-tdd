package br.com.msmlabs.tdd_leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.msmlabs.tdd_leilao.R;
import br.com.msmlabs.tdd_leilao.model.Lance;
import br.com.msmlabs.tdd_leilao.model.Leilao;
import br.com.msmlabs.tdd_leilao.model.Usuario;
import br.com.msmlabs.tdd_leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class ListaLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);

        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(this, leiloesDeExemplo());
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(leilao -> {
            Intent vaiParaLancesLeilao = new Intent(ListaLeilaoActivity.this, LancesLeilaoActivity.class);
            vaiParaLancesLeilao.putExtra("leilao", leilao);
            startActivity(vaiParaLancesLeilao);
        });
    }

    private List<Leilao> leiloesDeExemplo() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Luiz"), 200.00));
        console.propoe(new Lance(new Usuario("Mario"), 500.00));

        Leilao computador = new Leilao("Console");
        computador.propoe(new Lance(new Usuario("Mario"), 800.00));

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Luiz"), 2000.00));
        carro.propoe(new Lance(new Usuario("Mario"), 5000.00));
        carro.propoe(new Lance(new Usuario("Manuela"), 8000.00));

        return new ArrayList<>(Arrays.asList(
                console,
                carro,
                computador
        ));


    }

}
