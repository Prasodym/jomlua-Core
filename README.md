# Jomlua Core Plugin

[![pipeline status](https://git.jomlua.de/Prasodym/jomluacore-paper/badges/master/pipeline.svg)](https://git.jomlua.de/Prasodym/jomluacore-paper/-/commits/master)[![Discord Chat](https://img.shields.io/discord/308323056592486420.svg)](https://discord.gg/SyQhwe2)

- [Beschreibung](#Bescreibung)
- [Befehle & Permissions](#befehle)

- [Configs](#configs)
	- [config.yml](#config)
	- [world.yml](#world)
	- [warps.yml](#warps)
	- [mysql.yml](#mysql)
	- [chat.yml](#chat)
# Beschreibung
IMG HERE


![logo.png](https://jomlua.de/image/gitlogo.png)


---
Dies ein Bukkit/Spigot Plugin was viele Standard Befehle beinheitet.
Eine abgespeckte version von Essentials, Es beinhaltet fast alles was für Täglichen gebrauch zu gebrauchen ist. Es beinheltet einen Blockcounter, Spielzeit und Spieler liste über Mysql-Datenbank.

- :white_check_mark: - Require Mysql, MariaDB 
***

Ein Chat API ist auch mit eingebaut, dafür werden die unten aufgelitende Plugins benötigt.
Im JoinListener wurde die Join Nachricht geänder wie auf den unten zu sehenen Bild
Die World Prefix kann über der config ausgeschalten werden



![2020-12-27_15.41.27.png](https://jomlua.de/image/img1.png)


- :white_check_mark: - Require Bukkit Vaul Plugin
- :white_check_mark: - Require Bukkit Luckperms

Im momment ist das Plugin nur auf Deutsch zu bekommen. Mehr dazu [Chatausgabe ändern](#chat).

# Befehle & Permissions
|Befehl|Alias/Abküzung|Permissions|Beschribung||
|---|---|---|---|---|
|/back|null|`jomlua.back`|Teleportiere dich zum Todespunkt zurück.|X|
|/discord|/dc|null|Zeigt die Discord Adresse an|X|
|/endsee|/es|``jomlua.endsee``|Schaue in deiner Endertruhe|Y|
|/farmwelt|null|`jomlua.farmwelt`|Teleportiert die zu Farmwelt|Z|
|/fly|null|`jomlua.fly`|Aktiviert Creativ Flug|Y|
|/gamemode|/gm|`jomlua.gamemode`|Wechsel dein Spielmodus|Y|
|/godmode|/god|`gomlua.godmode`|Werde unverwundbar|Y|
|/heal|null|`jomlua.heal`|Heilt dich|Y|
|/home|null|`jomlua.home`|Teleportiert dich zum Homepunkt|X|
|/sethome|null|`jomlua.home`|Setz ein neuen Homepunkt|X|
|/delhome|null|`jomlua.home`|Löscht ein Homepunkt|X|
|/homes|null|`jomlua.home`|zeigt alle Homepunkte|X|
|/invsee|null|`jomlua.invsee`|Ausspähen eines Spieler inventar|Y|
|/iteminfo|null|`jomlua.iteminfo`|Infos über das Item in der haltende Hand|X|
|/maxworld|/mw|`jomlua.mw.*`|Welten Tool|Y|
|/money|null|`jomlua.money`|WIP: Zeigt dir dein Kontstand|Y|
|/onlinetime|/ptime|`jomlua.playtime`|Zeigt dir deine Spielzeit|X|
|/jcreload|null|jomlua.reload.plugin|Aktualisiert die Configs|Z|
|/load|none|`jomlua.plugin.load`|Server reload ohne Confirm|X|
|/randomtp|/rtp|`jomlua.rtp`|Teleportiert dich zufällig in der Welt|Y|
|/setfarmwelt|none|`jomlua.setfarmwelt`|Setze ein Teleportpunkt für /farmwelt|X|
|/setspawn|null|`jomlua.sethome`|Setze ein Global Spawnpunkt|X|
|/spawn|null|`jomlua.spawn`|Teleportiere dich zum Spawn oder Lobby|X,Y|
|/teamspeak|/ts|null|Zeigt dir die Teamspeak Adresse|X|
|/msg|/m|null|WIP: Chatte privat mit ein Spieler|Y|
|/tp|null|`jomlua.tp`|Teleportiere dich tum Spieler|X|
|/tphere|null|`jomlua.tphere`|Ein Spieler zu dir teleportieren|X|
|/tpa|null|`jomlua.tpa`|Sende eine Teleport anfrage|X|
|/tpahere|null|`jomlua.tpahere`|Sende eine Teleport anfrage zu dir|X|
|/tpadeny|null|`jomlua.tpahere`|Lehne die Teleport anfrage ab|X|
|/tpaccept|null|`jomlua.tpacept`|Bestätige die Teleport anfrage|X|
|/warp|null|`jomlua.warp`|Teleportiert dich zum bestimmten punkt/ -> /Setwarp|X|
|/warps|null|`jomlua.warps`|Listet alle warps auf|X|
|/setwarp|null|`jomlua.setwarp`|Setze einen neuen Teleport punkt / -> /warp|X|
|/delwarp|none|`jomlua.delwarp`|Lösche einen warp punkt|X|



# configs
## - config

Hierzu sehen ist die `config.yml`, Die liegt im, JomluaCore Ordner.

```yml
# 
# Plugin Jomlua Core Settings #
# Ist nicht mehr funktionsfähig, Siehe Chat.yml
# Dies kann auch auskommentiert werden.
# wie unten zu sehen ist
################################################
# plugin:
#   Prefix: '§7[§3Jo§amlua§7] '
#
Plugin:
  Prefix: '§7[§3Jo§amlua§7] '
################################################
# Position des /setspawn befehls
Spawn:
  World: world
  X: 0
  Y: 64.0
  Z: 0
  Yaw: 0
  Pitch: 0
# Position des /setfarmwelt befehls
Farmwelt:
  World: world
  X: 0
  Y: 64.0
  Z: 0
  Yaw: 0
  Pitch: 0
# Eine unsichbare grenze für den RTP befehl
world-border: true
# default 200, das ist das Radius für den /rtp befehl
border: 200
# Machimale homes was spieler setzen dürfen außer ein Spieler oder Gruppe besitzt die Permissions: jomlua.homes.multipl
homepoints: 3
# Bitte nicht Löschen!
test: 80
```
---
## - world
Eine konfig womit eingesehen kann welche Welt hinzugefügt wurde mit `/mw create <Worldtype> <Worldname>` 

```yml
Worlds:
- Test
- Flatworld

```
---
## - warps

Diese Datei kan quasi unberührt bleiben. Dies Speichert nur die Warp punkte mit `/setwarp <name>`,

```yml
# Um ein Warpname zu ändern, könnte event: geändert werden zb. zu event1:
event:
  world: world
  x: 212.37079975162433
  y: 64.0
  z: -35.186621580120466
  yaw: -221.1334228515625
  pitch: 27.449403762817383
```
---
## - mysql
Damit das Plugin läuft muss unbeding die Mysql config behandelt werden.

```yml
Mysql:
  host: localhost
  port: '3306'
  database: mc
  user: root
  password: ''
  connectet-pool-timeout: 20
```
---
## - chat
Jeder Befehl der eingegben wir löst auch eine Textausgabe aus. Die könnte hier in der Datei belibig geändert werden.

`%target%`    = Der angepingte Spieler.
`%player%`    = Der Spieler Selber.
`%amont%`     = Kontostand des Spielers
`%acc%`       = Kontoname des Spielers.
`%n`	   	  = Zeile unterbrechen / Sprich Enter.
`%no_player%` = Der nicht zu erreichbare Spieler

```yml
prefix: '&7[&3Jo&amlua&7] '
error:
  permission:
    denid: '&cYou have no permission to do this.'
  noplayer:
    exist: '&cDieser Spieler existiert nicht.'
    online: '&cDer Spieler &e%no_player% &cist derzeit nicht online.'
msg:
  heal:
    me: '&aDu hast &c%target% &ageheilt.'
    to: '&aDu wurdes von &c%player% &ageheilt.'
    forme: §aDu hast §3§l%target%§a geheilt.
  online: '&D'
  tpa:
    meblock: '&cDu hast die TPA anfrage geblockt'
    toblock: '&c%target% &e hat deine TPA anfrage abgelehnt.'
    sendto: '&c%target% &eHat sich zu dir Teleportiert.'
    notparequest: '&cDu hast keine offene Tpa anfragen.'
    totparequest: '&c%player% &aMöchte sich zu dir Teleportieren'
    totpamessage: '&aGebe &c/tpaacept &aein um die anfrage anzunehmen.'
    tpasuccessfullmsg: '&2Deine Anfrage wurde verschickt.'
    noplayer: '&cDer spieler &a%target% &cist gerade nicht online.'
    tpadeny: '&aWenn du diese anfrage ablehnen möchtest gebe &c/tpadeny &a ein.'
    tptome: '&c%player% &aMöchte das du dich zu ihn Teleportierst'
  trp:
    nonether: '&cDu kannst in der Netherwelt diesen befehl nutzen'
    nether: '&aTeleportiere...'
eco:
  prefix: '&8[&3E&aco&8] '
  depositeplayer: '&2Du hast &c%amont% &2auf das konto &c%acc% &2überwieden.'
  balance: '&2%player%´s aktueller Kontostand beträgt:'
  balance1: '&8Summe: &a'
discord: '&7Besuche unseren Discord Server:%n &8-> &a&lhttps://discord.gg/SyQhwe2'
prefixc: '&8[&3Jomlua&8] '
problem:
  danger: '&e[&4&lHEIGHT&e] &c'
  warning: '&e[&e&lWARNING&e] &c'
teamspeak: '&7Besuche unseren TeamSpeak Server:%n &9&l-> &a&lts.jomlua.de'
```
---
