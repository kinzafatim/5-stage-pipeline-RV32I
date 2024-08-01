package single_cycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _

class immGentest  extends FreeSpec with ChiselScalatestTester {
"immgen Tester" in{
    test(new ImmGen){ x =>
        x.io.instruction.poke("b00000000000000000010010011".U)
        x.clock.step(10)
        x.io.imm_val.expect(0.S)

    }}}