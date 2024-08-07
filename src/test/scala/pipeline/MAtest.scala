package pipeline
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _
class MAtest extends FreeSpec with ChiselScalatestTester {
    "Memory Test" in {
        test(new MA){ x =>
            x.io.addr.poke(10.U)
            x.io.writeData.poke(0.S)

            x.io.memRead.poke(1.B)
            x.io.memWrite.poke(0.B)

            x.clock.step(10)

            x.io.readData.expect(0.S)
    }
}
}