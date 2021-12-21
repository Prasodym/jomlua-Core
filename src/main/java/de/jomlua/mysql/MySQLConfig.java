package de.jomlua.mysql;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MySQLConfig {
    public void setDefault(){
        FileConfiguration cfg = getFileConfiguration();

        cfg.options().copyDefaults(true);

        cfg.addDefault("Mysql.host", "localhost");
        cfg.addDefault("Mysql.port", "3306");
        cfg.addDefault("Mysql.database", "test");
        cfg.addDefault("Mysql.user", "root");

        cfg.addDefault("Mysql.prepStmtCacheSqlLimit", "2548");
        cfg.addDefault("Mysql.cachePrepStmts" ,"true");
        cfg.addDefault("Mysql.prepStmtCacheSize", "250");

        try {
            cfg.save(getFile());
        }catch (IOException e){
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + ChatOutput.DANGER.getText()+"Die MYSQL datei konnte nicht´geladen werden.");
        }
    }

    private File getFile(){
        return new File("plugins/JomluaCore", "mysql.yml");
    }

    private FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getFile());
    }
    public void readData(){
        FileConfiguration cfg = getFileConfiguration();

        MySQL.host = cfg.getString("Mysql.host");
        MySQL.port = cfg.getString("Mysql.port");
        MySQL.database = cfg.getString("Mysql.database");
        MySQL.user = cfg.getString("Mysql.user");
        MySQL.password = cfg.getString("Mysql.password");

        MySQLHikari.cachePrepStmts = cfg.getBoolean("Mysql.cachePrepStmts");
        MySQLHikari.prepStmtCacheSqlLimit = cfg.getInt("Mysql.prepStmtCacheSqlLimit");
        MySQLHikari.prepStmtCacheSize = cfg.getInt("Mysql.prepStmtCacheSize");


    }
}

