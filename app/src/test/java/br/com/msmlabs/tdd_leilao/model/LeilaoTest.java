package br.com.msmlabs.tdd_leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Michael"), 200.00));

        double menorLanceDevolvido = console.getMenorLance();

        assertEquals(200.0, menorLanceDevolvido, 0.001);
    }
    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Carlos"), 200.0));
        computador.propoe(new Lance(new Usuario("Marcos"), 500.0));

        double menorLanceDevolvido = computador.getMenorLance();

        assertEquals(200.0,menorLanceDevolvido,0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecescente() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Marcelo"), 5000.0));
        carro.propoe(new Lance(new Usuario("Rangel"), 3000.0));

        double menorLanceDevolvido = carro.getMenorLance();

        assertEquals(3000.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Michael"), 200.00));

        double maiorLanceDevolvido = console.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, 0.001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Carlos"), 200.0));
        computador.propoe(new Lance(new Usuario("Marcos"), 500.0));

        double maiorLanceDevolvido = computador.getMaiorLance();

        assertEquals(500.0,maiorLanceDevolvido,0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecescente() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Marcelo"), 5000.0));
        carro.propoe(new Lance(new Usuario("Rangel"), 3000.0));

        double maiorLanceDevolvido = carro.getMaiorLance();

        assertEquals(5000.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        Leilao console = new Leilao("Console");

        String descricaoDevolvida = console.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }
}