# SmallAdditions
 
## Description
SmallAdditions is a plugin, that adds a few features to vanilla Minecraft.

## Usage
You can enable and disable all functions in the config file.

Add the harvest permissions to a group/player (either .crops or separate) and right-click on a crop.

Currently, the only command is:

Command | Aliases | Usage
--- | --- | ---
/autofeed <Food option> | /af | Player selects food that is then fed to the player.

## How it works

### No crop trample
By giving a player or group the permission _smalladd.notrample_. When the player or group has this permission, they can't trample any of the crops.

---
![](https://i.imgur.com/qiCk9Ac.gif)

### Crop harvest
By giving a player or group the permission _smalladd.crops_ (or any of the separate crops permissions), the player can right-click harvest crops. The seeds will be auto-replanted. If the drops contains the seeds, the seed will be taken out of the drop and if the drops dont contain it, then the seed will be taken out of the players inventory. If players inventory doesn't contain any seeds, the crop wont be replanted.

---
![](https://i.imgur.com/XU3iQkw.gif)


## Permissions

Permissions | Usage
--- | ---
smalladd.*| All permissions for this plugin.
smalladd.notrample | Player with permission cant trapmle crops.
smalladd.autofeed | Enables auto-feed option
smalladd.crops | Enables harvest function for all crops
smalladd.wheat | Enables wheat harvest.
smalladd.potato | Enables potato harvest.
smalladd.carrot | Enables carrot harvest.
smalladd.beetroot | Enables beetroot harvest.
smalladd.netherwart | Enables nether wart harvest.

## Support the project
If you like this project, and wish to see frequent updates, support this project.
<a href="https://paypal.me/zbe420?locale.x=en_US"><img style="width: 30%; height: 30%;" src="https://raw.githubusercontent.com/stefan-niedermann/paypal-donate-button/master/paypal-donate-button.png?fbclid=IwAR1C58lEX29L-ZlY23vzQcaZBrJnihD9z1B075At7eNiBnaxzT4If08Wung"></img></a>
