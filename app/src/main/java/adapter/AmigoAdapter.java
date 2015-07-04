package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.conceicaolourenco.instagramfake_314214192.R;

import task.DownloadImageTask;
import to.TOAmigo;
import to.TOPublicacao;
import to.TORede;

/**
 * Created by ConceicaoLourenco on 26/06/2015.
 */
public class AmigoAdapter extends BaseAdapter {

    private TORede rede;
    private Context context;

    public AmigoAdapter(TORede rede, Context context) {
        this.rede = rede;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rede.getLista().size();
    }

    @Override
    public Object getItem(int position) {
        return rede.getLista().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TOPublicacao tp = rede.getLista().get(position);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.item_list, null);

        int qdt = 0;

            final ImageView imagem = (ImageView) v.findViewById(R.id.imagem);
            TextView txtDescricao = (TextView) v.findViewById(R.id.descricao);

            TextView txtData = (TextView) v.findViewById(R.id.data);


            DownloadImageTask d = new DownloadImageTask(){
                @Override
                protected void onPostExecute(Bitmap bitMap){
                    imagem.setImageBitmap(bitMap);
                }
            };

            d.execute(tp.getImagem());

        if (tp.getLikes().size() > 0) {

            txtData.setText("Data : "+tp.getData());
            txtDescricao.setText(tp.getDescricao());

            //USUARIOS QUE CURTIRAM
            TextView txtUsuario1 = (TextView) v.findViewById(R.id.usuario);

            String nome = "";
            for (TOAmigo amigo : tp.getLikes()) {
                qdt ++;
                if (amigo.getUsuario()!= null) {
                    nome += "  |  " + amigo.getUsuario() ;
                    txtUsuario1.setText(nome);
                }
            }
        }

        return v;
       /*
        TOAmigo t = rede.getLista().get(position);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.item_list, null);

        TextView txtNome = (TextView) v.findViewById(R.id.nome);
        TextView txtData = (TextView) v.findViewById(R.id.data);
        TextView txtLike = (TextView) v.findViewById(R.id.quantidade);
        TextView txtAmigos = (TextView) v.findViewById(R.id.amigos);

        txtNome.setText(t.getUsuario());


        final ImageView i = (ImageView) v.findViewById(R.id.avatar);

        DownloadImageTask d = new DownloadImageTask(){
            @Override
            protected void onPostExecute(Bitmap bitMap){
                i.setImageBitmap(bitMap);
            }
        };

        d.execute(t.getAvatar());

        if (t.getData()!=null) {
            String data = t.getData().toString();
            txtData.setText(data);
        }

        if (t.getAmigos().size() > 0) {

            txtLike.setText((int) t.getAmigos().size() );

            //Toast.makeText(this, "Amigo qtd > " + t.getAmigos().size() , Toast.LENGTH_SHORT).show();

            for( String amigo : t.getAmigos()) {
                System.out.println(amigo);
                txtAmigos.setText(amigo);
            }
        } else {
            txtLike.setVisibility(View.INVISIBLE);
        }


        //txtAmigos.setText(t.getAmigos()); //----------------

        return v; */

    }
}
