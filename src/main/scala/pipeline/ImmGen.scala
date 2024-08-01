package single_cycle
import chisel3._
import chisel3.util._

class ImmGen extends Module {
  val io = IO(new Bundle {
    val instruction = Input(UInt(32.W))
    val imm_val = Output(SInt(32.W))
  })
  
  val wiree = WireInit(0.S(32.W)) // Initialize as signed integer
  io.imm_val := 0.S // Default value as signed integer

  val opcode = io.instruction(6, 0)

  switch(opcode) {
    is("b0010011".U) { // I/ load-type
      wiree := Cat(Fill(20, io.instruction(31)), io.instruction(31, 20)).asSInt()
      io.imm_val := wiree
    }
    is("b0100011".U) { // Store-type
      wiree := Cat(Fill(20, io.instruction(31)), io.instruction(31), io.instruction(30, 25), io.instruction(11, 7)).asSInt()
      io.imm_val := wiree
    }
    is("b1100011".U) { // B-type
      wiree := Cat(Fill(19, io.instruction(31)), io.instruction(31), io.instruction(7), io.instruction(30, 25), io.instruction(11, 8), 0.U(1.W)).asSInt()
      io.imm_val := wiree
    }
    is("b0110111".U) { // LUI
      wiree := Cat(io.instruction(31, 12), Fill(11, 0.U),0.U).asSInt()
      io.imm_val := wiree
    }
    is("b0010111".U) { // AUIPC
      wiree := Cat(io.instruction(31, 12), Fill(12, 0.U)).asSInt()
      io.imm_val := wiree
    }
    is("b1101111".U) { // JAL
      wiree := Cat(Fill(11, io.instruction(31)), io.instruction(31), io.instruction(19, 12), io.instruction(20), io.instruction(30, 21), 0.U(1.W)).asSInt()
      io.imm_val := wiree
    }
    is("b1100111".U) { // JALR
      wiree := Cat(Fill(20, io.instruction(31)), io.instruction(31, 20)).asSInt()
      io.imm_val := wiree
    }
  }
}
