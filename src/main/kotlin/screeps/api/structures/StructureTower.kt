package screeps.api.structures

import screeps.api.*

abstract external class StructureTower : Structure, Owned, EnergyContainer {
    fun attack(target: Creep): ScreepsReturnCode
    fun heal(target: Creep): ScreepsReturnCode
    fun repair(target: IStructure): ScreepsReturnCode
}