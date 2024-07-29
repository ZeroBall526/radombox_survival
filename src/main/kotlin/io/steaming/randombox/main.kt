package io.steaming.randombox

import io.steaming.randombox.command.boxcommand
import io.steaming.randombox.command.toolboxcommand
import io.steaming.randombox.event.Event
import org.bukkit.plugin.java.JavaPlugin

class main : JavaPlugin() {
    //enable-function
    override fun onEnable() {
        // Plugin startup logic
        logger.info("RandomBox plugin is enabled!")
        //register event class
        server.pluginManager.registerEvents(Event(), this@main)

        //register boxcommand
        initCommand()
    }

    //disable-function
    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("RandomBox plugin is disabled!")
    }

    private fun initCommand(){
        //type you want to add boxcommand class
        getCommand("box")?.setExecutor(boxcommand())
        getCommand("toolbox")?.setExecutor(toolboxcommand())
    }
}