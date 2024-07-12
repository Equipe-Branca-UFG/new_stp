package br.ufg.inf.backend.stp.infra;

import br.ufg.inf.backend.stp.infra.enumeracao.TipoMensagem;

public class MensagemValidacao {

    private boolean valido;
    private TipoMensagem tipoMensagem;
    private String mensagem;

    public MensagemValidacao(String mensagem, TipoMensagem tipo) {
        valido = false;
        this.mensagem = mensagem;
        this.tipoMensagem = tipo;
    }

    public MensagemValidacao() {
        valido = true;
    }

    public MensagemValidacao(String mensagemSucesso) {
        valido = true;
        this.mensagem = mensagemSucesso;
    }

    public boolean isValido() {
        return valido;
    }
    public void setValido(boolean valido) {
        this.valido = valido;
    }
    public TipoMensagem getTipoMensagem() {
        return tipoMensagem;
    }
    public void setTipoMensagem(TipoMensagem tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


}
