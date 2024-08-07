package pipeline
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._

import chiseltest.experimental.TestOptionBuilder . _

class IFtest extends FreeSpec with ChiselScalatestTester {
"Instruction Fetch Test" in{
    test(new IF("./src/main/scala/pipeline/test.txt")){ x =>
        x.io.addr.poke(24.U)
        
        x.clock.step(10)

        x.io.instruction.expect(0.U)
    }}
    }