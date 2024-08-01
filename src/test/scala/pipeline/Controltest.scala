package single_cycle

import chisel3._
import chisel3.util._
import org.scalatest._
import chiseltest._

class Controltest extends FreeSpec with ChiselScalatestTester {
  "Control Test" in {
    test(new Control) { x =>
      // Set inputs for the instruction li x1, 3
      x.io.instruction.poke("b00000000000000011001000010010011".U) // 00300093
      x.io.btaken.poke(false.B)

      // Advance clock
      x.clock.step(1)

      // Check outputs after clock step
      x.io.aluOP.expect("b0001000".U) // ALU operation for ADDI (opcode 0010011, funct3 000)
      x.io.rs1.expect(0.U)            // rs1 should be 0 (register x0)
      x.io.rs2.expect(0.U)            // rs2 should be 0 (not used in this instruction)
      x.io.rd.expect(1.U)             // rd should be 1 (register x1)
      x.io.imm.expect(3.S)            // Immediate value should be 3
      x.io.regWrEn.expect(true.B)     // Register write enable should be true
      x.io.branch.expect(false.B)     // Branch signal should be false
      x.io.memRead.expect(false.B)    // Memory read signal should be false
      x.io.memWrite.expect(false.B)   // Memory write signal should be false
      x.io.memToReg.expect(false.B)   // Memory to register signal should be false
      x.io.aluImm.expect(true.B)      // ALU select immediate signal should be true
      x.io.branchfun3.expect(0.U)     // Branch function 3 should be 0
      x.io.pcsel.expect(false.B)      // Program counter select should be false
    }
  }
}
