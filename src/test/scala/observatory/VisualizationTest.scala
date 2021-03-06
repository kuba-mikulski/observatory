package observatory


import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

trait VisualizationTest extends FunSuite with Checkers {

  import scala.math._

  val epsilon = 20 // kilometers

  test("distance between London and Paris should be around 340 km") {
    val paris = Location(48.864716, 2.349014)
    val london = Location(51.509865, -0.118092)
    assert(abs(london.distance(paris) - 344) < epsilon)
  }

  test("distance between Beijing and Rio de Janeiro should be around 17330 km") {
    val beijing = Location(39.913818, 116.363625)
    val rio = Location(-22.9032315871, -43.1729427749)
    assert(abs(beijing.distance(rio) - 17330) < epsilon)
  }

  test("distance between Auckland and Seville (antipodes) should be around 20000 km") {
    val christchurch = Location(-37, 175)
    val seville = Location(37, -5)
    assert(christchurch.antipodes == seville)
    assert(abs(christchurch.distance(seville) - 20000) < epsilon)
  }

  test("interpolated color below black should be black") {
    val points: Seq[(Temperature, Color)] = Seq((0, Color(0,0,0)), (100, Color(255,255,255)))
    assert(Visualization.interpolateColor(points, -20) == Color(0,0,0))
  }

  test("interpolated color above white should be white") {
    val points: Seq[(Temperature, Color)] = Seq((0, Color(0,0,0)), (100, Color(255,255,255)))
    assert(Visualization.interpolateColor(points, 120) == Color(255,255,255))
  }

  test("interpolated color between black and white should be gray") {
    val points: Seq[(Temperature, Color)] = Seq((0, Color(0,0,0)), (100, Color(255,255,255)))
    assert(Visualization.interpolateColor(points, 50) == Color(128,128,128))
  }
}
