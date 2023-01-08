import spinal.core.assert
import spinal.core.sim._

import scala.util.Random

object TopModuleSim {
  def main(args: Array[String]): Unit = {
    SimConfig.withVCS.withFsdbWave.doSim(new TopModule) { dut =>
      dut.clockDomain.forkStimulus(period = 10)
      var modelState = 0
      for (i <- 0 to 99) {
        // Drive the dut inputs with random values
        dut.io.cond0 #= Random.nextBoolean()
        dut.io.cond1 #= Random.nextBoolean()

        // Wait a rising edge on the clock
        dut.clockDomain.waitRisingEdge()

        // Sample dut values
        println(s"$i: state = ${dut.io.state.toInt}, flag = ${dut.io.flag.toBoolean}")

        // Check that the dut values match with the reference model ones
        val modelFlag = modelState == 0 || dut.io.cond1.toBoolean
        assert(dut.io.state.toInt == modelState)
        assert(dut.io.flag.toBoolean == modelFlag)

        // Update the reference model value
        if (dut.io.cond0.toBoolean) {
          modelState = (modelState + 1) & 0xFF
        }
      }
    }
  }
}