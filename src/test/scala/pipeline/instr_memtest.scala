package single_cycle
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._

import chiseltest.experimental.TestOptionBuilder . _

class instr_memtest extends FreeSpec with ChiselScalatestTester {
"Instruction memory Test" in{
    test(new Instr_mem("./src/main/scala/single_cycle/test.txt")){ x =>
        x.io.addr.poke(24.U)
        
        x.clock.step(10)

        x.io.instruction.expect(0.U)
    }}
    }