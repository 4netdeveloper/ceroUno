package com.desarrollo.cerouno.administrador.conex;

import org.json.JSONException;

public interface onPostExecute {
    void recibirTexto(String txt, int estado) throws JSONException;
}
