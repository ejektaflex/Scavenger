package com.ejektaflex.scavenging.ext

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.oredict.OreDictionary

val IBlockState.pretty: String
    get() {
        var proto = block.registryName.toString()
        val meta = block.getMetaFromState(this)

        if (meta != 0) {
            proto += ":$meta"
        }

        return proto
    }

operator fun World.get(pos: BlockPos): IBlockState {
    return getBlockState(pos)
}

operator fun World.set(pos: BlockPos, block: IBlockState) {
    setBlockState(pos, block)
}

val String.toMeta: Int
    get() {
        return when {
            this == "*" -> OreDictionary.WILDCARD_VALUE
            else -> {
                try {
                    Integer.parseInt(this)
                } catch (e: Exception) {
                    e.printStackTrace()
                    0
                }
            }
        }
    }

val String.toItemStack: ItemStack?
    get() {
        val sect = split(":").toMutableList()
        if (sect.size !in 2..3) {
            return null
        } else if (sect.size == 2) {
            sect += "0"
        }
        val item = Item.getByNameOrId("${sect[0]}:${sect[1]}")
        return if (item != null) {
            ItemStack(item, 1, sect[2].toMeta)
        } else {
            null
        }
    }

fun getOreTags(stack: ItemStack): List<String> {
    return OreDictionary.getOreIDs(stack).map { OreDictionary.getOreName(it) }
}

val IBlockState.toItemStack: ItemStack
    get() = ItemStack(Item.getItemFromBlock(block), 1, block.getMetaFromState(this))