package pipeline
import chisel3._
import chisel3.util._
import chisel3.util.experimental.loadMemoryFromFile

// Combined Module IF
class IF(initFile: String) extends Module {
  val io = IO(new Bundle {
    // pc inputs and outputs
    val pcsel = Input(Bool())
    val aluout = Input(UInt(32.W))
    val pc_out = Output(UInt(32.W))
    val pc_4out = Output(UInt(32.W))
    
    // Instruction mem input/output
    val addr = Input(UInt(32.W))
    val instruction = Output(UInt(32.W))
  })

  // Instr mem
  val instrMem = Mem(1024, UInt(32.W))
  loadMemoryFromFile(instrMem, initFile)
  io.instruction := instrMem(io.addr / 4.U)

  // pc
  val pc = RegInit(0.U(32.W))
  pc := Mux(io.pcsel, io.aluout, pc + 4.U)
  io.pc_out := pc
  io.pc_4out := pc + 4.U
}
