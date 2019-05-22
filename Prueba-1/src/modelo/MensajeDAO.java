/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Francisca Carmona
 */
public class MensajeDAO {

    Conexion con;

    public MensajeDAO() {
        this.con = new Conexion();
    }

    public ArrayList<Mensaje> getMensaje(Usuario nuevoUsuario) throws SQLException {
        ArrayList<Mensaje> mensajes = new ArrayList();
        Connection accesoBD = con.getConexion();

        int id = nuevoUsuario.getId_usuario();

        try {
            String sql = "SELECT * FROM usuario where id=" + id;

            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);

            while (resultados.next()) {
                int id_mensaje = Integer.parseInt(resultados.getString("id_mensaje"));
                Usuario emisor = resultados.getString("id_usr_emisor");
                Usuario receptor = resultados.getString("id_usr_receptor");
                String contenido = resultados.getString("contenido");

                mensajes.add(new Mensaje(id_mensaje, emisor, receptor, contenido));
            }

            accesoBD.close();
            return mensajes;
        } catch (Exception e) {

        }
    }
}
