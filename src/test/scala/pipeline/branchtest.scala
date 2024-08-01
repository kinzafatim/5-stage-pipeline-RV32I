package single_cycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _
class branchtest extends FreeSpec with ChiselScalatestTester {
"branch Test" in {
    test(new Branch){ x =>
    x.io.fnct3.poke(1.U)

    x.io.branch.poke(1.B)

    x.io.x1.poke(5.S)
    x.io.x2.poke(10.S)

    x.clock.step(1)

    x.io.br_taken.expect(1.B)
    }
}
}