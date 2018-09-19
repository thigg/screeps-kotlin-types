package screeps.api

import screeps.api.structures.Structure
import screeps.api.structures.StructureController
import screeps.api.structures.StructureStorage
import screeps.api.structures.StructureTerminal

external class Room {
    val controller: StructureController?
    val energyAvailable: Int
    val energyCapacityAvailable: Int
    val memory: dynamic
    val name: String
    val storage: StructureStorage?
    val terminal: StructureTerminal?
    val visual: RoomVisual

    fun createConstructionSite(x: Int, y: Int, structureType: StructureConstant): ScreepsReturnCode
    fun createConstructionSite(pos: RoomPosition, structureType: StructureConstant): ScreepsReturnCode
    fun createFlag(
        x: Int,
        y: Int,
        name: String = definedExternally,
        color: ColorConstant = definedExternally,
        secondaryColor: ColorConstant = definedExternally
    ): Any

    fun createFlag(
        pos: RoomPosition,
        name: String = definedExternally,
        color: ColorConstant = definedExternally,
        secondaryColor: ColorConstant = definedExternally
    ): Any

    fun <T : RoomObject> find(FIND_CONSTANT: FindConstant): Array<T>
    fun findExitTo(room: String): Any
    fun findExitTo(room: Room): Any
    fun findPath(fromPos: RoomPosition, toPos: RoomPosition, opts: FindPathOpts? = definedExternally): Array<PathStep>
    fun getPositionAt(x: Int, y: Int): RoomPosition?
    fun lookAt(x: Int, y: Int): Array<LookAt>
    fun lookAt(target: RoomPosition): Array<LookAt>
    fun lookAtArea(
        top: Int,
        left: Int,
        bottom: Int,
        right: Int,
        asArray: Boolean? = definedExternally
    ): dynamic

    fun <T> lookForAt(type: LookConstant, x: Int, y: Int): Array<T>?
}

external class LookAt {
    val type: LookConstant
    val creep: Creep?
    val structure: Structure?
    val terrain: String?
    val constructionSite: ConstructionSite?
    val resource: Resource?
}

fun Room.findCreeps() = find<Creep>(FIND_CREEPS)
fun Room.findEnergy() = find<Source>(FIND_SOURCES)
fun Room.findConstructionSites() = find<ConstructionSite>(FIND_CONSTRUCTION_SITES)
fun Room.findStructures() = find<Structure>(FIND_STRUCTURES)
fun Room.findDroppedEnergy() = find<Resource>(FIND_DROPPED_RESOURCES).filter { it.resourceType == RESOURCE_ENERGY }

class FindPathOpts(
    val ignoreCreeps: Boolean = false,
    val ignoreDestructibleStructures: Boolean = false,
    val ignoreRoads: Boolean = false,
    val maxOps: Int = 2000,
    val heuristicWeight: Double = 1.2,
    val serialize: Boolean = false,
    val maxRooms: Int = 16,
    val range: Int = 0
)

external interface PathStep {
    var x: Int
    var dx: Int
    var y: Int
    var dy: Int
    var direction: dynamic
}