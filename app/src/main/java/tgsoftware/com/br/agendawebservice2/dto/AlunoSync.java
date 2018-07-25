package tgsoftware.com.br.agendawebservice2.dto;

import java.util.List;

import tgsoftware.com.br.agendawebservice2.modelo.Aluno;

/**
 * Created by alura on 12/2/16.
 */

public class AlunoSync {

    private List<Aluno> alunos;
    private String momentoDaUltimaModificacao;

    public String getMomentoDaUltimaModificacao() {
        return momentoDaUltimaModificacao;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}
