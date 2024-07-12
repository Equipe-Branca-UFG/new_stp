package br.ufg.inf.backend.stp.domain.exception;

public class NegocioError {
    private final String codeMessage;
    private final String developerMessage;
    private String[] messageParams;

    public NegocioError(String codeMessage, String developerMessage, String... messageParams) {
        this.codeMessage = codeMessage;
        this.developerMessage = developerMessage;
        this.messageParams = messageParams;
    }

    public String getCodeMessage() {
        return this.codeMessage;
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public String[] getMessageParams() {
        return this.messageParams;
    }
}
