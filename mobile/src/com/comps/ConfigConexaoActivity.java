package com.comps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.comps.web.UrlUtil;

public class ConfigConexaoActivity extends AbstractValidacaoActivity {
	
	EditText etUrl;
    EditText etBanco;
	Button btSalvar;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_conexao);

        etUrl = (EditText) findViewById(R.id.etUrl);
        etBanco = (EditText) findViewById(R.id.etBanco);


        btSalvar = (Button) findViewById(R.id.btSalvarUrl);
        btSalvar.setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			case R.id.btSalvarUrl:
				String url = "http://" + etUrl.getText().toString();
                String banco = etBanco.getText().toString();
				UrlUtil.server = url;
                UrlUtil.db = banco;
                UrlUtil.geraUrls();
				mensagemSalvarSucesso();
				break;
	
			default:
				break;
		}
	}

}
