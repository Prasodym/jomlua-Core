package de.jomlua.util;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.jomlua.JomluaCore.reply;

public class ChatUtils {

    public ChatUtils(){

    }

    /**
     *
     * @param text Text or word which is displayed in the chat
     * @param command Command that will be executed when clicked
     * @param hoverText Infotext when hovering the mouse over it
     * @return Chat output sin clickable
     */
    public static TextComponent ChatCommand(String text, String command, String hoverText){
        TextComponent warps = new TextComponent();
        warps.setText(text);
        warps.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(Color(hoverText))));
        warps.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,command));
        return warps;
    }

    /**
     *
     * @param text Text or word which is displayed in the chat
     * @param address destination address
     * @param hoverText Infotext when hovering the mouse over it
     * @return Chat output, when you click you will be redirected to the address
     */
    public static TextComponent ChatLink(String text, String address, String hoverText){
        TextComponent warps = new TextComponent();
        warps.setText(text);
        warps.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(Color(hoverText))));
        warps.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, address));
        return warps;
    }

    public static TextComponent ChatHover(String text,String hovertext){
        TextComponent satz = new TextComponent();
        satz.setText(text);
        satz.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(Color(hovertext))));
        return satz;
    }

    public static TextComponent ChatText(String text){
        TextComponent worldliste = new TextComponent();
        worldliste.setText(Color(text));
        return worldliste;
    }

    public static void ConsolText(String text){

        switch (text){
            case "&0":
                ChatColor.BLACK.isColor();
                break;
            case "&1":
                ChatColor.DARK_BLUE.isColor();
                break;
            case "&2":
                ChatColor.DARK_GREEN.isColor();
                break;
            case "&3":
                ChatColor.DARK_AQUA.isColor();
                break;
            case "&4":
                ChatColor.DARK_RED.isColor();
                break;
            case "&5":
                ChatColor.DARK_PURPLE.isColor();
                break;
            case "&6":
                ChatColor.GOLD.isColor();
                break;
            case "&7":
                ChatColor.GRAY.isColor();
                break;
            case "&8":
                ChatColor.DARK_GRAY.isColor();
                break;
            case "&9":
                ChatColor.BLUE.isColor();
                break;
            case "&a":
                text.replace("&a", (CharSequence) ChatColor.GREEN);
                //ChatColor.GREEN.isColor();
                break;
            case "&b":
                ChatColor.AQUA.isColor();
                break;
            case "&c":
                ChatColor.RED.isColor();
                break;
            case "&d":
                ChatColor.LIGHT_PURPLE.isColor();
                break;
            case "&e":
                ChatColor.YELLOW.isColor();
                break;
            case "&f":
                ChatColor.WHITE.isColor();
                break;
            case "&l":
                ChatColor.BOLD.isColor();
                break;
            case "&n":
                ChatColor.UNDERLINE.isColor();
                break;
            case "&i":
                ChatColor.ITALIC.isColor();
                break;
            case "&m":
                ChatColor.STRIKETHROUGH.isColor();
                break;
            case "&k":
                ChatColor.MAGIC.isColor();
                break;
            default:
                ChatColor.WHITE.isColor();
                break;
        }
        Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + text);
    }
    private static String Color(String text){
       return ChatColor.translateAlternateColorCodes('&',text);
    }


    public static String setHexColor(String msg){
        // ChatUtils.setHexColor("")
        Pattern pattern = Pattern.compile("#[a-fa-f0-9]{6}");

        Matcher match = pattern.matcher(msg);
        while (match.find()){
            String color = msg.substring(match.start(), match.end());
            msg = msg.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
            match = pattern.matcher(msg);
        }
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&',msg);
    }



    public static void sendMessageAtHex(Player player, String msg){
        player.sendMessage(setHexColor(msg));
    }

    public static String getOnlinePlayer(){
        int a = Bukkit.getOnlinePlayers().size();
        int b = Bukkit.getMaxPlayers();

        return "§a" + a + " §7von§c " + b;
    }

    public static String getReadableTime(long timeInSec) {
        long weeks = timeInSec / 604800;
        long wRemainder = timeInSec % 604800;
        long days = wRemainder / 86400;
        long dRemainder = wRemainder % 86400;
        long hours = dRemainder / 3600;
        long hRemainder = dRemainder % 3600;
        long minutes = hRemainder / 60;
        long seconds = hRemainder % 60;

        StringBuilder stringBuilder = new StringBuilder();

        if(weeks > 0){
            stringBuilder.append(weeks);

            if(weeks == 1){
                stringBuilder.append(" Week ");
            } else {
                stringBuilder.append(" Weeks ");
            }
        }
        if(days > 0){
            stringBuilder.append(days);

            if(days == 1){
                stringBuilder.append(" Day ");
            } else {
                stringBuilder.append(" Days ");
            }
        }
        if((hours > 0)){
            stringBuilder.append(hours);

            if(hours == 1){
                stringBuilder.append(" Hour ");
            } else {
                stringBuilder.append(" Hours ");
            }
        }
        if(minutes > 0){
            stringBuilder.append(minutes);

            if(minutes == 1){
                stringBuilder.append(" Minute ");
            } else {
                stringBuilder.append(" Minutes ");
            }
        }
        if(seconds > 0){
            stringBuilder.append(seconds);

            if(seconds == 1){
                stringBuilder.append(" Second ");
            } else {
                stringBuilder.append(" Seconds ");
            }
        }
        return stringBuilder.toString();
    }

    public static void setReply(Player player1, Player player2){
        reply.put(player1, player2);
        reply.put(player2, player1);
    }

    public static Player getReply(Player player1){
        return reply.get(player1);
    }


    /**
     * Ändert den Namen des Items
     * @param player
     * @param itemname
     */
    public static void RenameItemInHand(Player player, String itemname){

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack == null || itemStack.getType().equals(Material.AIR)){
            ChatUtils.sendMessageAtHex(player, "&cDu hast kein Item in deiner Hand.");
            return;
        }

        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(ChatUtils.setHexColor(itemname));
        itemStack.setItemMeta(im);
        player.getInventory().setItem(EquipmentSlot.HAND,itemStack);
    }


}
