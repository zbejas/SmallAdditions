# SmallAdditions
 
## Description
SmallAdditions is a plugin, that adds a few features to vanilla Minecraft.

## Usage
You can enable and disable all functions in the config file.

Command | Aliases | Usage | Premission
--- | --- | --- | ----
/autofeed <Food option> | /af | Player selects food that is then fed to the player. | smalladd.autofeed
/portableworkbench | /pwb | Sets item name to 'Portable Workbench'. | smalladd.workbench
 /saupdate | /sau | Check for updates. | smalladd.admin

## How it works

#### No crop trample
By giving a player or group the permission _smalladd.notrample_, the player or group can't trample any of the crops.

![Gif broken (click)](https://imgur.com/tBWydPo.gif)
---

#### Crop harvest
By giving a player or group the permission _smalladd.crop.*_ (or any of the separate crops permissions), the player can right-click harvest crops. The seeds will be auto-replanted. If the drops contain the seeds, the seed will be taken out of the drop and if the drops dont contain it, then the seed will be taken out of the players inventory. If players inventory doesn't contain any seeds, the crop wont be replanted.

![Gif broken (click)](https://imgur.com/Fo5feLo.gif)
---

#### Workbench
By giving a player or group the permission _smalladd.workbech_, the player can use the _/portableworkbench_ (or _/pwb_) to rename the workbench in hand into "Portable Workbench". When the player right-click on that workbench, crafting menu opens.

![Gif broken (click)](https://imgur.com/igXaqTB.gif)


## Permissions

Permissions | Usage
--- | ---
smalladd.*| All permissions for this plugin.
smalladd.admin | Currently only for update check.
smalladd.autofeed | Enables auto-feed option
smalladd.crop.* | Enables harvest function for all crops
smalladd.crop.beetroot | Enables beetroot harvest.
smalladd.crop.carrot | Enables carrot harvest.
smalladd.crop.potato | Enables potato harvest.
smalladd.crop.netherwart | Enables nether wart harvest.
smalladd.crop.wheat | Enables wheat harvest.
smalladd.notrample | Player with permission cant trapmle crops.
smalladd.workbench | Enables portable workbench command and usage.

## Support the project
If you like this project, and wish to see frequent updates, support this project.
<a href="https://paypal.me/zbe420?locale.x=en_US"><img style="width: 30%; height: 30%;" src="https://raw.githubusercontent.com/stefan-niedermann/paypal-donate-button/master/paypal-donate-button.png?fbclid=IwAR1C58lEX29L-ZlY23vzQcaZBrJnihD9z1B075At7eNiBnaxzT4If08Wung"></img></a>
