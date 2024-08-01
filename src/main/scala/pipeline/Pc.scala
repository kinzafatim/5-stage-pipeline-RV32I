package single_cycle
import chisel3._
import chisel3.util._

class Pc extends Module {
  val io = IO(new Bundle {
    val pcsel = Input(Bool())
    val aluout = Input(UInt(32.W))
    val pc_out = Output(UInt(32.W))
    val pc_4out = Output(UInt(32.W))
  })
  
  val pc = RegInit(0.U(32.W))

  pc := Mux(io.pcsel, io.aluout , pc + 4.U )
  io.pc_out := pc
  io.pc_4out:=pc+4.U
}