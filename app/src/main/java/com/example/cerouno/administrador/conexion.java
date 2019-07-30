package com.example.cerouno.administrador;
// package com.example.testconexion;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.DhcpInfo;
import java.net.InetAddress;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.ExecutionException;

public class conexion {
    private String url;


    private String proto, host, port, dev, acc, val; //protocolo,host,puerto,
    // disopitivo
    // accion
    // valor
    private int status;
    private String user, has ;
    private onPostExecute FuncionAEjecutar;
    private boolean bol_user =false,bol_has=false;
    private MyAsyncTask _MAsync;
    private Context Contexto;
    private WifiManager wifi;


    /*objeto especial para procesar respuestas */
    //private resultado rts;

    public conexion(Context Contexto) {
        /*
        * TODO: clase de coneccion se requiere :
            url conformada por <protocolo>://<host>:<port>

        */


        // funcion de prueba de coneccion.
        this.Contexto = Contexto;

        // this.setUrl(url);
        // this._MAsync = new MyAsyncTask(host, port, usrhas);

        // host="192.168.155.10";
        port="8181";

        // entre otras cosas buscar la raspberry:



        //
        // prueba exitosa enciende una luz con el usuario leandro morala.
        //
        // this.test();

    }

    public conexion send(String dev, String acc, String val){
        send(dev,acc,val,new conexion.onPostExecute() {
            @Override
            public void recibirTexto(String txt, int est) {
            }
        });
        return this;
    }

    public conexion send(String dev, String acc, String val,onPostExecute EjecutarDespues) {
        // alg oooo par hacer.
        if (_MAsync == null) {
            // primera vez obteniendo informacion de contexto

            if (host==null || host=="" ) {
                msg.echo("buscar raspberry....");
                ScanRaspberry("4net-core"
                        , port
                        , "_domotica._tcp",EjecutarDespues);
            }else {
                // no se ejecuta si no tengo el host
                this._MAsync = new MyAsyncTask(host, port, this);
                this._MAsync.setUsrhas(this.has);

                _MAsync
                        .setDev(dev)
                        .setAcc(acc)
                        .setVal(val)
                        .execute();

                FuncionAEjecutar = EjecutarDespues;
            }
        }else{
            Toast.makeText(Contexto
                    , "Hay una tecla funcionando aun."
                    , Toast.LENGTH_LONG).show();
        }
        // _MAsync.execute();// */
        Log.i("CONEXION SEND -------", dev+" --- "+acc+" --- "+val);
        return this;
    }


    public conexion setUser(String usuario){
        this.user = usuario;
        bol_user=true;

        return this;
    }
    public conexion setHash(String hash){
        this.has=hash;
        bol_has=true;

        return this;
    }

    /***
     * return int ( 0=falla no hay conecion)
     * (1=falla no autorizado)
     * (2=ok)
     * */

    public int getStatus(){
        return getStatus(new onPostExecute() {
            @Override
            public void recibirTexto(String txt, int est) {
                // no hay accion asociada al recibir texto
            }
        });
    }

    public int getStatus(onPostExecute EjecutarDespues){
        status=0;
        if ( ( bol_user && bol_has ) && ( getWIFIStatus() == 1 ) ){
            status=1;
            /*
            if (_MAsync == null) {
                // primera vez obteniendo informacion de contexto
                ScanRaspberry("4net-core", "8181","_domotica._tcp");
                this._MAsync = new MyAsyncTask(host, port, this);
                this._MAsync.setUsrhas(this.has);
            }

            _MAsync.setDev("GP0")
                    .setAcc("R")
                    .setVal("0")
                    .execute();

            FuncionAEjecutar=EjecutarDespues;
            */
            send("GP0","R","0",EjecutarDespues);

        }
        return status;
    }


