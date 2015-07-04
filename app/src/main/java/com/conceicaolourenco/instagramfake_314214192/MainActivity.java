package com.conceicaolourenco.instagramfake_314214192;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import adapter.AmigoAdapter;
import task.RedeTask;
import to.TORede;


public class MainActivity extends ActionBarActivity {


    private ProgressDialog pd;
    private ListView listAmigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // remover o titulo - bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // fim
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAmigos = (ListView) findViewById(R.id.listAmigos);

        obterRede();
    }

    public void obterRede() {

        pd = new ProgressDialog(this);
        pd.setMessage("Carregando...");
        pd.show();

        RedeTask r = new RedeTask() {
            @Override
            protected void onPostExecute(TORede toRede) {

                pd.hide();

                if (toRede != null) {

                    AmigoAdapter adapter = new AmigoAdapter(toRede, MainActivity.this);
                    listAmigos.setAdapter(adapter);

                }

            }
        };

        r.execute("");

    }
}
