package pipeline
import chisel3._
import chisel3.util._
class WB extends Module {
  val io = IO(new Bundle {
    val aluResult = Input(SInt(32.W))     // ALU output
    val memReadData = Input(SInt(32.W))   // Data read from memory
    val memToReg = Input(Bool())          // Control signal
    val rd = Input(UInt(5.W))             // Destination reg address
    val regWriteEnable = Input(Bool())    // Reg write enable signal

    val writeData = Output(SInt(32.W))    // Data to write back to reg file
    val writeRegAddr = Output(UInt(5.W))  // Address of reg to write back
    val regWrEn = Output(Bool())          // Signal to write to reg file
  })

  io.writeData := Mux(io.memToReg, io.memReadData, io.aluResult)
  io.writeRegAddr := io.rd
  io.regWrEn := io.regWriteEnable
}
