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

// clase CONEXION DE LA UML

public class miwebService {

    String url;
    int status;
    Usuario usuario = new Usuario();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Usuario getHash() {
        return hash;
    }

    public void setHash(Usuario hash) {
        this.hash = hash;
    }

    int Status;
    Usuario hash = new Usuario();

    public boolean getVerificarRaspi(){

        return true;
    }

}
