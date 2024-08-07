package pipeline
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _

class EXtest extends FreeSpec with ChiselScalatestTester {
    "Execute Test" in {
        test(new EX){ x =>
            // Branch inputs
            x.io.fnct3.poke(1.U)
            x.io.branch.poke(1.B)
            x.io.x1.poke(5.S)
            x.io.x2.poke(10.S)

            // ALU inputs
            x.io.in_A.poke(10.S)
            x.io.in_B.poke(1.S)
            x.io.alu_Op.poke(1.U)

            x.clock.step(10)
            
            x.io.out.expect(9.S) // Alu output
            x.io.sum.expect(9.S) // Alu output

            x.io.br_taken.expect(1.B) // Branch output


        }
}
}