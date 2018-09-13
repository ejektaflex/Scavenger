package com.ejektaflex.scavenging.items

import com.ejektaflex.scavenging.Scavenging
import com.ejektaflex.scavenging.ext.*
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentString
import net.minecraft.world.World
import net.minecraftforge.oredict.OreDictionary

class Scraper : Item() {

    val name = "scraper"

    init {
        unlocalizedName = name
        registryName = ResourceLocation(Scavenging.MODID, name)
    }

    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {

        if (worldIn.isRemote) {
            return EnumActionResult.FAIL
        }

        val bs = worldIn[pos]

        val blockTags = getOreTags(bs.toItemStack).toSet()

        println("Block (${bs.block.localizedName}) has ore dict tags above ^")

        // All tags that are shared with the given block
        val matches = blockTags.intersect(keySet)
        if (matches.isNotEmpty()) {
            val nugTags = matches.mapNotNull { oreMap[it] }

            if (nugTags.isNotEmpty()) {
                println("Nug tags: $nugTags")
                val nugs = nugTags.map { OreDictionary.getOres(it) }.flatten()

                println("Nugitems: ${nugs.map { it.item.registryName }}")

                if (nugs.isNotEmpty()) {
                    println("Nugs: ${nugs.map { it.unlocalizedName }}")
                    player.addItemStackToInventory(nugs.first().copy())
                    worldIn[pos] = Blocks.STONE.defaultState
                }

            }

        }

        player.sendMessage(TextComponentString(worldIn[pos].pretty))

        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ)
    }

    companion object {
        val oreMap = mapOf(
                "oreCopper" to "nuggetCopper",
                "oreTin" to "nuggetTin",
                "oreIron" to "nuggetIron",
                "oreGold" to "nuggetGold",
                "oreCoal" to "coal",
                "oreLapis" to "gemLapis",
                "oreDiamond" to "gemDiamond",
                "oreEmerald" to "gemEmerald",
                "oreRedstone" to "dustRedstone"
        )


        val keySet: Set<String>
            get() = oreMap.keys.toSet()
    }

}

