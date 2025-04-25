package com.rockthejvm.part2oop

object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    //add fields/methods
    def openDocument(): Unit = {
      if(this == READ) println("Opening document. ")
      else println("Reading not allowed.")
    }
  }

  val somePermissions: Permissions = Permissions.READ

//  object PermissionsWithBits {
//    def fromBits(bits: Int): PermissionsWithBits = // whatever
//      PermissionsWithBits.NONE
//  }

  object PermissionsWithBits {
    def fromBits(bits: Int): Set[Permissions] = {
      var permissions = Set.empty[Permissions]
      if ((bits & 0x1) != 0) permissions += Permissions.READ
      if ((bits & 0x2) != 0) permissions += Permissions.WRITE
      if ((bits & 0x4) != 0) permissions += Permissions.EXECUTE
      if (permissions.isEmpty) permissions += Permissions.NONE
      permissions
    }
  }

  // standard API
  val somePermissionsOrdinal = somePermissions.ordinal
  val allPermissions = PermissionsWithBits.values // array of all possible values of the enum
  val readPermission: Permissions = Permissions.valueOf("READ") // Permissions.READ

  // constructor args
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  def main(args: Array[String]): Unit = {
    println(somePermissionsOrdinal)
    println(allPermissions)
  }
}
