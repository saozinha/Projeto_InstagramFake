package task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import fw.Util;


/**
 * Created by welper on 13/06/2015.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{


    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap b = obterImagem(params[0]);

        if( b == null ){
            try{
                InputStream in = new java.net.URL(params[0]).openStream();
                b = BitmapFactory.decodeStream(in);
            }catch (Exception e){
                b = null;
            }
        }

        return b;
    }

    private void salvarBitMap(Bitmap b, String url) throws Exception{
        String id = Util.md5(url);
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/images";
        File dir = new File(filePath);
        dir.mkdir();

        Log.i("Imagem", "Salvando imagem: " + filePath + id + ".png");
        FileOutputStream out = new FileOutputStream(filePath + id + ".png");
        b.compress(Bitmap.CompressFormat.PNG,100,out);
        out.flush();
        out.close();
    }

    private Bitmap obterImagem(String url){
        String id = Util.md5(url);
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/images";
        File dir = new File(filePath);
        if( dir.exists()){
            Log.i("Imagem", "Obtendo imagem: " + filePath + id + ".png");
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            return bitmap;
        } else {
            return null;
        }
    }
}
