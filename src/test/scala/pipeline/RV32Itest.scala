package single_cycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder._

class RV32Itest  extends FreeSpec with ChiselScalatestTester {
"Rv32i Test" in{
    test(new RV32I){ 
        x=>
        x.clock.step(120)
        x.io.in.poke(0.S)
        x.io.out.expect(0.S)
    }
}
}
