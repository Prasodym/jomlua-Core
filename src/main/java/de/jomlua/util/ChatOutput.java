package de.jomlua.util;

import java.util.HashMap;

public enum ChatOutput {
    PREFIX("Prefix", "&8[&3Jo&amlua&8] "),
    PREFIXC("PrefixConsole", "&8[&3Jomlua&8] "),
    PREFIXECO("PrefixEco", "&7[Jomlua &aE&3co&7] &8&l| "),
    DANGER("Danger", "&e[&4&lHEIGHT&e] &c"),
    WARNING("Warning", "&e[&e&lWARNING&e] &c"),
    ECO_PREFIX("EcoPrefix", "&8[&3E&aco&8] "),
    TAB_HEADER_MSG("Footer", "&3Willkommen auf &aJomlua.de!"),
    TAB_FOOTER_MSG("FooterTablist",""),
    NO_PERMISSIONS("PermissionDenid", "&cYou have no permission to do this."),
    NO_PLAYER("DoPlayerExist", "&cDieser Spieler existiert nicht."),
    NO_PLAYER_ONLINE("NoPlayerOnline", "&cDer Spieler &e%np_player% &cist derzeit nicht online."),
    HEAL_ME("HealMe", "&aDu hast &c%target% &ageheilt."),
    HEAL_TO("HealTo", "&aDu wurdes von &c%player% &ageheilt."),
    HEAL_FOR_ME("HealMeText", "§aDu hast §3§l%target%§a geheilt."),
    TPA_DENY_A("TpaDenidTarget", "&cDu hast die TPA anfrage geblockt"),
    TPA_DENY_B("TpaDenidPlayer", "&c%target% &e hat deine TPA anfrage abgelehnt."),
    TPA_DENY_C("TpaTo", "&c%target% &eHat sich zu dir Teleportiert."),
    TPA_DENY_D("TpaNoRequest", "&cDu hast keine offene Tpa anfragen."),
    TPA_DENY_E("TpaRequest", "&7Du hast eine austehende Tpa anfrage von &c%player%"),
    TPA_DENY_G("TpaSendRequest", "&2Deine Anfrage wurde verschickt."),
    TPA_DENY_H("TpaPlayerNotOnline", "&cDer spieler &a%target% &cist gerade nicht online."),
    TPA_DENY_I("TpaNoRequest1","&cDu hast keine offene anfragen"),
    TPA_DENY_J("TpaTargetToMe", "&c%player% &7Möchte das du dich zu ihn Teleportierst"),
    ECO_DEPOSIT("EcoPayToTarget","&2Du hast &c%amont% &2auf das konto &c%acc% &2überwiesen."),
    ECO_AMOUNT("EcoBalace", "&2%player%´s &faktueller Kontostand beträgt:"),
    ECO_AMOUNT1("EcoBalace1", "&8Summe: &a{0} &7Blocks"),
    RANDOM_TP("RandomTpDenid", "&cIn der Netherwelt kannst du nicht Random teleportieren."),
    RANDOM_TP_TRUE("RandomTpAllow", "&aTeleportiere..."),
    ONLINE_OTHER_ERR("Online","&D"),
    JOIN_MSG("JoinMessages","&7[&a+&7] %player%"),
    QUIT_MSG("QuitMessages","&7[&c-&7] %player%"),
    DISCORD("Discord", "&7Besuche unseren Discord Server:%n &8-> &a&lhttps://discord.gg/SyQhwe2"),
    TEAMSPEAK("Teamspeak","&7Besuche unseren TeamSpeak Server:%n &9&l-> &a&lts.jomlua.de"),
    NIGHT("Night" , "&aDie zeit wurde auf &cNacht &agestellt."),
    DAY("Day","&aDie zeit wurde auf &cTag &agestellt."),
    SAVEKIT("SaveKit", "&aDu hast das Kit &c{0} &agespeichert"),
    SAVEKITEXIST("KitAlreadyExist", "&cDas Kit &e{0} &cgibt es schon."),
    GETKIT("GiveKit", "&eDu hast ein Kit &c{0} &ebekommen."),
    GETKITUSE("GetKitAlreadyUse", "&cDu hast bereits diesen Kit benutzt.");

    private String text;
    private String configName;

    ChatOutput(String configName, String text){
        this.configName = configName;
        this.text = text;
    }

    public String getConfigName(){
        return this.configName;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public String getText(HashMap<String, String> replacements){
        String replText = this.text;
        for (String key : replacements.keySet()){
            replText = replText.replace(key, replacements.get(key));
        }
        return replText;
    }
    public  static ChatOutput getConfig(){
        for (ChatOutput b : ChatOutput.values()){
            if (b.getConfigName().equalsIgnoreCase(b.configName)){
                return b;
            }
        }
        return null;
    }


}
