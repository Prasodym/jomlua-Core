package de.jomlua;

import de.jomlua.mysql.*;
import de.jomlua.util.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;




public class JomluaCore extends JavaPlugin {

    public static Chat chat;
    public EconomyImpl economyImp;
    public VaultHook vaultHook;
    public static boolean vaultEnabled = true;
    public static JomluaCore plugin;
    public static JomluaCore instance;
    private static Economy eco;
    private static Connection connection;
    private static PreparedStatement stmt;
    public static File file = new File("plugins/JomluaCore", "worlds.yml");
    public static YamlConfiguration cfg =YamlConfiguration.loadConfiguration(file);

    public static List<String> MAPS = cfg.getStringList("Worlds");
    //public static Map<Player, Long> Online = new HashMap<>();

    public static HashMap<String, Location> back = new HashMap<>();
    public static HashMap<UUID,UUID> tpa = new HashMap<UUID, UUID>();


    public static HashMap<Player, Player> reply = new HashMap<Player, Player>();
    public static HashMap<UUID, TeleportTyp> tpType = new HashMap<UUID, TeleportTyp>();
    public final HashMap<UUID, Double> playerBank = new HashMap<>();
    public static HashMap<String, String> checkInv = new HashMap<>();

    public static HashMap<String, String> testing = new HashMap<String, String>();
    public static Map<Player, Long> playerLogTimeMap = new HashMap<>();
    public static HashMap<String, String> GivePlayer = new HashMap<>();

    Logger log = Logger.getLogger(ChatOutput.);
    public static Scoreboard score;

    @Override
    public void onEnable() {





        score  = Bukkit.getScoreboardManager().getMainScoreboard();

        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "§eDas Plugin Startet jetzt..");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "§3   |   |――――|");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "§3   |   | [] |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "§3|__|   |____|");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "§2Version §c1.6.8");
        plugin = this;

        MySQLConfig file = new MySQLConfig();

        file.setDefault();
        file.readData();

        runOnEnabled();
        BorderUtils.setBorder();
        // Plugin startup logic
        if (!setupChat()){
            this.getLogger().log(Level.WARNING,"Vaut plugin wurde nicht gefunden oder geladen.");
            vaultEnabled = false;
        }
        RTeleportUtil yeet = new RTeleportUtil(this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        PlayTimePlayers timer = new PlayTimePlayers();
        timer.getPlayTimer();

        getCommands.setUp();
        getListener.setUp();

        for (String map : MAPS) {
            WorldCreator w = WorldCreator.name(map);
            Bukkit.createWorld(w);
            Bukkit.getWorlds().add(Bukkit.getWorld(map));
        }


        MySQL.connect();
        SqlExistTable.sqlUser();
        SqlExistTable.sqlBans();
        SqlExistTable.sqlInventory();
        //SqlExistTable.sqlGroups();
        //SqlExistTable.sqlPermissions();
        //SqlExistTable.sqlBlocks();

        loadConfig();




    }




    public static JomluaCore getPlugin() {
        return plugin;
    }

    public static JomluaCore getInstance(){

        return instance;
    }

    private static void setInstance(JomluaCore instance){
        JomluaCore.instance = instance;
        JomluaCore.instance = instance;
    }
    public void loadConfig() {

        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        //config.options().copyHeader(true);

        this.saveDefaultConfig();
    }
    private boolean setupChat(){
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
    private boolean setupEconomy(){
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }

    private void instanceClasses(){
        plugin = this;
        economyImp = new EconomyImpl();
        vaultHook = new VaultHook();
    }
    public void runOnEnabled(){
        instanceClasses();
        vaultHook.hook();
    }



    @Override
    public void onDisable() {



        MySQL.disconnect();
        vaultHook.unHook();
        //QuitListener.QuitPlayerSaveData(Bukkit);

        Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§eBis bald!");
        // Plugin shutdown logic
    }
}
