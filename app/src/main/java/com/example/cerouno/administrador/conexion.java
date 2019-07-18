package com.example.cerouno.administrador;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.DhcpInfo;
import java.net.InetAddress;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;



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

public class conexion {
    private String url;


    private String proto, host, port, dev, acc, val; //protocolo,host,puerto,
    // disopitivo
    // accion
    // valor
    private int status;
    private String user, has ;
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
        host="http://192.168.0.103";
        port="8181";

        this._MAsync = new MyAsyncTask(host, port);

        //
        // prueba exitosa enciende una luz con el usuario leandro morala.
        //
        // this.test();

    }

    public conexion send(String dev, String acc, String val) {
        // alg oooo par hacer.

        _MAsync.setDev(dev).setAcc(acc).setVal(val);
        _MAsync.execute("");// */
        Log.i("CONEXION SEND -------", dev+" --- "+acc+" --- "+val);
        return this;
    }
    public conexion setUser(String usuario){
        this.user = usuario;
        bol_user=true;
        this._MAsync.setUsrhas(usuario);

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
        status=0;
        if (bol_user && bol_has){
            // tengo usuario y clave.
            //detectando red:
            Object t = Contexto.getSystemService(Contexto.WIFI_SERVICE);
            if (  t != null  ){
                status=1;
            }
        }
        return status;
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



    /**
     *
     *
     * codificacion de servicios
     *
     * */


    public void sendBroadcast(
            final String Usuario, final String Valor
            , String address, final int puerto
    ) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    InetAddress address = getBroadcastAddress(Contexto );

                    String Cadena = "u:" + Usuario + ":v:" + Valor;
                    byte[] Buf = Cadena.getBytes();
                    DatagramSocket clientSocket = new DatagramSocket();
                    clientSocket.setBroadcast(true);

                    DatagramPacket sendPacket = new DatagramPacket(
                            Buf
                            , Buf.length
                            , address
                            , puerto);

                    clientSocket.send(sendPacket);

                    clientSocket.close();

                } catch (Exception e) {
                    //Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, e);
                    msg.echo(e.getMessage());
                }
            }
        }).start();

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
        IOException ioException;
        boolean working=false;

        MyAsyncTask( String host,String port ) {
            super();
            this.host = host;
            this.port = port;
            // this.usrhas=usr;

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




    private class UDPServer {
        //servicio de escucha en un puerto.
        private DatagramSocket udpSocket;
        private int port;
        private String host,key;
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