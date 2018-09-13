package com.ejektaflex.scavenging.proxy

import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent


interface IProxy {
    fun preInit(e: FMLPreInitializationEvent)

    fun init(e: FMLInitializationEvent)

    fun postInit(e: FMLPostInitializationEvent)
}