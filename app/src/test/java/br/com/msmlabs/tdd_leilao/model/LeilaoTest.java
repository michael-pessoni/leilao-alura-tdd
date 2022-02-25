package br.com.msmlabs.tdd_leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {

    @Test
    public void getMenorLance() {
    }

    @Test
    public void getMaiorLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Michael"), 200.00));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, 0.001);
    }

    @Test
    public void getDescricao() {
        Leilao console = new Leilao("Console");

        String descricaoDevolvida = console.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }
}