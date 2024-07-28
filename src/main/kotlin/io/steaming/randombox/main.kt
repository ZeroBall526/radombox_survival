package io.steaming.randombox

import io.steaming.randombox.command.box
import io.steaming.randombox.command.toolbox
import io.steaming.randombox.event.Event
import org.bukkit.plugin.java.JavaPlugin

class main : JavaPlugin() {
    //enable-function
    override fun onEnable() {
        // Plugin startup logic
        logger.info("RandomBox plugin is enabled!")
        //register event class
        server.pluginManager.registerEvents(Event(), this@main)

        //register box
        initCommand()
    }

    //disable-function
    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("RandomBox plugin is disabled!")
    }

    private fun initCommand(){
        //type you want to add box class
        getCommand("box")?.setExecutor(box())
        getCommand("toolbox")?.setExecutor(toolbox())
    }
}