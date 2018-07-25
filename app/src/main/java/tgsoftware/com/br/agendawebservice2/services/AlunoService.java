package tgsoftware.com.br.agendawebservice2.services;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tgsoftware.com.br.agendawebservice2.dto.AlunoSync;
import tgsoftware.com.br.agendawebservice2.modelo.Aluno;

/**
 * Created by alura on 11/29/16.
 */

public interface AlunoService {

    @POST("aluno")
    Call<Void> insere(@Body Aluno aluno);

    @GET("aluno")
    Call<AlunoSync> lista();

    @DELETE("aluno/{id}")
    Call<Void> deleta(@Path("id") String id);
}
