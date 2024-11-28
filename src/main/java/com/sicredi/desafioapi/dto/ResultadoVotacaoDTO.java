package com.sicredi.desafioapi.dto;

public class ResultadoVotacaoDTO {

    private long simCount;
    private long naoCount;

    public ResultadoVotacaoDTO(long simCount, long naoCount) {
        this.simCount = simCount;
        this.naoCount = naoCount;
    }

    // Getters and Setters
    public long getSimCount() {
        return simCount;
    }

    public void setSimCount(long simCount) {
        this.simCount = simCount;
    }

    public long getNaoCount() {
        return naoCount;
    }

    public void setNaoCount(long naoCount) {
        this.naoCount = naoCount;
    }

    @Override
    public String toString() {
        return "ResultadoVotacao{" +
                "simCount=" + simCount +
                ", naoCount=" + naoCount +
                '}';
    }
}
