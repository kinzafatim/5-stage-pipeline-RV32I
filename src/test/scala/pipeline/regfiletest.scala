package single_cycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder._

class regfiletest extends FreeSpec with ChiselScalatestTester {
"Register file Test" in{
    test(new regfile){ x =>

        x.io.readAddr1.poke(24.U)
        x.io.readAddr2.poke(25.U)
        x.io.writeAddr.poke(26.U)

        x.io.writeData.poke(42.S)
        x.io.writeEnable.poke(1.B)
        
        x.clock.step(100)

        x.io.rs1.expect(0.S)
        x.io.rs2.expect(0.S)
    }}
    }