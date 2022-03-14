package br.com.msmlabs.tdd_leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.msmlabs.tdd_leilao.exceptions.LanceMenorQueUltimoLanceException;
import br.com.msmlabs.tdd_leilao.exceptions.LanceSeguidoDoMesmoUsuarioException;
import br.com.msmlabs.tdd_leilao.exceptions.UsuarioDeuCincoLancesException;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance){
        if (lanceNaoValido(lance)) return;
        lances.add(lance);

        double valorLance = lance.getValor();
        if (defineMaiorEMenorLancesNoPrimeiroLance(valorLance)) return;

        Collections.sort(lances);
        calculaMaiorLance(valorLance);
        calculaMenorLance(valorLance);
    }

    private boolean defineMaiorEMenorLancesNoPrimeiroLance(double valorLance) {
        if(lances.size() == 1){
            maiorLance = valorLance;
            menorLance = valorLance;
            return true;
        }
        return false;
    }

    private boolean lanceNaoValido(Lance lance) {
        double valorLance = lance.getValor();

        if (lanceMenorQueOUltimo(valorLance))
            throw new LanceMenorQueUltimoLanceException();

        if(!lances.isEmpty()){
            Usuario usuarioNovo = lance.getUsuario();

            if (mesmoUsuarioDoUltimoLance(usuarioNovo))
                throw new LanceSeguidoDoMesmoUsuarioException();

            if (usuarioDeuCincoLances(usuarioNovo))
                throw new UsuarioDeuCincoLancesException();

        }
        return false;
    }

    private boolean usuarioDeuCincoLances(Usuario usuarioNovo) {
        int lancesMesmoUsuario = 0;
        for (Lance l:
             lances) {
            Usuario usuarioExistente = l.getUsuario();
            if (usuarioExistente.equals(usuarioNovo)){
                lancesMesmoUsuario++;
                if (lancesMesmoUsuario == 5) return true;
            }
        }
        return false;
    }

    private boolean mesmoUsuarioDoUltimoLance(Usuario usuarioNovo) {
        Usuario ultimoUsuario = lances.get(0).getUsuario();

        if (ultimoUsuario.equals(usuarioNovo)){
            return true;
        }
        return false;
    }

    private boolean lanceMenorQueOUltimo(double valorLance) {
        if (maiorLance > valorLance){
            return true;
        }
        return false;
    }

    private void calculaMenorLance(double valorLance) {
        if(valorLance < menorLance){
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if(valorLance > maiorLance){
            maiorLance = valorLance;
        }
    }

    public double getMenorLance() {
        return menorLance;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> tresMaioresLances() {
        int quantidadeMaximaDeLances = lances.size();
        if(quantidadeMaximaDeLances > 3){
            quantidadeMaximaDeLances = 3;
        }
        return lances.subList(0, quantidadeMaximaDeLances);
    }

    public int quantidadeLances() {
        return lances.size();
    }
}
