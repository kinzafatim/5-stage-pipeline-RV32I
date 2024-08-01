package single_cycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _
class Alutest extends FreeSpec with ChiselScalatestTester {
"ALU Test" in {
    test(new ALU){ x =>
    x.io.in_A.poke(10.S)
    x.io.in_B.poke(1.S)
    x.io.alu_Op.poke(1.U)
    x.clock.step(10)
    x.io.out.expect(9.S)
    x.io.sum.expect(9.S)
    }
}
}