package org.techdive.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CursoDTO implements Serializable {

    @NotEmpty(message = "Campo obrigatório: codigo")
    private String codigo;

    @NotEmpty(message = "Campo obrigatório: assunto")
    private String assunto;

    private Integer duracaoEmDias;


    public CursoDTO() { }

    public CursoDTO(String codigo, String assunto, Integer duracaoEmDias) {
        this.codigo = codigo;
        this.assunto = assunto;
        this.duracaoEmDias = duracaoEmDias;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Integer getDuracaoEmDias() {
        return duracaoEmDias;
    }

    public void setDuracaoEmDias(Integer duracaoEmDias) {
        this.duracaoEmDias = duracaoEmDias;
    }

}
