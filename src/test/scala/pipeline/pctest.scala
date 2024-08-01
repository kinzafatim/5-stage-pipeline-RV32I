package single_cycle
import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class pctest extends FreeSpec with ChiselScalatestTester {
  "Program counter" in {
    test(new Pc){ x =>
        x.io.pcsel.poke(0.B)
        x.io.aluout.poke(4.U)
        x.clock.step(1)
        x.io.pc_out.expect(4.U)
       
    } 
  }
}