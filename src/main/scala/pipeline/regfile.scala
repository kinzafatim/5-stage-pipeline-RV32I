package single_cycle
import chisel3._
import chisel3.util._

class regfile extends Module {
  val io = IO(new Bundle {
    val readAddr1 = Input(UInt(5.W)) // rs1
    val readAddr2 = Input(UInt(5.W)) // rs2
    val writeAddr = Input(UInt(5.W)) // rd (address)

    val writeData = Input(SInt(32.W))
    val writeEnable = Input(Bool()) 
    
    val rs1 = Output(SInt(32.W)) // rs1
    val rs2 = Output(SInt(32.W)) // rs2
  })

  val regFile = RegInit(VecInit(Seq.fill(32)(0.S(32.W)))) 

  io.rs1 := Mux(io.readAddr1 === 0.U, 0.S, regFile(io.readAddr1)) // x0=0
  io.rs2 := Mux(io.readAddr2 === 0.U, 0.S, regFile(io.readAddr2)) // x0=0

  when(io.writeEnable === 1.U) {
    regFile(io.writeAddr) := io.writeData
  }
}