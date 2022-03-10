package br.com.msmlabs.tdd_leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario MICHAEL = new Usuario("Michael");

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(MICHAEL, 200.00));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, 0.001);
    }
    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(MICHAEL, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Marcos"), 500.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(200.0,menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecescente() {
        CONSOLE.propoe(new Lance(MICHAEL, 5000.0));
        CONSOLE.propoe(new Lance(new Usuario("Marcos"), 3000.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(3000.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(MICHAEL, 200.00));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, 0.001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(MICHAEL, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Marcos"), 500.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(500.0,maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecescente() {
        CONSOLE.propoe(new Lance(MICHAEL, 5000.0));
        CONSOLE.propoe(new Lance(new Usuario("Marcos"), 3000.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(5000.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = CONSOLE.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        CONSOLE.propoe(new Lance(MICHAEL, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Marcos"), 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Rangel"), 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }
}