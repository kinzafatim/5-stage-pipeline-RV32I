package pipeline
import chisel3._
import chisel3.util._
class Top extends Module {
  val io = IO(new Bundle {
    val in = Input(SInt(32.W))
    val out = Output(SInt(32.W))

  })
  io.out := io.in

 // objects
 val fetch = Module(new IF("/home/kinzaa/Desktop/5-stage-pipeline-RV32I/src/main/scala/pipeline/test.txt"))
 val decoder = Module(new ID)
 val execute = Module(new EX)
 val memory = Module(new MA)
 val writeBack = Module(new WB)

 // IF/ID pipeline registers (Fetch to Decode)
 val F_D_ins = RegNext(fetch.io.instruction) // Output of fetch
 decoder.io.instruction := F_D_ins           // input of decode

 val F_D_pc = RegNext(fetch.io.pc_out)   // output of Fetch
 val F_D_pc4 = RegNext(fetch.io.pc_4out) // output of Fetch

 // ID/EX pipeline registers (Decode to Execute)
 val D_EX_regrs1 = RegNext(decoder.io.regrs1)
 val D_EX_regrs2 = RegNext(decoder.io.regrs2)
 val D_EX_aluOP = RegNext(decoder.io.aluOP)
 val D_EX_rs1 = RegNext(decoder.io.rs1)
 val D_EX_rs2 = RegNext(decoder.io.rs2)
 val D_EX_rd = RegNext(decoder.io.rd)
 val D_EX_imm = RegNext(decoder.io.imm)
 val D_EX_regWrEn = RegNext(decoder.io.regWrEn)
 val D_EX_branch = RegNext(decoder.io.branch)
 val D_EX_memRead = RegNext(decoder.io.memRead)
 val D_EX_memWrite = RegNext(decoder.io.memWrite)
 val D_EX_memToReg = RegNext(decoder.io.memToReg)
 val D_EX_aluImm = RegNext(decoder.io.aluImm)
 val D_EX_branchfun3 = RegNext(decoder.io.branchfun3) 
 val D_EX_pcsel = RegNext(decoder.io.pcsel)
 val D_EX_jaltype = RegNext(decoder.io.jaltype)
 val D_EX_jalrtype = RegNext(decoder.io.jalrtype)
 val D_EX_luitype = RegNext(decoder.io.luitype)
 val D_EX_auipctype = RegNext(decoder.io.auipctype) 
 
 // Execute pipeline registers
 val EX_br_taken = RegNext(execute.io.br_taken)
 val EX_out = RegNext(execute.io.out)
 val EX_sum = RegNext(execute.io.sum)

 // Execute connections
 execute.io.fnct3 := D_EX_branchfun3
 execute.io.branch := D_EX_branch
 execute.io.x1 := D_EX_regrs1
 execute.io.x2 := D_EX_regrs2
 // ALU
 execute.io.in_A := Mux(D_EX_jaltype || (D_EX_branch && EX_br_taken), F_D_pc.asSInt, D_EX_regrs1)
 execute.io.in_B := Mux(D_EX_aluImm, D_EX_imm, D_EX_regrs2)
 execute.io.alu_Op := D_EX_aluOP


 // Memory(MA) pipeline registers
 val MA_readData = RegNext(memory.io.readData)

   // Memory Stage connections
  memory.io.addr := EX_out.asUInt
  memory.io.writeData := D_EX_regrs2
  memory.io.memRead := D_EX_memRead
  memory.io.memWrite := D_EX_memWrite

  // WriteBack connections
  writeBack.io.aluResult := EX_out
  writeBack.io.memReadData := MA_readData
  writeBack.io.memToReg := D_EX_memToReg
  writeBack.io.rd := D_EX_rd
  writeBack.io.regWriteEnable := D_EX_regWrEn

  // WriteBack output to reg file in Decode
  decoder.io.writeAddr := writeBack.io.writeRegAddr
  decoder.io.writeData := writeBack.io.writeData
  decoder.io.writeEnable := writeBack.io.regWrEn


  val EX_auipc_result = RegNext(F_D_pc.asSInt + D_EX_imm)
  // Write back data based on instr type
  when(D_EX_jalrtype) {
    writeBack.io.writeData := F_D_pc4.asSInt

  }.elsewhen(D_EX_luitype) {
    writeBack.io.writeData := D_EX_imm

  }.elsewhen(D_EX_auipctype) {
    writeBack.io.writeData := EX_auipc_result

  }.otherwise {
    writeBack.io.writeData := Mux(D_EX_memToReg, MA_readData, EX_out)
  }
  io.out := execute.io.out


}
