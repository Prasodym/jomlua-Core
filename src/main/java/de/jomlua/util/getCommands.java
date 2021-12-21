package de.jomlua.util;

import de.jomlua.JomluaCore;
import de.jomlua.commands.*;
import de.jomlua.commands.Secure.ExecuteBan;
import de.jomlua.commands.Secure.kick;
import de.jomlua.commands.Teleports.*;
import de.jomlua.commands.tpa.tpa;
import de.jomlua.commands.tpa.tpaacept;
import de.jomlua.commands.tpa.tpadeny;
import de.jomlua.commands.tpa.tpahere;
import de.jomlua.util.Config.ChatOutputConfig;
import org.bukkit.command.CommandExecutor;

import java.io.File;


public class getCommands{
    public static ChatOutputConfig message;

    public static void setUp(){
        LoadConfigs();
        ListCommands();

    }
    public static void LoadConfigs(){
        message = new ChatOutputConfig(new File("plugins/JomluaCore", "Chat.yml"));
    }

    /**
     *
     * @param commandName Set Command in String "heal"
     * @param commandClass set new CMDCommans()
     *
     */
    private static void command(String commandName, CommandExecutor commandClass){
        JomluaCore.plugin.getCommand(commandName).setExecutor(commandClass);
    }

    public static void ListCommands(){

        JomluaCore.plugin.getCommand("heal").setExecutor(new CMDHeal());
        JomluaCore.plugin.getCommand("m").setExecutor(new CMDtell());
        JomluaCore.plugin.getCommand("r").setExecutor(new CMDtell());
        JomluaCore.plugin.getCommand("fly").setExecutor(new CMDFly());
        JomluaCore.plugin.getCommand("setspawn").setExecutor(new CMDSetSpawn());
        JomluaCore.plugin.getCommand("spawn").setExecutor(new CMDSpawn());
        JomluaCore.plugin.getCommand("setfarmwelt").setExecutor(new CMDSetFarmwelt());
        JomluaCore.plugin.getCommand("farmwelt").setExecutor(new CMDFarmwelt());
        JomluaCore.plugin.getCommand("warp").setExecutor(new CMDWarp(JomluaCore.plugin));
        JomluaCore.plugin.getCommand("setwarp").setExecutor(new CMDWarp(JomluaCore.plugin));
        JomluaCore.plugin.getCommand("delwarp").setExecutor(new CMDWarp(JomluaCore.plugin));
        JomluaCore.plugin.getCommand("warps").setExecutor(new CMDWarp(JomluaCore.plugin));
        JomluaCore.plugin.getCommand("gm").setExecutor(new CMDGamemode());
        JomluaCore.plugin.getCommand("tp").setExecutor(new CMDTp());
        JomluaCore.plugin.getCommand("tphere").setExecutor(new CMDTphere());
        JomluaCore.plugin.getCommand("load").setExecutor(new CMDReload());
        command("adebug", new CMDDebug());
        command("sun", new CMDWhether());
        command("storm", new CMDWhether());
        command("iteminfo", new CMDIteminfo());
        command("pleace", new CMDPlaece());
        command("kick", new kick());
        command("kickall", new kick());
        command("ban", new ExecuteBan());
        command("tempban", new ExecuteBan());
        command("check", new ExecuteBan());
        command("unban", new ExecuteBan());
        command("createkit", new CMDKits());
        command("kit", new CMDKits());
        command("kits", new CMDKits());
        command("speed", new CMDSpeed());
        command("eheads", new CMDHead());
        command("day", new CMDTime());
        command("nigth" , new CMDTime());
        command("tpo",new CMDTp());
        command("killme",new CMDKillme());
        command("iname", new CmdItemUname());

        JomluaCore.plugin.getCommand("plugins").setExecutor(new CMDPlugins());
        JomluaCore.plugin.getCommand("god").setExecutor(new CMDGodmode());
        JomluaCore.plugin.getCommand("back").setExecutor(new CMDBack());
        JomluaCore.plugin.getCommand("onlinetime").setExecutor(new CMDOnlinetime());
        JomluaCore.plugin.getCommand("invsee").setExecutor(new CMDInvsee());
        JomluaCore.plugin.getCommand("sethome").setExecutor(new CMDSetHome(JomluaCore.plugin));
        JomluaCore.plugin.getCommand("home").setExecutor(new CMDHome(JomluaCore.plugin));
        JomluaCore.plugin.getCommand("homes").setExecutor(new CMDHome(JomluaCore.plugin));
        JomluaCore.plugin.getCommand("delhome").setExecutor(new CMDHome(JomluaCore.plugin));
        JomluaCore.plugin.getCommand("ahome").setExecutor(new AdminHome());
        JomluaCore.plugin.getCommand("maxworld").setExecutor(new CMDMaxWorld(JomluaCore.plugin));
        JomluaCore.plugin.getCommand("set").setExecutor(new CMDSetBlock());
        JomluaCore.plugin.getCommand("wand").setExecutor(new CMDWand());
        JomluaCore.plugin.getCommand("tpa").setExecutor(new tpa());
        JomluaCore.plugin.getCommand("tpaccept").setExecutor(new tpaacept());
        JomluaCore.plugin.getCommand("tpadeny").setExecutor(new tpadeny());
        JomluaCore.plugin.getCommand("tpahere").setExecutor(new tpahere());
        JomluaCore.plugin.getCommand("rtp").setExecutor(new CMDRandomTp());
        JomluaCore.plugin.getCommand("setwb").setExecutor(new CMDRandomTp());
        JomluaCore.plugin.getCommand("discord").setExecutor(new CMDDiscord());
        JomluaCore.plugin.getCommand("teamspeak").setExecutor(new CMDTeamSpeak());
        JomluaCore.plugin.getCommand("jcreload").setExecutor(new CMDPluginReload());
        JomluaCore.plugin.getCommand("endsee").setExecutor(new CMDEndersee());


          JomluaCore.plugin.getCommand("money").setExecutor(new CMDmoney());

        // Tab Completer -----------
        JomluaCore.plugin.getCommand("maxworld").setTabCompleter(new CMDMaxWorld(JomluaCore.getPlugin()));
        JomluaCore.plugin.getCommand("tempban").setTabCompleter(new ExecuteBan());
        JomluaCore.plugin.getCommand("warp").setTabCompleter(new CMDWarp(JomluaCore.getPlugin()));
        JomluaCore.plugin.getCommand("set").setTabCompleter(new CMDSetBlock());
        JomluaCore.plugin.getCommand("home").setTabCompleter(new CMDHome(JomluaCore.getPlugin()));

    }

}
