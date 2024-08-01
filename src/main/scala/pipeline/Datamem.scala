package single_cycle
import chisel3._
import chisel3.util._
class Datamem extends Module {
  val io = IO(new Bundle{
    val addr      = Input(UInt(32.W))   // Address to read/write
    val writeData = Input(SInt(32.W))  // Data to write
    val memRead   = Input(Bool())    // Enable memory read
    val memWrite  = Input(Bool())     // Enable memory write
    
    val readData  = Output(SInt(32.W))  // Data read from memory
  })
  val mem = Mem(1024, SInt(32.W))
  io.readData := 0.S // default value

  when(io.memRead) {
    io.readData := mem(io.addr)
  }
  when(io.memWrite) {
    mem(io.addr) := io.writeData
  }
}
