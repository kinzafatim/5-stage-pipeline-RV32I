package pipeline
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder . _

class Toptest extends FreeSpec with ChiselScalatestTester {
    "Execute Test" in {
        test(new Top){ x =>
            x.io.in.poke(1.S)

            x.clock.step(10)
            
            x.io.out.expect(1.S)


        }
}
}