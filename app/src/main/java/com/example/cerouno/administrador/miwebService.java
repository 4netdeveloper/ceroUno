package com.example.cerouno.administrador;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import static android.support.v4.content.ContextCompat.getSystemService;

public class miwebService {
    private String[] Estados={"offLine", "onLine","onLan"};
    private String host;
    private String estadoEth;
    private Boolean StatusOnline=false;
    private Boolean StatusOnLan=false;
    private Boolean Error=false;
    private String Error_mesage  = "";
    private Context enContexto;
    private HttpURLConnection urlConnection;
    private JSONObject respuesta;


    public miwebService(String host,Context contexto){
        // TODO constructor y verificador de red.
        this.host = host;
        this.enContexto = contexto;
        this.run();

    }
    public void run(){
        StatusOnline = checkRed();
        if (StatusOnline){
            Console.echo("obtener informacion de coneccion con la raspberry:");
            Console.echo("testBonjour:"+TestBonjour());
            StatusOnLan = chechRaspyLan("/",true);
            Console.echo("la raspberry tiene la ip:" + this.TestBonjour() );
        }
    }

    public void setContext(Context contex){
        this.enContexto = contex;
    }

    public void setHost(String host){
        this.host = host ;
    };

    public String getStatus(){
        int st;
        if (StatusOnline){
            if (StatusOnLan){
                st=2;
            }else{
                st=1;
            }
        }else st=0;
        return Estados[st];
    }

    public String getErrores(){
        String rt = this.Error_mesage;
        this.Error_mesage = "";
        return rt;
    }

    public boolean ira(String strurl)  {

        return chechRaspyLan(strurl,false);
    }

    private Boolean chechRaspyLan(String strurl,Boolean onlyCheck ){

        Boolean rt =false;
        int SotopTime = 5000; // tiempo de espera de una solicitud.
        String onlyCheckStr = this.host + "/index.php/checkstatus";
        if (this.StatusOnline ) {
            // fabricando objeto url:
            URL objecturl ;
            if (onlyCheck) {
                strurl = onlyCheckStr;
                SotopTime = 2000;
            }
            Console.echo("solicitando::"+strurl);
            try {
                // decidiendo si se chequea o se solicita informacion:
                objecturl = new URL(strurl);
            } catch (MalformedURLException e) {
                // en caso de falla:
                Console.echo("error en formacion:<"+e.getMessage()+">");
                this.Error = true;
                this.Error_mesage += ":" + e.getMessage();
                return false;
            }
            Console.echo("Constryendo la conneccion....");
            // generando el objeto de navegacion pertinente.
            HttpURLConnection urlConnection  ;
            try {
                urlConnection = (HttpURLConnection) objecturl.openConnection();
                Console.echo("estableciendo tiempo de vida.");
                urlConnection.setConnectTimeout(SotopTime);
                // ejecutar la accion propiamente dicha de navegacion:
                Console.echo("Ejecutando....");
                urlConnection.connect();
            } catch (IOException e) {
                Console.echo("error en peticion:<"+e.getMessage()+">");
                this.Error = true;
                this.Error_mesage += ":" + e.getMessage();
                return false;
            }
            Console.echo("esperando datos....");
            try {
                // obteniendo los datos de respuesta:
                // test = urlConnection.getInputStream();
                // InputStream in = new BufferedInputStream( urlConnection.getInputStream() );
                InputStream in =  urlConnection.getInputStream() ;
                    try {
                        Console.echo("informacion recibida---<"+in.toString()+">---");
                        respuesta = new JSONObject( in.toString() );
                    } catch (JSONException e) {
                        // fallo la convercion del json.
                        Console.echo("error en recepcion:<"+e.getMessage()+">");
                        this.Error = true;
                        this.Error_mesage += ":" + e.getMessage();
                        return false;
                    }


            } catch (IOException e) {
                // fallo la adquisicion de los datos.
                Console.echo("error en recepcion mas general:<"+e.getMessage()+">");
                this.Error = true;
                this.Error_mesage += ":" + e.getMessage();
                return false;
            }
            return true; // todo se ejecuto correctamente.
        }else return false; // TODO la red esta cerrada.
    }

    private Boolean checkRed() {
        // funcion de chequeo comprobaciond e estado de conecion.

        if (enContexto == null )return false;
        // generando el manegador de redes:
        ConnectivityManager connMgr;
        try {
            // intentando verificar la red.
            connMgr = (ConnectivityManager) enContexto.getSystemService(enContexto.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Console.echo("la red fue verificada:");
            if (networkInfo != null && networkInfo.isConnected()) {
                // Operaciones http
                // this.estadoRaspberry = "onRed";
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            // fallo la adquisicion de los datos.
            this.Error = true;
            this.Error_mesage += ":" + e.getMessage();
            Console.echo("error y falla:<"+e.getMessage()+">");
            Console.echo("--------------------------------------------");
            return false;
        }
    }
    /*
    no funciona
     * /
    private void TestBonjour(){
        final JmDNSImpl jmdns = new JmDNSImpl(null, null);
        final HostInfo hostInfo = HostInfo.newHostInfo(InetAddress.getByName("192.168.1.78"), jmdns, null);
        System.out.println("MDNS hostname (Bonjour): " + hostInfo.getName());
        System.out.println("DNS hostname: " + hostInfo.getInetAddress().getHostName());
        System.out.println("IP address: " + hostInfo.getInetAddress().getHostAddress());
        jmdns.close();
    }
    */
    private String TestBonjour(){
        // Socket socket = new Socket();
        InetSocketAddress address;
        try {
            address = new InetSocketAddress("4net-core.net", 8181);
            // socket.connect(address);
        }catch (Exception e) {
            Console.echo("error:","no se pudo obtener datos.");
            return "...";
        }

        String ip = (String) address.getAddress().toString();

        Console.echo(" direccion ip de la raspberry: " + ip );
        return ip;
    }
}
