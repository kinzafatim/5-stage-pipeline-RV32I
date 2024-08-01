package single_cycle
import chisel3._
import chisel3.util._
import chisel3.util.experimental.loadMemoryFromFile
class Instr_mem ( initFile : String ) extends Module {
    val io = IO(new Bundle {
        val addr = Input(UInt(32.W))
        val instruction = Output(UInt(32.W))
  })
  val mem = Mem(1024, UInt(32.W)) 
  loadMemoryFromFile ( mem , initFile ) 
  io.instruction := mem(io.addr/4.U)
}