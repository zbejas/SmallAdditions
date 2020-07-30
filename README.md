
SmallAdditions
=== 
Description
---
SmallAdditions is a plugin, that adds a few features to vanilla Minecraft. 

Its features include: 
* Crop harvest with right-click
* Crafting to-go
* Prevent crop trample
* Handy crafting recipes
* Leashing villagers
* Villager inventory and xp drops
* Auto-feed
* Auto armor switch
* Armor switch with right-click
* Infinite torches
* Totem usage from inventory
* Mining spawners
* Block creeper destruction

Stats: [bStats](https://bstats.org/plugin/bukkit/SmallAdditions/6335)

Usage
---
You can enable and disable all functions in the config file.

Command | Aliases | Usage | Premission
--- | --- | --- | ----
/autoarmor | /aa | Player enables AutoArmor | smalladd.tool.armor
/autofeed `<Food option>` | /af | Player selects food that is then fed to the player. | smalladd.autofeed
/portableworkbench | /pwb | Sets item name to 'Portable Workbench'. | smalladd.tool.workbench
/infinitetorch | /it | Creates an infinite torch | smalladd.tool.torch
/sadeath | /sad | Gives the player a Death Note | smalladd.deathbook
 /saupdate | /sau | Check for updates. | smalladd.admin

How it works
---
#### Crop harvest
By giving a player or group the permission _smalladd.crop.*_ (or any of the separate crops permissions), the player can right-click harvest crops. The seeds will be auto-replanted. If the drops contain the seeds, the seed will be taken out of the drop and if the drops don't contain it, then the seed will be taken out of the players' inventory. If the players' inventory doesn't contain any seeds, the crop won't be replanted.

![Gif broken](https://i.imgur.com/V4l8zRK.gif)

---

#### Custom recipes
SmallAdditions has a handful of different recipes to improve your time playing.

_**Chest from logs**_
You can craft 4 chests from any kind of log.

![Image broken](https://i.imgur.com/Y0PiypX.png)

_**Melons to slices**_
You can now cut up your own melon! No more block placing and breaking to turn melons into melon slices.

![Image broken](https://i.imgur.com/nIBdTKz.png)

_**Dying carpets**_
You can now dye 8 carpets with a single dye.

![Gif broken](https://i.imgur.com/I2jkEPe.gif)

---

#### No crop trample
By giving a player or group the permission _smalladd.notrample_, the player or group can't trample any of the crops.

![Gif broken](https://camo.githubusercontent.com/373a62f1d39621043b85dda117a7cf40335f1f51/68747470733a2f2f692e696d6775722e636f6d2f494c37614a736a2e676966)

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

![Gif broken](https://i.imgur.com/ox8l48D.gif)

---

#### Infinite torch
By giving a player or group the permission _smalladd.tool.torch_, the player can use the _/infinitetorch_ (or _/it_) to create an infinite torch. 

WARNING: Broken torches still drop items.

![Gif broken](https://i.imgur.com/J1CUygz.gif)

---

#### Mine spawners
By giving a player or group the permission _smalladd.spawner_, the player can mine spawners with a silk-touch pickaxe.

**BROKEN IN 1.16, WILL BE FIXED**

---

#### Improved hoes
By giving a player or group the permission _smalladd.tool.hoe_, they can use improved hoes. iron and gold hoes dig 2x2, while diamond hoe can dig 3x3.

![Gif broken](https://i.imgur.com/TImoa0o.gif)

---

#### Workbench
By giving a player or group the permission _smalladd.tool.workbech_, the player can use the _/portableworkbench_ (or _/pwb_) to rename the workbench in hand into "Portable Workbench". When the player right-clicks on that workbench, crafting menu opens.

![Gif broken](https://i.imgur.com/GGpMHbm.gif)
---

#### Death book
On death, players with permissions will recieve a book with their death coordinates. Players with teleport permission can also click on those coordinates to be teleported there.

![Image broken](https://i.imgur.com/n0zrXYN.png)

---

#### Auto-Feed
You will have to try it for yourself! Try using `/af <food option>`, for example `/af BAKED_POTATO` and watch the magic happen. Handy when busy isn't it?

---

#### Auto-Armor
Try `/aa`. When you pick up armor that has higher priority (set in config) from the one you are wearing, they get switched automatically.

---

#### Armor Switch
You can right-click to equip armor. You can't right-click to switch that equipped armor. **NO LONGER!**
 
---

#### Creepers
Creeper griefing is annoying...If not you can just disable it.

---

Config
---
I try to make everything customizable. Don't like some additions? Disable it all in the config.

Vague description:
![Image broken](https://i.imgur.com/enyin79.png) 

---


Languages
---
You can modify lang.yml. This will be improved in the future.


Permissions
---
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

Support the project
===
If you like this project, and wish to see frequent updates, support this project.
<a href="https://paypal.me/zbe420?locale.x=en_US"><img style="width: 30%; height: 30%;" src="https://raw.githubusercontent.com/stefan-niedermann/paypal-donate-button/master/paypal-donate-button.png?fbclid=IwAR1C58lEX29L-ZlY23vzQcaZBrJnihD9z1B075At7eNiBnaxzT4If08Wung"></img></a>
