/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.config;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alice
 */
public final class Config {
    
    public static final String JDBC_DRIVER = getValor("jdbc.driver");
    public static final String JDBC_URL = getValor("jdbc.url");
    public static final String JDBC_USUARIO = getValor("jdbc.usuario");
    public static final String JDBC_SENHA = getValor("jdbc.senha");
    
    private Config(){
        
    }
    
    private static String getValor(String chave){
        Properties properties = new Properties();
        try {
            properties.load(Config.class.getResourceAsStream("config.properties"));
            return properties.getProperty(chave);
        } catch (IOException ex) {
            return null;
        }
        
    }
}
