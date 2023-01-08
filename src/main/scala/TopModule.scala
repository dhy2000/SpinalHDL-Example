import spinal.core._

import scala.language.postfixOps

class TopModule extends Component {
  // module rename
  setDefinitionName("top_module")

  val io = new TopModule.IOBundle
  noIoPrefix() // don't generate "io_" prefix

  // signal rename
  io.cond0.setName("cond_0")
  io.cond1.setName("cond_1")

  private val counter: UInt = Reg(UInt(8 bits)) init 0

  when(io.cond0) {
    counter := counter + 1
  }

  io.state := counter
  io.flag := (counter === 0) | io.cond1
}

object TopModule {
  class IOBundle extends Bundle {
    val cond0: Bool = in Bool()
    val cond1: Bool = in Bool()
    val flag: Bool = out Bool()
    val state: UInt = out UInt (8 bits)
  }

  def main(args: Array[String]): Unit = {
    SpinalConfig(
      mode = Verilog,
      targetDirectory = "output_dir",
      defaultConfigForClockDomains = ClockDomainConfig(resetKind = SYNC)
    ).generate(new TopModule)
  }
}