package to;

import java.util.List;

/**
 * Created by ConceicaoLourenco on 13/06/2015.
 */
public class TOPublicacao extends TOBase{

    private List<TOAmigo> likes;
    private String imagem;
    private String data;
    private String descricao;

    public List<TOAmigo> getLikes() {
        return likes;
    }

    public void setLikes(List<TOAmigo> likes) {
        this.likes = likes;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
