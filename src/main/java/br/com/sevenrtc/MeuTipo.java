package br.com.sevenrtc;

import java.io.Serializable;

/**
 * Created by a.accioly on 12/23/14.
 */
public class MeuTipo implements Serializable {

    private final Integer meuId;
    private final String meuNome;

    public MeuTipo(Integer meuId, String meuNome) {
        this.meuId = meuId;
        this.meuNome = meuNome;
    }

    public Integer getMeuId() {
        return meuId;
    }

    public String getMeuNome() {
        return meuNome;
    }

    @Override
    public String toString() {
        return "MeuTipo{" +
                "meuId=" + meuId +
                ", meuNome='" + meuNome + '\'' +
                '}';
    }
}
