<p align="center"><img src="src/main/resources/icon.png" width="128"></p>

# ways anchor optimiser

client side fabric mod for minecraft 1.21.11 that makes anchoring faster. it works while you hold a respawn anchor or glowstone.

- the use cooldown is lowered to a random number between your min and max ticks (1 and 2 by default)
- the attack cooldown is reset
- if you place while your crosshair is on a player or mob, it uses the block behind them (up to 4.5 blocks) so the anchor still gets placed or charged

## settings

open mod menu, find ways anchor optimiser and click config. there is an on switch and min and max ticks sliders from 0 to 4. careful, putting both at 0 can get you flagged by anticheats, and some servers ban stuff like this anyway.

## install

you need fabric loader, and mod menu with cloth config for the settings screen. put the jar from the releases page in your mods folder.

## build

`./gradlew build`, the jar ends up in build/libs.

cc0, do what you want with it.

all rights reserved. made by wysss.
