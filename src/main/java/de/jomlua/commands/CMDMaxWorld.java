package de.jomlua.commands;

import de.jomlua.JomluaCore;
import de.jomlua.util.VoidGenerator;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.bukkit.WorldType.*;

public class CMDMaxWorld implements CommandExecutor, TabCompleter {
    public static final String mw = "§7[§aMW§7] ";
    private JomluaCore plugin;
    public CMDMaxWorld(JomluaCore plugin){
        this.plugin = plugin;
    }

    public static File file = new File("plugins/JomluaCore", "worlds.yml");
    public static YamlConfiguration cfg =YamlConfiguration.loadConfiguration(file);

    public static List<String> MAPS = cfg.getStringList("Worlds");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;


        if (args.length == 0) {
            player.sendMessage(mw + "§aBefehle die dir helfen könnten.");
            player.sendMessage("- §e/§bmw create §c<Normal, Flatworld ..>");
            player.sendMessage("- §e/§bmw tp §c<world>");
            player.sendMessage("- §e/§bmw delete §c<world>");
        }else if (args[0].equalsIgnoreCase("tp")){
            // /maxworld tp <name>
            if (player.hasPermission("jomlua.mw.tp")){
                if (args.length == 2) {
                    String world = args[1];
                    if (!(Bukkit.getServer().getWorld(world) == null)) {
                        player.sendMessage(mw + "§aTeleportiere zu §c" + world);
                        player.teleport(Bukkit.getWorld(world).getSpawnLocation());
                    } else {
                        player.sendMessage(mw + "§cDiese Welt existiert nicht!");
                   }
                }
            }

        } else if(args[0].equalsIgnoreCase("create")){
            if (player.hasPermission("jomlua.mw.crate")){
                String name = args[2];
                if (!MAPS.contains(name)) {
                    if (args[1].equalsIgnoreCase("normal")) {
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else if (args[1].equalsIgnoreCase("nether")) {
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        w.environment(World.Environment.NETHER);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else if (args[1].equalsIgnoreCase("end")) {

                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        w.environment(World.Environment.THE_END);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else if (args[1].equalsIgnoreCase("flat")) {
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        WorldType we = FLAT;
                        w.type(we);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else if (args[1].equalsIgnoreCase("large_biomes")) {
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        WorldType we = LARGE_BIOMES;
                        w.type(we);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");
                    } else if (args[1].equalsIgnoreCase("AMPLIFIED")) {
                        player.sendMessage(mw + "§3Die Welt " + name + " wird erstellt!");
                        WorldCreator w = WorldCreator.name(name);
                        WorldType we = AMPLIFIED;
                        w.type(we);
                        Bukkit.createWorld(w);
                        Bukkit.getWorlds().add(Bukkit.getWorld(name));
                        MAPS.add(name);
                        cfg.set("Worlds", MAPS);
                        player.sendMessage(mw + "§aDie Welt " + name + " wurde erfolgreich erstellt!");



                    }else{
                        player.sendMessage(mw + "- /World create NORMAL/WORLD_NETHER/END/FLATMAP/Large_Biomes/AMPLIFIED <Name>");
                        player.sendMessage(mw + "- /World tp <Name>");
                    }
                    try {
                        cfg.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    player.sendMessage(mw + "Diese Welt gibt es bereits");
                }
            }

        } else if (args[0].equalsIgnoreCase("delete")){
            if (player.hasPermission("jomlua.mw.delete")){
                if (args.length == 2){
                    String name = args[1];
                    if (name != cfg.getString("Worlds")){


                        player.teleport(Bukkit.getWorld("world").getSpawnLocation());
                        Bukkit.unloadWorld(name,false);
                        if (!Bukkit.unloadWorld(name,true)){
                            Objects.requireNonNull(Bukkit.getWorld(name)).getWorldFolder().delete();
                            cfg.getString(name,null);
                            player.sendMessage(mw + "§CDie welt wurde jetzt deaktiviert.");
                            player.sendMessage(mw + "§4coming Soon..");
                        } else {
                            player.sendMessage(mw + "§cEs gab ein unerwateter fehler beim löschen der Welt.");
                        }

                        Bukkit.unloadWorld(name, false);

                    }else{
                        player.sendMessage(mw + "§cDiese Weltr gibt es nicht.");
                    }

                }
            }

        } else if(args[0].equalsIgnoreCase("activ")) {
            if (player.hasPermission("jomlua.mw.active")) {
                if (args.length == 2) {
                    String name = args[1];
                    Bukkit.unloadWorld(name, true);
                    player.sendMessage(mw + "§CDie welt wurde jetzt aktiviert.");
                }
            }
        }else if(args[0].equalsIgnoreCase("info")) {
            String ani;
            String mons;
            if (player.getWorld().getAllowAnimals()){
                ani = "true";

            }else{
                 ani = "false";
            }
            if (player.getWorld().getAllowMonsters()){
                mons = "true";

            }else{
                mons = "false";
            }
        if (player.hasPermission("jomlua.mv.list")){
            player.sendMessage("§7 - [§a§l" + player.getWorld().getName() + "] §7-");
            player.sendMessage("§7--------------------");
            player.sendMessage("§7| Worldtyp: §c"  + player.getWorld().getEnvironment());
            player.sendMessage("§7| PvP: §c" + player.getWorld().getPVP());
            player.sendMessage("§7| Seed: §c" + player.getWorld().getSeed());
            player.sendMessage("§7| Spawn Tiere: §c" + ani);
            player.sendMessage("§7| Spawn Monster: §c" + mons);
            player.sendMessage("§7--------------------");
        }
        } else if (args[0].equalsIgnoreCase("list")){
            if (player.hasPermission("jomlua.mw.list")){
                if (args.length == 1){
                    String[] worldNames = new String[Bukkit.getServer().getWorlds().size()];
                    int count = 0;
                    for(World w : Bukkit.getServer().getWorlds()){
                        worldNames[count] = w.getName();
                        count++;
                    }

                    player.sendMessage(mw + "§3Alle aktiven Welten.");
                    int i = 1;
                    for(String list : worldNames){

                        TextComponent warps = new TextComponent();
                        warps.setText("§7[§2Teleport§7]");
                        warps.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§3Klicke um zu Teleportieren.")));
                        warps.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/mw tp " + list));

                        TextComponent delwarp = new TextComponent();
                        delwarp.setText("§7[§cLöschen§7]");
                        delwarp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§cKlicke um §a§l" + list.toString() + " §czu löschen.")));
                        delwarp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/mw delete " + list.toString()));


                        TextComponent worldliste = new TextComponent();
                        worldliste.setText( "§e§l" + i++ + " §a" + list + " §7>> ");

                        worldliste.addExtra(warps);
                        warps.addExtra(delwarp);

                        player.spigot().sendMessage(worldliste);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        //arr
        List<String> list = Arrays.asList("activ", "set", "list", "delete", "tp", "create", "info");
        List<String> listtype = Arrays.asList("NORMAL", "nether", "end", "flat");
        String[] worldNames = new String[Bukkit.getServer().getWorlds().size()];
        List<String> world = new ArrayList<>();


        // Var für /command <input>
        String input = args[0].toLowerCase();



        List<String> completion = null;

        for (String stra: list){
            if (stra.startsWith(input)){
                if (completion == null){
                    completion = new ArrayList<>();
                }
                completion.add(stra);
            }
        }
        for (String type: listtype){
            if (args[0].equalsIgnoreCase("create")){
                if (completion == null){
                    completion = new ArrayList<>();
                }
                completion.add(type);
            }
        }
        for (World worlds : Bukkit.getServer().getWorlds()){
            if (args[0].equalsIgnoreCase("tp")){
                if (completion == null){
                    completion = new ArrayList<>();
                }
                completion.remove("tp");
                completion.add(worlds.getName());

            }
        }

        if (completion != null){
            Collections.sort(completion);
        }

        return completion;
    }
}
