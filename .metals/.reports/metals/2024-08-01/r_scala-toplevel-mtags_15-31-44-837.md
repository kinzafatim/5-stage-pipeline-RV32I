error id: file://<WORKSPACE>/src/main/scala/pipeline/InstrFetch.scala:[64..71) in Input.VirtualFile("file://<WORKSPACE>/src/main/scala/pipeline/InstrFetch.scala", "package pipeline
import chisel3._
import chisel3.util._

class  extends Module {
  val io = IO(new Bundle {
    val pcsel = Input(Bool())        // Control signal for PC update
    val aluout = Input(UInt(32.W))   // ALU result for branch targets
    val instruction = Output(UInt(32.W)) // Fetched instruction
    val pc_out = Output(UInt(32.W)) // Current PC value
    val pc_4out = Output(UInt(32.W)) // PC incremented by 4
  })

  // Instantiate PC and Instruction Memory modules
  val pc = Module(new Pc())
  val instrMem = Module(new Instr_mem(initFile))

  // Connect PC and Instruction Memory modules
  pc.io.pcsel := io.pcsel
  pc.io.aluout := io.aluout
  instrMem.io.addr := pc.io.pc_out

  // Connect outputs
  io.pc_out := pc.io.pc_out
  io.pc_4out := pc.io.pc_4out
  io.instruction := instrMem.io.instruction
}
")
file://<WORKSPACE>/src/main/scala/pipeline/InstrFetch.scala
file://<WORKSPACE>/src/main/scala/pipeline/InstrFetch.scala:5: error: expected identifier; obtained extends
class  extends Module {
       ^
#### Short summary: 

expected identifier; obtained extends