    private boolean validarLogin(String rtUsr){
        return user == rtUsr;
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



    /* objeto especial para procesar respuestas inicio */
    private resultado rts;
    private class resultado{
        public String staus;
        public String usuario;
        public Object respuesta;
        public int error;

        public resultado(JSONObject objSon){
            try {
                staus = objSon.getString("status");
                usuario=objSon.getString("usuario");
                respuesta=objSon.getJSONObject("respuesta");
                error=objSon.getInt("error");

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
    /* objeto especial para procesar respuestas fin */

    protected void onPostProcesor(String respuesta){
        // procesando respuesta recibida.
        receive();
        String rtspost="";
        // deve ejecutar la funcion asignada ( si existiera )
        if (rts.staus.compareToIgnoreCase("ok")==0 ) {
            // validar usuario en cada peticion.
            status = 2;
        }else
            status = 1;

        if (!(((JSONObject) rts.respuesta).isNull("shell"))) {
            rtspost = ((JSONObject) rts.respuesta).opt("shell").toString();
        }
        FuncionAEjecutar.recibirTexto(rtspost, status);

        //_MAsync.
        // destruir el conector.
        _MAsync = null;

    }

    public interface onPostExecute{

        void recibirTexto(String txt, int estado);
    }

    public String receive(){

        String rt= null;
        try {
            rt = ((MyAsyncTask) _MAsync).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // msg.echo("datos recibidos fin de espera.:"+rt);
        try {
            rts = new resultado(new JSONObject(rt));
            //msg.var_dump(rts);

        } catch (JSONException e) {
            rts = new resultado(new JSONObject());
            e.printStackTrace();
        }

        return rt;
    }




    /*
     *
     * clase asincronica de comunicacion de datos.
     *
     * */


    private class MyAsyncTask extends AsyncTask<String, Void, String>
    {

        String host,port,headget,response;
        private int contadorSend =0;
        String usrhas, dev,acc,val;
        // private onPostExecute FuncionPost;
        private conexion padre;
        IOException ioException;
        boolean working=false;

        MyAsyncTask( String host,String port ,conexion padre) {
            super();

            this.host = host;
            this.port = port;
            // this.usrhas=usr;
            this.padre=padre;
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
            padre.onPostProcesor(result);
        }

        public String getResponse(){

            return this.response;
        }

        public String getUsrhas() { return usrhas;}
        public void setUsrhas(String usrhas) {
            this.usrhas = usrhas;

        }

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


    /**
     * TODO: conexion automatica con la raspberry
     *
     */
    public void ScanRaspberry(String Nombre,String Puerto,String BusquedaBonjour,onPostExecute Ejecutor){

        // funcion de buqueda del equipo
        // "_domotica._tcp"

        InetAddress test= null;
        boolean ch=false;
        msg.echo("buscando la raspberry pi.............");

        try {
            // solo envia paquete al broadcast
            sendBroadcast(
                    this.user
                    ,this.has
                    ,8182,Ejecutor);//(int) Integer.getInteger(Puerto));
            ch=true;
            msg.echo("se ha enviado el broadcast");
        } catch (Exception e) {
            e.printStackTrace();
            ch=false;
        }


        msg.echo("fin de la busqueda de la raspberry.");

    }
    public void setHost(String host){
        // la clave deve coincidir con el has de usuario
        this.host = host;
        status=2;
    }

    /**
     *
     *
     * codificacion de servicios
     *
     * */


    public void sendBroadcast(
            final String Usuario, final String Valor
            , final int puerto
            , final onPostExecute Ejecutor
    ) throws Exception {
        InetAddress respuesta = null;
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    msg.echo("enviando datagrama");
                    InetAddress address = getBroadcastAddress(Contexto );
                    msg.echo("broadcast obtenido.");
                    String Cadena = "u:" + Usuario + ":v:" + Valor;
                    byte[] Buf = Cadena.getBytes();


                    msg.echo("verificando.");
                    DatagramSocket clientSocket = new DatagramSocket();
                    clientSocket.setBroadcast(true);

                    msg.echo("preparando envio.");
                    DatagramPacket sendPacket = new DatagramPacket(
                            Buf
                            , Buf.length
                            , address
                            , puerto);

                    msg.echo("enviado.");
                    clientSocket.send(sendPacket);


                    DatagramPacket peticion = new DatagramPacket(Buf, Buf.length);
                    msg.echo("recepcion:");
                    clientSocket.receive(peticion);
                    String txt = new String(peticion.getData());

                    msg.echo("autorizacion:"+txt  );
                    setHash(txt.substring(0,13));
                    setHost(peticion.getAddress().getHostAddress().toString());

                    msg.echo("host:"+ peticion.getAddress().getHostAddress().toString() );
                    Ejecutor.recibirTexto("broadcast",1);
                    clientSocket.close();

                } catch (Exception e) {
                    //Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, e);
                    msg.echo(e.getMessage());
                }
            }
        }).start();

    }



    private class UDPServer {
        //servicio de escucha en un puerto.
        private DatagramSocket udpSocket;
        private int port;
        public String host,key;
        private String msg;
        // private Activity activity;

        public UDPServer( int port ) throws SocketException, IOException {
            this.port = port;
            this.udpSocket = new DatagramSocket(this.port);
            /* udpSocket .*/
            //this.activity = activity;
            this.host="";
        }

        public void listen() throws Exception {

            // String msg;

            while (true) {

                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                // blocks until a packet is received
                udpSocket.receive(packet);
                // convirtiendo el datagrama en string recibido.
                msg = new String(packet.getData()).trim();
                /*
                if (msg.equals("Okkkk")) {
                    msg = "Message from " + packet.getAddress().getHostAddress() + ": " + msg;
                    final String finalMsg = msg;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, finalMsg, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                */
                Log.e("UDP_SERVER", "Message from "
                        + packet.getAddress().getHostAddress()
                        + ": " + msg);
                host=packet.getAddress().getHostAddress();
                key=msg;
            }


        }

    }

    // private class NetworkHelper {


    private int getWIFIStatus(){
        InetAddress ch = null;
        try {
            ch = getBroadcastAddress(Contexto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ch!=null)
            return 1;
        else
            return 0;


    }
    /**
     * Método que se encarga de obtener la dirección de broadcast de la subred.
     * @return  InetAddres con la dirección de broadcast.
     */
    private static InetAddress getBroadcastAddress(Context context) throws Exception {

        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcp = wifi.getDhcpInfo();

        int broadcast = (dhcp.ipAddress & dhcp.netmask) | ~dhcp.netmask;
        byte[] quads = new byte[4];
        for (int k = 0; k < 4; k++)
            quads[k] = (byte) (broadcast >> (k * 8));

        return InetAddress.getByAddress(quads);
    }

    /**
     * Método que se encarga de obtener la dirección ip del dispositivo.
     * @return  InetAddres con la dirección ip del dispositivo.
     */
    private static InetAddress getLocalIpAddress(Context context) throws IOException{

        final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        final ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putInt(wifiManager.getConnectionInfo().getIpAddress());
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByAddress(null, byteBuffer.array());
        } catch (Exception e) {
            msg.echo("NetworkHelper"+ e.getMessage() );
        }
        return inetAddress;
    }

    //}

}