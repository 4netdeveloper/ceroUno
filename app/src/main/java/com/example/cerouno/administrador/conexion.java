package com.example.cerouno.administrador;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.Log;


import com.example.cerouno.manejadores.usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

public class conexion {
    private String url;


    private String proto, host, port, dev, acc, val; //protocolo,host,puerto,
    // disopitivo
    // accion
    // valor
    private int status;
    private String has;
    private MyAsyncTask _MAsync;
    private Context Contexto;
    private WifiManager wifi;


    /*objeto especial para procesar respuestas */
    //private resultado rts;

    public conexion(Context Contexto, String usrhas, String url) {
        /*
        * TODO: clase de coneccion se requiere :
            url conformada por <protocolo>://<host>:<port>

        */


        // funcion de prueba de coneccion.
        this.Contexto = Contexto;
        this.has = usrhas;

        this.setUrl(url);
        this._MAsync = new MyAsyncTask(host, port, usrhas);

        //
        // prueba exitosa enciende una luz con el usuario leandro morala.
        //
        // this.test();

    }

    public static String urlDelCorePi() {

        return "http://192.168.155.14:8181";

    }

    public Boolean send(String dev, String acc, String val, Boolean sinRespuesta) {

        send(dev, acc, val);
        return false;

    }
    public conexion()
    {}
    public conexion send(String dev, String acc, String val) {
        // alg oooo par hacer.

        /*_MAsync.setDev(dev).setAcc(acc).setVal(val);
        _MAsync.execute("");*/
        Log.i("CONEXION SEND -------", dev+" --- "+acc+" --- "+val);
        return this;
    }

    private class MyAsyncTask extends AsyncTask<String, Void, String>
    {

        String host,port,headget,response;
        private int contadorSend =0;
        String usrhas, dev,acc,val;
        IOException ioException;
        boolean working=false;

        MyAsyncTask( String host,String port, String usr ) {
            super();
            this.host = host;
            this.port = port;
            this.usrhas=usr;

            this.response = "";
            this.ioException = null;
        }

        /*

        public void execute(String ... params){

            this.onPostExecute(this.doInBackground(params));

        }

        // * */

        @Override
        protected String doInBackground(String... params) {
            // protected String doInBackground(String... params) {
            this.working=true;
            // construyendo el header :
            headget=headGet(host,port,usrhas,dev,acc,val);
            msg.echo("MyAsinc: enviando \r\n"+headget);
            //msg.var_dump(this);
            /*al enviar la ejecuion a segundo plano */
            StringBuilder sb = new StringBuilder();
            try {

                Socket socket = new Socket( host, Integer.parseInt(port));
                OutputStream out = socket.getOutputStream();
                msg.echo("MyAsinc: write-"+contadorSend+"--");
                out.write(headget.getBytes());
                contadorSend++;
                InputStream in = socket.getInputStream();

                /*lectura de respuesta*/
                byte buf[] = new byte[1024];
                int nbytes;
                while ((nbytes = in.read(buf)) != -1) {
                    sb.append(new String(buf, 0, nbytes));
                }

                //msg.echo("MyAsinc: READ \r\n"+sb.toString());
                socket.close();

            } catch(IOException e) {
                this.ioException = e;
                this.working=false;
                msg.echo("MyAsinc: falla" + e.getMessage());
                return "{}";
            }
            this.working=false;
            return headRequest( sb.toString() );
            // sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            /*al terminar la ejcucion recibe la respuesta */
            if (this.ioException != null) {
                this.response="error";
            } else {
                // this.textView.setText(result);

                this.response=result;
            }
            //msg.echo("MyAsinc: request-brute:"+result);
            msg.echo("MyAsinc: responde: --\r\n"+response );
            // this.button.setEnabled(true);
        }

        public String getResponse(){

            return this.response;
        }

        public String getUsrhas() { return usrhas;}
        public void setUsrhas(String usrhas) {this.usrhas = usrhas;}

        public String getDev() {return dev;}
        public MyAsyncTask setDev(String dev) {this.dev = dev; return this ;}

        public String getAcc() {return acc;}
        public MyAsyncTask setAcc(String acc) {this.acc = acc; return this; }

        public String getVal() {return val;}
        public MyAsyncTask setVal(String val) {this.val = val; return this; }

        private String headGet(String host,String port, String usr,String dev,String acc,String val) {
            // cambiamos desitno a la accion necesaria:
            String rt = usr + '/' + dev + '/' + acc + '/' + val;
            // codifcamos cabecera y datos a base64

            String dataToSend = String.format(
                    "GET /%s HTTP/1.1\r\n"
                            +"Host: %s:%s\r\n"
                            +"User-Agent: Midori/1.0 \r\n"
                            +"Gecko/20100101 \r\n"
                            +"Accept: */* \r\n"
                            +"Connection: keep-alive \r\n\r\n"
                    // encriptacion de datos:
                    ,encript(rt)
                    // ,proto
                    ,host
                    ,port
            );


            return dataToSend;
            //return rt;
        }
        private String encript(String t){

            return Base64.encodeToString(t.getBytes(), Base64.CRLF);

        }

        public String headRequest(String code){
            // recibimos el resultado y lo convertimos a texto.
            String rt=code;
            byte[] data = Base64.decode(code, Base64.CRLF);

            try {
                rt= new String(data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                rt="falla decodificacion.";
                e.printStackTrace();
            }

            // salido de tipo cadena:
            return rt;
        }

    }

    public conexion setUrl(String url){
        // forma del url:
        // <prot>://<host>:<port>
        String[] datos = url.split(":");
        proto=datos[0];
        host=datos[1].replace("/","");
        port=datos[2];
        this.url = url;
        return this;

        // {proto,host,port} = url.split(':');
    }


}