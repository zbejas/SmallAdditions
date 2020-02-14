# SmallAdditions
 
## Description
SmallAdditions is a plugin, that adds a few features to vanilla Minecraft. 

Its features include: 
* Extra crafting recipes
* Crop harvest and replant
* Villager leash
* Villagers drop their inventory, XP and 0-2 emeralds
* Crafting to-go
* Auto-Feed
* Infinite torches
* Totem in inventory
* Improved hoes
* Prevents trampling
* Lets you mine spawners

Stats: [bStats](https://bstats.org/plugin/bukkit/SmallAdditions/6335)

## Usage
You can enable and disable all functions in the config file.

Command | Aliases | Usage | Premission
--- | --- | --- | ----
/autoarmor | /af | Player enables AutoArmor | smalladd.tool.armor
/autofeed <Food option> | /af | Player selects food that is then fed to the player. | smalladd.autofeed
/portableworkbench | /pwb | Sets item name to 'Portable Workbench'. | smalladd.tool.workbench
/infinitetorch | /it | Creates an infinite torch | smalladd.tool.torch
/sadeath | /sad | Gives the player a Death Note | smalladd.deathbook
 /saupdate | /sau | Check for updates. | smalladd.admin

## How it works

#### Crop harvest
By giving a player or group the permission _smalladd.crop.*_ (or any of the separate crops permissions), the player can right-click harvest crops. The seeds will be auto-replanted. If the drops contain the seeds, the seed will be taken out of the drop and if the drops don't contain it, then the seed will be taken out of the players' inventory. If the players' inventory doesn't contain any seeds, the crop won't be replanted.

_Crop harvest now has animations! New gif incoming._

![Gif broken](https://i.imgur.com/6PwLcbN.gif)

---

#### Custom recipe
Why craft planks and then chests, if you can craft chests directly from logs!

![Gif broken](https://i.imgur.com/Rqydxqb.gif)

---

#### No crop trample
By giving a player or group the permission _smalladd.notrample_, the player or group can't trample any of the crops.

![Gif broken](https://i.imgur.com/IL7aJsj.gif)

---

#### Villager drops
All villagers drop their inventory, their XP and 0-2 emeralds (In the future, I will make this more configurable).

![Gif broken](https://i.imgur.com/XzBcRDN.gif)

---

#### Villager leash
Players with _smalladd.villagerleash_ can leash villagers.

![Gif broken](https://i.imgur.com/v9GBAg6.gif)

---

#### Totem in inventory
Players with a totem in their inventory slot will still get totem effect.

![Gif broken](https://i.imgur.com/77MMzE5.gif)

---

#### Infinite torch
By giving a player or group the permission _smalladd.tool.torch_, the player can use the _/infinitetorch_ (or _/it_) to create an infinite torch. 

WARNING: Broken torches still drop items.

![Gif broken](https://i.imgur.com/spj9FjX.gif)

---

#### Mine spawners
By giving a player or group the permission _smalladd.spawner_, the player can mine spawners with a silk-touch pickaxe.

![Gif broken](https://i.imgur.com/ydBBpqA.gif)

---

#### Improved hoes
By giving a player or group the permission _smalladd.tool.hoe_, they can use improved hoes. iron and gold hoes dig 2x2, while diamond hoe can dig 3x3.

![Gif broken](https://i.imgur.com/TImoa0o.gif)

---

#### Workbench
By giving a player or group the permission _smalladd.tool.workbech_, the player can use the _/portableworkbench_ (or _/pwb_) to rename the workbench in hand into "Portable Workbench". When the player right-clicks on that workbench, crafting menu opens.

![Gif broken](https://i.imgur.com/T4KAM5P.gif)

---


## Permissions

Permissions | Usage
--- | ---
smalladd.* | All permissions for this plugin.
smalladd.admin | Currently only for update check.
smalladd.autofeed | Enables the auto-feed option
smalladd.crop.* | Enables harvest function for all crops
smalladd.crop.beetroot | Enables beetroot harvest.
smalladd.crop.carrot | Enables carrot harvest.
smalladd.crop.netherwart | Enables nether wart harvest.
smalladd.crop.potato | Enables potato harvest.
smalladd.crop.wheat | Enables wheat harvest.
smalladd.deathbook | Player gets a Death Note
smalladd.deathbook.teleport | Player can teleport using this book
smalladd.notrample | Player with permission cant trample crops.
smalladd.spawner | Lets players mine spawners.
smalladd.tool.* | Enables all tools for players.
smalladd.tool.armor | Enables armor switch on right-click and AutoArmor
smalladd.tool.hoe | Lets players use improved hoes.
smalladd.tool.torch | Enables infinite torch command and usage.
smalladd.tool.workbench | Enables portable workbench command and usage.
smalladd.villagerleash | Lets players leash villagers.

## Support the project
If you like this project, and wish to see frequent updates, support this project.
<a href="https://paypal.me/zbe420?locale.x=en_US"><img style="width: 30%; height: 30%;" src="https://raw.githubusercontent.com/stefan-niedermann/paypal-donate-button/master/paypal-donate-button.png?fbclid=IwAR1C58lEX29L-ZlY23vzQcaZBrJnihD9z1B075At7eNiBnaxzT4If08Wung"></img></a>
