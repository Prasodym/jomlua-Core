package de.jomlua.mysql;

import de.jomlua.util.ChatOutput;
import de.jomlua.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class SaveSQL {

    private static int i = 1;

    public SaveSQL(){

    }

    public static void savePlayTime(String uuid, long time){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("UPDATE users SET playtime = ? WHERE uuid = ?");
                pdo.setLong(1, time);
                pdo.setString(2, uuid);
                pdo.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public static void LastPlaySaved(String uuid){
        Date date = new Date();
        long time = date.getTime();
        Timestamp test = new Timestamp(time);
        long after = test.getTime();

        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("UPDATE users SET lastplay= ? WHERE uuid = ?");
            pdo.setLong(1,after );
            pdo.setString(2, uuid);
            pdo.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Deathcounter ever 1 up
     *
     * @param uuid User uuid from Players
     * @throws SQLException Messages from sqlfails
     */
    public static void saveDeaths( String uuid) throws SQLException {
        PreparedStatement pdo = MySQL.getConnection().prepareStatement("UPDATE users SET deaths=? WHERE uuid=?");
        try{
            pdo.setInt(1, i);
            pdo.setString(2,uuid);
            pdo.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        i++;
    }


    /**
     * Pay money on target player.
     *
     * @param owner Bank owner/ frombanks
     * @param target Bank target/ to banks
     * @param money Amount of money
     */
    public static void setPay(Player owner, Player target, double money){

        long Mown = ReadSQL.getMoney(owner.getUniqueId().toString());
        long Mtar = ReadSQL.getMoney(target.getUniqueId().toString());

        final java.text.DecimalFormat twoDigits = new java.text.DecimalFormat( "##,###.##" );

       if(!(Mown < money)){
           try {
               PreparedStatement pdo = MySQL.getConnection().prepareStatement("UPDATE users SET money = ? WHERE uuid = ?");
               pdo.setLong(1, (long) (Mown-money));
               pdo.setString(2, owner.getUniqueId().toString());
               pdo.executeUpdate();

               PreparedStatement pdos = MySQL.getConnection().prepareStatement("UPDATE users SET money = ? WHERE uuid = ?");
               pdos.setLong(1, (long) (Mtar+money));
               pdos.setString(2, target.getUniqueId().toString());
               pdos.executeUpdate();
               ChatUtils.sendMessageAtHex(target, ChatOutput.PREFIX.getText() +"&fDu hast von &a" + owner.getName() + "&f, &c" + twoDigits.format(money) + " &fBlocks bekommen.");
               ChatUtils.sendMessageAtHex(owner, ChatOutput.PREFIX.getText() +"&fDu Hast §c" + target.getName() + "§f, §a" + twoDigits.format(money) +" Blocks §fgesendet.");
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }else{

           owner.sendMessage(ChatOutput.PREFIX.getText() + "§cDu hast nicht genug Geld.");
       }
    }

    public static void setPay(Player owner, double money){

        long Mown = ReadSQL.getMoney(owner.getUniqueId().toString());


        final java.text.DecimalFormat twoDigits = new java.text.DecimalFormat( "##,###.##" );

        if(!(Mown < money)){
            try {
                PreparedStatement pdo = MySQL.getConnection().prepareStatement("UPDATE users SET money = ? WHERE uuid = ?");
                pdo.setLong(1, (long) (Mown-money));
                pdo.setString(2, owner.getUniqueId().toString());
                pdo.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{

            owner.sendMessage(ChatOutput.PREFIX.getText() + "§cDu hast nicht genug Geld.");
        }
    }


    public static void addPay(Player owner, OfflinePlayer target, double money){

        long Mown = ReadSQL.getMoney(owner.getUniqueId().toString());


        final java.text.DecimalFormat twoDigits = new java.text.DecimalFormat( "##,###.##" );


            try {
                PreparedStatement pdo = MySQL.getConnection().prepareStatement("UPDATE users SET money = ? WHERE uuid = ?");
                pdo.setLong(1, (long) (Mown+money));
                pdo.setString(2, owner.getUniqueId().toString());
                pdo.executeUpdate();
                try {
                    owner.sendMessage(ChatOutput.PREFIXECO.getText() + ChatUtils.setHexColor("§fDu hast §c" + target.getName() + "&f gerade #954141" + twoDigits.format(money) + " &fgegeben."));
                }catch (NullPointerException e){
                    Bukkit.getLogger().info(owner.getName() + "hat gerade " + target + money + "Blocks gegeben.");

                    PreparedStatement pdoe = MySQL.getConnection().prepareStatement("UPDATE users SET money = ? WHERE uuid = ?");
                    pdoe.setLong(1, (long) (Mown+money));
                    pdoe.setString(2, owner.getUniqueId().toString());
                    pdoe.executeUpdate();
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public static void addPay(Player owner,double money){

        long Mown = ReadSQL.getMoney(owner.getUniqueId().toString());


        final java.text.DecimalFormat twoDigits = new java.text.DecimalFormat( "##,###.##" );


        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("UPDATE users SET money = ? WHERE uuid = ?");
            pdo.setLong(1, (long) (Mown+money));
            pdo.setString(2, owner.getUniqueId().toString());
            pdo.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
