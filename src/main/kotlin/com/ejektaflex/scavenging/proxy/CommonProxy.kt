package com.ejektaflex.scavenging.proxy

import com.ejektaflex.scavenging.items.Scraper
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.event.RegistryEvent



open class CommonProxy : IProxy {

    override fun preInit(e: FMLPreInitializationEvent) {

    }

    override fun init(e: FMLInitializationEvent) {

    }

    override fun postInit(e: FMLPostInitializationEvent) {

    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        register(event.registry)
    }

    private fun register(registry: IForgeRegistry<Item>) {
        registry.register(Scraper())
    }

}