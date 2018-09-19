package screeps.api.structures

import screeps.api.RoomObject
import screeps.api.ScreepsReturnCode
import screeps.api.StructureConstant


open external class Structure : RoomObject {
    val hits: Double
    val hitsMax: Double
    val structureType: StructureConstant

    fun destroy(): ScreepsReturnCode
    fun isActive(): Boolean
    fun notifyWhenAttacked(enabled: Boolean): ScreepsReturnCode
}

open external class OwnedStructure : Structure {
    val my: Boolean
    val owner: Owner
}

external interface Owner {
    val username: String
}

external interface EnergyContainingStructure {
    val energy: Int
    val energyCapacity: Int
}

/**
 * Energy can be used for spawning of creeps.
 */
external interface EnergyStructure : EnergyContainingStructure

external interface DecayingStructure {
    val ticksToDecay: Int
}




