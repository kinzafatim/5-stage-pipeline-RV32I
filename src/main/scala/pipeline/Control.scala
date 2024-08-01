package single_cycle

import chisel3._
import chisel3.util._

class Control extends Module {
  val io = IO(new Bundle {
    val instruction = Input(UInt(32.W))
    val btaken = Input(Bool())

    val aluOP = Output(UInt(4.W))
    val rs1 = Output(UInt(5.W))
    val rs2 = Output(UInt(5.W))
    val rd = Output(UInt(5.W))
    val imm = Output(SInt(32.W))
    val regWrEn = Output(Bool())
    val branch = Output(Bool())
    val memRead = Output(Bool())
    val memWrite = Output(Bool())
    val memToReg = Output(Bool())
    val aluImm = Output(Bool())
    val branchfun3 = Output(UInt(3.W))
    val pcsel = Output(Bool())
    val jaltype = Output(Bool())
    val jalrtype = Output(Bool())
    val luitype = Output(Bool())
    val auipctype = Output(Bool())
  })

  // Default values
  io.aluOP := 0.U
  io.rs1 := 0.U
  io.rs2 := 0.U
  io.rd := 0.U
  io.imm := 0.S
  io.regWrEn := 0.B
  io.branch := 0.B
  io.memRead := 0.B
  io.memWrite := 0.B
  io.memToReg := 0.B
  io.aluImm := 0.B
  io.branchfun3 := 0.U
  io.pcsel := 0.B
  io.jaltype := 0.B
  io.jalrtype := 0.B
  io.luitype := 0.B
  io.auipctype := 0.B

  val opcode = io.instruction(6, 0) // opcode for instruction
  val func3 = io.instruction(14, 12)
  val func7 = io.instruction(31, 25)

  // Instantiate ImmGen module
  val immGen = Module(new ImmGen)
  immGen.io.instruction := io.instruction
  io.imm := immGen.io.imm_val

  switch(opcode) {
    is("b0110011".U) { // R-type instruction
      io.aluOP := Cat(func3, func7(5))
      io.rs1 := io.instruction(19, 15)
      io.rs2 := io.instruction(24, 20)
      io.rd := io.instruction(11, 7)
      io.regWrEn := 1.B
      io.aluImm := 0.B
      io.pcsel := 0.B
      io.jalrtype :=0.B
    }
    is("b0010011".U) { // I-type instruction
      io.aluOP := Cat(func3, 0.U)
      io.rs1 := io.instruction(19, 15)
      io.rd := io.instruction(11, 7)
      io.rs2 := 0.U
      io.regWrEn := 1.B
      io.aluImm := 1.B
      io.pcsel := 0.B
      io.jalrtype :=0.B
    }
    is("b0000011".U) { // Load instruction
      io.aluOP := Cat(func3, 0.U)
      io.rs1 := io.instruction(19, 15)
      io.rd := io.instruction(11, 7)
      io.rs2 := 0.U
      io.regWrEn := 1.B
      io.memRead := 1.B
      io.memToReg := 1.B // use datamemory
      io.aluImm := 1.B
      io.pcsel := 0.B
      io.jalrtype :=0.B
    }
    is("b0100011".U) { // Store instruction
      io.aluOP := Cat(func3, 0.U)
      io.rs1 := io.instruction(19, 15)
      io.rs2 := io.instruction(24, 20)
      io.rd := 0.U
      io.memWrite := 1.B
      io.aluImm := 1.B
      io.pcsel := 0.B
      io.jalrtype :=0.B
    }
    is("b1100011".U) { // Branch instruction
      io.aluOP := 0.U
      io.rs1 := io.instruction(19, 15)
      io.rs2 := io.instruction(24, 20)
      io.rd := 0.U
      io.branchfun3 := func3
      io.aluImm := 1.B
      io.branch := 1.B
      io.pcsel := Mux(io.branch && io.btaken, 1.B, 0.B)
      io.jalrtype :=0.B
    }
    is("b0110111".U) { // LUI (Load Upper Immediate)
      io.aluOP := 0.U
      io.rs1 := 0.U
      io.rs2 := 0.U
      io.rd := io.instruction(11, 7)
      io.regWrEn := 1.B
      io.aluImm := 1.B
      io.pcsel := 0.B
      io.luitype := 1.B
      io.jalrtype :=0.B
    }
    is("b0010111".U) { // AUIPC (Add Upper Immediate to PC)
      io.aluOP := 0.U
      io.rs1 := 0.U
      io.rs2 := 0.U
      io.rd := io.instruction(11, 7)
      io.regWrEn := 1.B
      io.aluImm := 1.B
      io.pcsel := 0.B
      io.auipctype := 1.B
      io.jalrtype :=0.B
      io.luitype := 0.B
    }
    is("b1101111".U) { // JAL (Jump and Link)
      io.aluOP := 0.U
      io.rs1 := 0.U
      io.rs2 := 0.U
      io.rd := io.instruction(11, 7)
      io.regWrEn := 1.B
      io.branch := 0.B
      io.aluImm := 1.B
      io.pcsel := 1.B
      io.jaltype := 1.B
      io.jalrtype :=0.B
      io.luitype := 0.B
    }
    is("b1100111".U) { // JALR (Jump and Link Register)
      io.aluOP := Cat(func3, 0.U)
      io.rs1 := io.instruction(19, 15)
      io.rs2 := 0.U
      io.rd := io.instruction(11, 7)
      io.regWrEn := 1.B
      io.branch := 0.B
      io.aluImm := 1.B
      io.pcsel := 1.B
      io.jaltype := 0.B
      io.jalrtype :=1.B
      io.luitype := 0.B
    }
  }
}
