/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sabrina Winckler
 */
//Classe que faz a conexão com o servidor e o GET nos dados
public class ClienteHttp {
    HttpURLConnection conexao;

    public void criarConexao() throws IOException {

        //URL do site que será consultado
        URL url = new URL("http://example.com");

        //O HttpUrlConnection class é usado para todos os tipos de requests: GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE.
        this.conexao = (HttpURLConnection) url.openConnection();

        //Define o método da conexão como GET
        conexao.setRequestMethod("GET");
    }

    public void definirParametro(String parametro) throws IOException {
        //Para adicionar parametros ao request, setar true na propriedade doOutput
        //Escrever a String param1=value&param2=value no OutputStream da instancia de HttpUrlConnection
        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");

        conexao.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(conexao.getOutputStream());
        out.writeBytes(ParametroBuilder.getParamsString(parameters));
        out.flush();
        out.close();

    }

}
