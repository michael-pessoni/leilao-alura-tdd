package br.com.msmlabs.tdd_leilao.model;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.msmlabs.tdd_leilao.exceptions.LanceMenorQueUltimoLanceException;
import br.com.msmlabs.tdd_leilao.exceptions.LanceSeguidoDoMesmoUsuarioException;
import br.com.msmlabs.tdd_leilao.exceptions.UsuarioDeuCincoLancesException;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario MICHAEL = new Usuario("Michael");

    @Rule
    public ExpectedException exception = ExpectedException.none();

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

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeUmLance(){
        CONSOLE.propoe(new Lance(MICHAEL, 200));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeDoisLances(){
        CONSOLE.propoe(new Lance(MICHAEL, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Marcos"), 300.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances(){
        CONSOLE.propoe(new Lance(MICHAEL, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Marcos"), 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Rangel"), 400.0));
        CONSOLE.propoe(new Lance(new Usuario("Raquel"), 500.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(500.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance(new Usuario("Marcelo"), 600.00));

        List<Lance> tresMaioresLancesDevolvidosParaCincoLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(600.0, tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);

    }

    @Test
    public void deve_DevolverZeroParaMaiorLance_QuandoNaoRecebeNenhumLance(){
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);

    }
    @Test
    public void deve_DevolverZeroParaMenorLance_QuandoNaoRecebeNenhumLance(){
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_LancarException_QuandoForMenorQueOMaiorLance(){
        exception.expect(LanceMenorQueUltimoLanceException.class);

        CONSOLE.propoe(new Lance(MICHAEL,500.00));
        CONSOLE.propoe(new Lance(new Usuario("Carol"), 400.00));

    }

    @Test
    public void deve_LancarException_QuandoUsuárioForOMesmoDoUltimoLance(){
        exception.expect(LanceSeguidoDoMesmoUsuarioException.class);

        CONSOLE.propoe(new Lance(MICHAEL, 400.0));
        CONSOLE.propoe(new Lance(new Usuario("Michael"), 500.0));
    }

    @Test(expected = UsuarioDeuCincoLancesException.class)
    public void deve_LancarException_QuandoDerCincoLances(){
        CONSOLE.propoe(new Lance(MICHAEL, 100));
        final Usuario CAROL = new Usuario("Carol");
        CONSOLE.propoe(new Lance(CAROL, 200));
        CONSOLE.propoe(new Lance(MICHAEL, 300));
        CONSOLE.propoe(new Lance(CAROL, 400));
        CONSOLE.propoe(new Lance(MICHAEL, 500));
        CONSOLE.propoe(new Lance(CAROL, 600));
        CONSOLE.propoe(new Lance(MICHAEL, 700));
        CONSOLE.propoe(new Lance(CAROL, 800));
        CONSOLE.propoe(new Lance(MICHAEL, 900));
        CONSOLE.propoe(new Lance(CAROL, 1000));
        CONSOLE.propoe(new Lance(MICHAEL, 1100));

    }

}