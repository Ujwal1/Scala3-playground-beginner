package com.rockthejvm.part2oop

val meaningOfLife = 42
def computeMyLife: String = "SCALA"
// scala package will create a special object called part2oop and that will hold the above two definitions

object PackagesImportsExports { // top-level


  // packages: folders

//   fully qualified name
  val aList: com.rockthejvm.practice.LList[Int] = ???

  // import
  import  com.rockthejvm.practice.LList
  val anotherList: LList[Int] = ???

  // import under an alias
  import java.util.List as JList
  val aJavaList: JList[Int] = ???

  // import everything
  import com.rockthejvm.practice.*
  val aPredicate: Cons[Int] = ???

  // import multiple symbols
  import PhysicsConstants.{SPEED_OF_LIGHT, EARTH_GRAVITY}
  val c = SPEED_OF_LIGHT

  //import everything but something
  object PlainPhysics {
    import PhysicsConstants.{PLANK as _, *}
    val c = SPEED_OF_LIGHT
    // donesnt iomport PLANK
//    val plank = PLANK // will not work
  }

  import com.rockthejvm.part2oop.* // automatically imports "meaningOfLife" and "computeMyLife"

  val mol: Int = meaningOfLife
  // do not add global variables unless u absolutely need

  // default imports
  // scala.*. scala.Predef.*, java.lang.*

  //exports
  class PhysicsCalculator {
    import PhysicsConstants.*
    def computePhotonEnergy(wavelength: Double) : Double = {
      PLANK / wavelength
    }
  }

  object ScienceApp {
    val physicsCalculator = new PhysicsCalculator

    export physicsCalculator.computePhotonEnergy // this method not available under the plain name instead of using OBJ.FUN

    def computeEquivalentMass(wavelength: Double): Double =
      computePhotonEnergy(wavelength) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT)
  }



  def main(args: Array[String]): Unit = {

  }

  object PhysicsConstants {
    // constsns
    val SPEED_OF_LIGHT = 299792458
    val PLANK = 6.62e-34
    val EARTH_GRAVITY = 9.8
  }
}
