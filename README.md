# Create: Moo Drain

Cows standing on a Create Item Drain fill it with milk. Pipe it out with a mechanical pump like any other drain.

Target: Minecraft 1.21.1, NeoForge, Create 6.

## Building on GitHub (no local setup)
1. Push this folder as the root of a GitHub repo.
2. Open the repo's Actions tab: a "Build" run starts on every push (or press "Run workflow").
3. When it goes green, open the run and download the `create-moo-drain` artifact. It's a zip containing the mod jar.
4. If it goes red, the log usually names the missing dependency version; fix it in `gradle.properties` and push again.

## Building locally
Copy the Gradle wrapper (`gradlew`, `gradlew.bat`, `gradle/`) from the NeoForge 1.21.1 MDK into this folder, then `./gradlew runClient` to test or `./gradlew build` for the jar (in `build/libs`).

## Config (world/serverconfig/createmoodrain-server.toml)
- intervalTicks (40): ticks between milkings
- mbPerMilking (100): mB added per milking
- includeMooshrooms (true)
- playSound (true)

Babies don't produce milk. A drain already holding a different fluid won't accept milk.
