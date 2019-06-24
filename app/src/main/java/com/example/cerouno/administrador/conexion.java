package com.example.cerouno.administrador;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Base64;


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



    private String proto, host,port,dev,acc,val; //protocolo,host,puerto,
        // disopitivo
        // accion
        // valor
    private int status;
    private String has;
    private MyAsyncTask  _MAsync;
    private Context Contexto;
    private WifiManager wifi;


    /*objeto especial para procesar respuestas */
    private resultado rts;
    public conexion(Context Contexto, String usrhas, String url){
        /*
        * TODO: clase de coneccion se requiere :
            url conformada por <protocolo>://<host>:<port>

        */


        // funcion de prueba de coneccion.
        this.Contexto = Contexto;
        this.has=usrhas;

        this.setUrl(url);
        this._MAsync = new MyAsyncTask(host,port,usrhas);

        //
        // prueba exitosa enciende una luz con el usuario leandro morala.
        //
        // this.test();

    }

    public static String urlDelCorePi() {

        return "http://192.168.155.14:8181";

    }

    public Boolean send(String  dev,String acc,String val,Boolean sinRespuesta){

        send(dev,acc,val);
        return false;

    }
    public conexion send(String  dev,String acc,String val){
        // alg oooo par hacer.

        _MAsync.setDev(dev).setAcc(acc).setVal(val);
        _MAsync.execute("");
        return this;
    }


    public raspberry raspyServer(){
        // devolver un objeto raspberry completo:

        msg.echo("enviando solicitud de datos;");
        send("GP0","informacion","0");

        msg.echo("recibiendo datos:");
        receive();

        msg.echo("procesando datos:");
        usuario usr = new usuario("",has);

        msg.echo("construccion de raspberry:");
        raspberry rasp = new raspberry();
        //Context c = Contexto;

        /*
        class descriptor{

            public int id=0;
            public String tipo="";
            public String habitacion="";
            public String nombre="";
            public String posicion="";
            public String imagen="";
            public descriptor(JSONObject objetoJSON) {

                try {
                    id = objetoJSON.getInt("id");
                    tipo = objetoJSON.getString("tipo");
                    habitacion = objetoJSON.getString("habitacion");
                    nombre = objetoJSON.getString("nombre");
                    posicion = objetoJSON.getString("posicion");
                    imagen = objetoJSON.getString("imagen");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


        msg.echo("construccion de raspberry:");
        raspberry rasp = new raspberry();
        rasp.setHashUsaroi(usr.getHash());

        if (rts != null ) {

            /*
            * TODO: conexion ok se debe construir todos los objetos a partid de aqui.
            *
            * * /
            msg.var_dump(rts);
            if (rts.error == 0 && rts.staus == "ok"){
                if (rts.respuesta != null ) {
                    msg.echo("cnt: construyendo:");

                    JSONObject listado = (JSONObject) rts.respuesta;
                    // JSONObject desc = new JSONObject(descriptor.class);

                    msg.var_dump(listado);

                    descriptor desc = new descriptor(listado );
                    habitacion hab ;//= new habitacion(rasp,0,"");

                    for (int ind =0 ; ind < listado.length() ; ind++ ) {
                        msg.echo("cnt: construcciones: bucle");
                        try {
                            desc = new descriptor(listado.getJSONObject(ind+""));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // constructor de objetos:

                        if (rasp.getHabitacionByName(desc.habitacion) != null ){
                            // si la habitacion existe => un nuevo control a la misma.
                            rasp.getHabitacionByName(desc.habitacion).getArrayControles().add(
                                    (new Televisor())
                                        .setHabitacion(rasp.getHabitacionByName(desc.habitacion))
                                        .setTelevisor(desc.tipo)
                                        .setDispositivo(desc.nombre)
                            );

                        }else{
                            hab =  new habitacion( ).setPadre(rasp)
                                    .setIdValue(desc.id+0)
                                    .setNombreHabitacion( desc.habitacion+"");
                            //desc.id,desc.habitacion +""))
                            hab.getArrayControles().add(
                                    (new Televisor().setHabitacion(hab))
                                            .setTelevisor(desc.tipo)
                                            .setDispositivo(desc.nombre)
                            );
                            rasp.getHabitacionAray().add(hab);
                        }
                    }
                    /**
                     *
                     *      "0":{"id":"1","tipo":"CONTROL0","habitacion":"living","nombre":"TV0","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "1":{"id":"2","tipo":"CONTROL0","habitacion":"Comedor","nombre":"TV1","posicion":"2","imagen":"[img:\"Cubiertos.png\"]"},
                     *      "2":{"id":"3","tipo":"CONTROL0","habitacion":"living","nombre":"DVD","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "3":{"id":"4","tipo":"CONTROL0","habitacion":"Comedor","nombre":"PR0","posicion":"2","imagen":"[img:\"Cubiertos.png\"]"},
                     *      "4":{"id":"5","tipo":"LUZ","habitacion":"living","nombre":"GP0","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "5":{"id":"6","tipo":"LUZ","habitacion":"living","nombre":"GP1","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "6":{"id":"7","tipo":"LUZ","habitacion":"living","nombre":"GP2","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "7":{"id":"8","tipo":"LUZ","habitacion":"living","nombre":"GP3","posicion":"0","imagen":"[img:\"sillon.png\"]"},
                     *      "8":{"id":"15","tipo":"LUZ","habitacion":"Ba\u00f1o","nombre":"GP4","posicion":"3","imagen":"[img:\"ba\u00f1o.png\"]"},
                     *      "9":{"id":"16","tipo":"LUZ","habitacion":"DormitorioPrincipal","nombre":"GP5","posicion":"4","imagen":"[img:\"cama.png\"]"},
                     *      "10":{"id":"17","tipo":"LUZ","habitacion":"DormitorioPrincipal","nombre":"GP6","posicion":"4","imagen":"[img:\"cama.png\"]"},
                     *      "11":{"id":"18","tipo":"LUZ","habitacion":"DormitorioSecundario","nombre":"GP7","posicion":"5","imagen":"[img:\"cama.png\"]"
                     *
                     *
                     * * /
                }

            }


        }else{
            // rts es nullo.
            return null;
        }
        return rasp;
        */

        return rasp.raspyJSONConverter(rts.ValorRespuesta);


    }

    public static JSONObject informacion(String HasUsr,String Url){

        // obtiene informacion desde el servidor.
        conexion ctn = new conexion(null , HasUsr , Url);

        // devolver un objeto raspberry completo:

        msg.echo("enviando solicitud de datos;");
        ctn.send("GP0","informacion","0");

        msg.echo("recibiendo datos:");
        ctn.receive();
        resultado prev = ctn.getRts();
        if (prev.error == 0 && prev.respuesta!= null){

            return (JSONObject) prev.respuesta;
        }else{
            return null;
        }
    }

    public resultado getRts(){
        return rts;
    }

    public String receive(){

        /*msg.echo(((MyAsyncTask) _MAsync).working ? "trabajando":"no trabaja");

        while (((MyAsyncTask) _MAsync).working){
            try {
                wait(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String rt=((MyAsyncTask) _MAsync).getResponse();
        */
        // esperando y obteniendo el resultado .... congela el hilo principal.
        // try {
            //wait(1*1000);
            msg.esperar((long) 1000 );
       /* } catch (InterruptedException e) {
            e.printStackTrace();
            msg.echo(e.getMessage());
        }
        */
        String rt= null;
        try {
            rt = ((MyAsyncTask) _MAsync).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //msg.msg(Contexto, rt);
        msg.echo("datos recibidos fin de espera.:"+rt);
        try {
            rts = new resultado(new JSONObject(rt));
            //msg.var_dump(rts);

        } catch (JSONException e) {
            rts = new resultado(new JSONObject());
            e.printStackTrace();
        }

        return rt;
    }

    private class resultado{
        public String staus;
        public String usuario;
        public Object respuesta;
        public int error;
        public JSONObject ValorRespuesta;

        public resultado(JSONObject objSon){
            this.ValorRespuesta = objSon;

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


    public String mostrarIP()
    {
        String ip="";
        try
        {
            // @SuppressWarnings("deprecation")
            //Método que devuelve el valor de la IP Local asignada al dispositivo.
            // No soporta IPv6. Se recomienda el uso de la Clase InetAddress (ver nota 2 abajo

            String ip_local = Formatter.formatIpAddress(wifi.getConnectionInfo().getIpAddress());
            //Toast.makeText(this, "IP Local: " + ip_local, Toast.LENGTH_LONG).show();
            ip=ip_local;

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return ip;

    }

    // evaluar el estado del servicio de wifi:

    public int mostrarEstado(){
        int estadoWifi;
        try
        {
            //Método que devuelve un valor de verdadero o falso si se ha establecido la conexión Wifi.
            estadoWifi = wifi.isWifiEnabled() ? 2 : 1 ;
        }catch(Exception ex)
        {
            estadoWifi =0;
        }
        return estadoWifi;

    }

    private String TestBonjour(String nombreHost){
        // Socket socket = new Socket();
        String ip="";
        int puerto = Integer.parseInt( port);

        InetSocketAddress address;
        try {
            address = new InetSocketAddress(nombreHost+"",    puerto+0);
            ip = address.getAddress().toString();
            // socket.connect(address);
        }catch (Exception e) {
            msg.Console("error:","no se pudo obtener datos.");
        }



        msg.echo(" direccion ip de la raspberry: " + ip );
        return ip;
    }

    private boolean ping(String host){

        msg.echo("executeCommand");
        Runtime runtime = Runtime.getRuntime();
        try
        {
            Process  mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 "+host);
            int mExitValue = mIpAddrProcess.waitFor();
            msg.echo(" mExitValue "+mExitValue);
            if(mExitValue==0){
                return true;
            }else{
                return false;
            }
        }
        catch (InterruptedException ignore)
        {
            ignore.printStackTrace();
            msg.echo(" Exception:"+ignore);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            msg.echo(" Exception:"+e);
        }
        return false;
    }

    public String encontrarRaspberry(String hostNombre){
      if (mostrarEstado()==2){
          // el celuar esta conectado
          String testIP = TestBonjour(hostNombre);
            if (ping(testIP)){
                /*
                * TODO: host confirmado. y aprobado.
                *
                * */
                return testIP;
            }
      }
      return null;
    }



    public String test(){
        proto="http";
        host="192.168.155.10";
        port="8181";
        has="159357";
        dev="GP0";
        acc="A";
        val="0";
        //msg.msg(this.Contexto,"ejecutando prueba:");
        msg.esperar(300);

        // this.Contexto=Contexto;
        _MAsync = new MyAsyncTask(host,port, has );  //"NTYzNzY5L0dQMC9BLzA=");
        //msg.msg(Contexto,"ejecutando solicitud:");
        _MAsync.setDev(dev).setAcc(acc).setVal(val);

        _MAsync.execute("");

        String rt="";
        //Object t = new MyAsyncTask("192.168.155.10","8181","NTYzNzY5L0dQMC9BLzA=");

        // ((MyAsyncTask) _MAsync).execute();

        while (((MyAsyncTask) _MAsync).working){
            try {
                wait(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        rt=((MyAsyncTask) _MAsync).response;
        //msg.msg(Contexto, rt);

        return rt;
    }



    /*
    *
    * AUTODESCUBRIMIENTO
    *
    *
    * */

    /*
    * INCIALIZADO DEL SERVICIO
    * */
    private class bounjure {

        private String TAG ="bounjure:";

        private String SERVICE_TYPE;
        private String serviceName;
        private NsdManager nsdManager;
        private NsdManager.DiscoveryListener discoveryListener;
        private NsdManager.ResolveListener resolveListener;

        public bounjure(){
            initializeDiscoveryListener();
        }

        public NsdManager.DiscoveryListener getDiscoveryListener(){
            return discoveryListener;
        }

        public void initializeDiscoveryListener() {

            // Instantiate a new DiscoveryListener
            initializeResolveListener();

            discoveryListener = new NsdManager.DiscoveryListener() {

                // Called as soon as service discovery begins.
                @Override
                public void onDiscoveryStarted(String regType) {
                    msg.Console(TAG, "Service discovery started");

                }

                @Override
                public void onServiceFound(NsdServiceInfo service) {
                    // A service was found! Do something with it.
                    msg.Console(TAG, "Service discovery success" + service);
                    if (!service.getServiceType().equals(SERVICE_TYPE)) {
                        // Service type is the string containing the protocol and
                        // transport layer for this service.
                        msg.Console(TAG, "Unknown Service Type: " + service.getServiceType());
                    } else if (service.getServiceName().equals(serviceName)) {
                        // The name of the service tells the user what they'd be
                        // connecting to. It could be "Bob's Chat App".
                        msg.Console(TAG, "Same machine: " + serviceName);
                    } else if (service.getServiceName().contains("NsdChat")) {
                        nsdManager.resolveService(service,  resolveListener);
                    }
                }

                @Override
                public void onServiceLost(NsdServiceInfo service) {
                    // When the network service is no longer available.
                    // Internal bookkeeping code goes here.
                    msg.Console(TAG, "SERVICIO PERDIDO: " + service);
                }

                @Override
                public void onDiscoveryStopped(String serviceType) {
                    msg.Console(TAG, "Discovery stopped: " + serviceType);
                }

                @Override
                public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                    msg.Console(TAG, "Discovery failed: Error code:" + errorCode);
                    nsdManager.stopServiceDiscovery(this);
                }

                @Override
                public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                    msg.Console(TAG, "Discovery failed: Error code:" + errorCode);
                    nsdManager.stopServiceDiscovery(this);
                }
            };
        }

        public void listar(){
            nsdManager.discoverServices(
                    SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener);

        }

        public void initializeResolveListener() {
            msg.echo("activando el resolvelistener.");

            resolveListener = new NsdManager.ResolveListener() {

                @Override
                public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                    // Called when the resolve fails. Use the error code to debug.
                    msg.echo("Resolve failed: " + errorCode);
                }

                @Override
                public void onServiceResolved(NsdServiceInfo serviceInfo) {
                    msg.echo( "Resolve Succeeded. " + serviceInfo);

                    if (serviceInfo.getServiceName().equals(serviceName)) {
                        msg.echo( "Same IP.");
                        return;
                    }
                    NsdServiceInfo mService = serviceInfo;
                    int port = mService.getPort();
                    InetAddress host = mService.getHost();
                    msg.echo("log encontrado:"+host.getHostAddress()+";"
                            +" nombre:"+host.getHostName()+";"
                            +"::"+host.getAddress()+";");
                }
            };
        }
    }

    /*********************************
     *
     *
     * GETTER Y SETTER
     *
     * ******************************/
    public String getUrl(){
        return url;

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
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public conexion setPort(String port) {
        this.port = port;
        return this;
    }

    public String getDev() {
        return dev;
    }

    public conexion setDev(String dev) {
        this.dev = dev;
        return this;
    }

    public String getAcc() {
        return acc;
    }

    public conexion setAcc(String acc) {
        this.acc = acc;
        return this;
    }

    public String getVal() {
        return val;
    }

    public conexion setVal(String val) {
        this.val = val;
        return this;

    }

    public int getStatus() {
        return status;
    }

    public conexion setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getHas() {
        return has;
    }

    public conexion setHas(String has) {
        this.has = has;
        return this;
    }

    public Context getContexto() {
        return Contexto;
    }

    public conexion setContexto(Context contexto) {
        Contexto = contexto;
        return this;
    }


    /* ********************************
    *
    *
    * private clase soket conection.
    *
    *  new MyAsyncTask(host, port, get).execute();
    *
    * ********************************** */



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
            msg.var_dump(this);
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

}
