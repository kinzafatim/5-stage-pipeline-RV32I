package single_cycle
import chisel3._
import chisel3.util._

class RV32I extends Module {
  val io = IO(new Bundle {
    val out = Output(SInt(32.W))
    val in = Input(SInt(32.W))
  })
  io.out := io.in

  // Module instantiations
  val alu = Module(new ALU)
  val branch = Module(new Branch)
  val control = Module(new Control)
  val datamem = Module(new Datamem)
  // val immGen = Module(new ImmGen)
  val instrMem = Module(new Instr_mem("/home/kinzaa/single cycle/src/main/scala/single_cycle/test.txt"))
  val pc = Module(new Pc)
  val regFile = Module(new regfile)

  // Instruction memory fetch
  instrMem.io.addr := pc.io.pc_out

  // PC connections
  pc.io.pcsel := control.io.pcsel
  pc.io.aluout := alu.io.out.asUInt

  // Control module connections
  control.io.instruction := instrMem.io.instruction
  control.io.btaken := branch.io.br_taken

  // Branch module connections
  branch.io.fnct3 := control.io.branchfun3
  branch.io.branch := control.io.branch
  branch.io.x1 := regFile.io.rs1
  branch.io.x2 := regFile.io.rs2

  // Register file connections
  regFile.io.readAddr1 := control.io.rs1
  regFile.io.readAddr2 := control.io.rs2
  regFile.io.writeAddr := control.io.rd
  regFile.io.writeData := Mux(control.io.memToReg, datamem.io.readData, alu.io.out)
  regFile.io.writeEnable := control.io.regWrEn

  // ALU connections
  alu.io.in_A := Mux((branch.io.br_taken && control.io.branch) || control.io.jaltype  , pc.io.pc_out.asSInt() , regFile.io.rs1)
  alu.io.in_B := Mux(control.io.aluImm, control.io.imm, regFile.io.rs2) 
  alu.io.alu_Op := control.io.aluOP

  // Data memory connections
  datamem.io.addr := alu.io.out.asUInt
  datamem.io.writeData := regFile.io.rs2 // store instruction
  datamem.io.memRead := control.io.memRead
  datamem.io.memWrite := control.io.memWrite

  when(control.io.jalrtype){ // JALR instruction
    regFile.io.writeData := pc.io.pc_4out.asSInt
  }

  when(control.io.luitype) { // LUI instruction
    regFile.io.writeData := control.io.imm 
  }
  
  when(control.io.auipctype) { // AUIPC instruction
    regFile.io.writeData := pc.io.pc_out.asSInt + control.io.imm 
  }
  
  io.out := alu.io.out
}
