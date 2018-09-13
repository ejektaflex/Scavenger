package com.ejektaflex.scavenging

import com.ejektaflex.scavenging.proxy.IProxy
import net.minecraft.init.Blocks
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@Mod(modid = Scavenging.MODID, name = Scavenging.NAME, version = Scavenging.VERSION, modLanguageAdapter = Scavenging.ADAPTER)
object Scavenging : IProxy {

    @SidedProxy(clientSide = CLIENT, serverSide = SERVER)
    @JvmStatic lateinit var proxy: IProxy

    lateinit var logger: Logger

    @EventHandler
    override fun preInit(e: FMLPreInitializationEvent) {
        logger = e.modLog

        proxy.preInit(e)

        MinecraftForge.EVENT_BUS.register(proxy)
    }

    @EventHandler
    override fun init(e: FMLInitializationEvent) {
        proxy.init(e)
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.registryName)
    }



    override fun postInit(e: FMLPostInitializationEvent) {
        proxy.postInit(e)
    }

    const val ADAPTER = "net.shadowfacts.forgelin.KotlinAdapter"
    const val MODID = "scavenging"
    const val NAME = "Scavenging"
    const val VERSION = "1.0"

    private const val CLIENT = "com.ejektaflex.scavenging.proxy.ClientProxy"
    private const val SERVER = "com.ejektaflex.scavenging.proxy.CommonProxy"

}
